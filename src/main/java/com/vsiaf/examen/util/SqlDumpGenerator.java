package com.vsiaf.examen.util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HexFormat;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * Genera un respaldo en SQL (sentencias INSERT) de las tablas del esquema "public",
 * usando solo JDBC. No necesita pg_dump instalado, así que funciona en Render.
 *
 * Alcance: solo DATOS. La estructura (tablas) debe existir antes de restaurar.
 * Las tablas se ordenan según sus llaves foráneas para que el script se pueda
 * ejecutar de arriba hacia abajo.
 */
public final class SqlDumpGenerator {

    private static final String ESQUEMA = "public";

    private SqlDumpGenerator() {
    }

    public static String generar(Connection con, Set<String> excluir) throws SQLException {
        DatabaseMetaData meta = con.getMetaData();
        Set<String> tablas = new TreeSet<>();
        try (ResultSet rs = meta.getTables(null, ESQUEMA, "%", new String[] {"TABLE"})) {
            while (rs.next()) {
                String nombre = rs.getString("TABLE_NAME");
                if (!excluir.contains(nombre)) {
                    tablas.add(nombre);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append("-- vSIAF - Respaldo de datos\n");
        sb.append("-- Generado: ").append(OffsetDateTime.now()).append('\n');
        sb.append("-- Base de datos: ").append(con.getCatalog()).append('\n');
        sb.append("-- Contenido: solo datos (INSERT). La estructura debe existir antes de restaurar.\n\n");

        for (String tabla : ordenarPorDependencias(meta, tablas)) {
            volcarTabla(con, tabla, sb);
        }
        return sb.toString();
    }

    /** Ordena las tablas: primero las referenciadas por llaves foráneas. */
    static List<String> ordenarPorDependencias(DatabaseMetaData meta, Set<String> tablas)
            throws SQLException {
        Map<String, Set<String>> depende = new HashMap<>();
        for (String t : tablas) {
            Set<String> padres = new LinkedHashSet<>();
            try (ResultSet rs = meta.getImportedKeys(null, ESQUEMA, t)) {
                while (rs.next()) {
                    String padre = rs.getString("PKTABLE_NAME");
                    if (!padre.equals(t) && tablas.contains(padre)) {
                        padres.add(padre);
                    }
                }
            }
            depende.put(t, padres);
        }

        List<String> orden = new ArrayList<>();
        Set<String> pendientes = new TreeSet<>(tablas);
        boolean avanzo = true;
        while (!pendientes.isEmpty() && avanzo) {
            avanzo = false;
            for (String t : new ArrayList<>(pendientes)) {
                if (orden.containsAll(depende.get(t))) {
                    orden.add(t);
                    pendientes.remove(t);
                    avanzo = true;
                }
            }
        }
        orden.addAll(pendientes); // ciclos de FK: se agregan al final
        return orden;
    }

    private static void volcarTabla(Connection con, String tabla, StringBuilder sb)
            throws SQLException {
        String destino = ident(ESQUEMA) + "." + ident(tabla);
        try (Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM " + destino)) {
            ResultSetMetaData md = rs.getMetaData();
            int n = md.getColumnCount();

            StringBuilder columnas = new StringBuilder();
            for (int i = 1; i <= n; i++) {
                if (i > 1) {
                    columnas.append(", ");
                }
                columnas.append(ident(md.getColumnName(i)));
            }

            int filas = 0;
            StringBuilder inserts = new StringBuilder();
            while (rs.next()) {
                inserts.append("INSERT INTO ").append(destino)
                        .append(" (").append(columnas).append(") VALUES (");
                for (int i = 1; i <= n; i++) {
                    if (i > 1) {
                        inserts.append(", ");
                    }
                    inserts.append(literal(rs.getObject(i)));
                }
                inserts.append(");\n");
                filas++;
            }

            sb.append("-- Tabla: ").append(tabla).append(" (").append(filas).append(" filas)\n");
            sb.append(inserts);
            for (int i = 1; i <= n; i++) {
                if (md.isAutoIncrement(i) && filas > 0) {
                    String col = ident(md.getColumnName(i));
                    sb.append("SELECT setval(pg_get_serial_sequence('").append(destino).append("', '")
                            .append(md.getColumnName(i)).append("'), (SELECT MAX(").append(col)
                            .append(") FROM ").append(destino).append("));\n");
                }
            }
            sb.append('\n');
        }
    }

    static String ident(String nombre) {
        return "\"" + nombre.replace("\"", "\"\"") + "\"";
    }

    static String literal(Object v) {
        if (v == null) {
            return "NULL";
        }
        if (v instanceof Boolean b) {
            return b ? "TRUE" : "FALSE";
        }
        if (v instanceof Double d && (d.isNaN() || d.isInfinite())) {
            return "'" + d + "'";
        }
        if (v instanceof Float f && (f.isNaN() || f.isInfinite())) {
            return "'" + f + "'";
        }
        if (v instanceof java.math.BigDecimal bd) {
            return bd.toPlainString();
        }
        if (v instanceof Number) {
            return v.toString();
        }
        if (v instanceof byte[] bytes) {
            return "decode('" + HexFormat.of().formatHex(bytes) + "', 'hex')";
        }
        return "'" + String.valueOf(v).replace("'", "''") + "'";
    }
}
