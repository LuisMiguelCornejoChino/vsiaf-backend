package com.vsiaf.examen.service;

import com.vsiaf.examen.dto.RespaldoDto;
import com.vsiaf.examen.entity.Respaldo;
import com.vsiaf.examen.repository.RespaldoRepository;
import com.vsiaf.examen.util.SqlDumpGenerator;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;
import javax.sql.DataSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class RespaldoService {

    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
    private static final ZoneId BOLIVIA = ZoneId.of("America/La_Paz");

    private final RespaldoRepository repository;
    private final DataSource dataSource;

    public RespaldoService(RespaldoRepository repository, DataSource dataSource) {
        this.repository = repository;
        this.dataSource = dataSource;
    }

    public List<RespaldoDto> listar() {
        return repository.listarResumen();
    }

    public RespaldoDto crear() {
        String sql;
        try (Connection con = dataSource.getConnection()) {
            // La tabla "respaldo" se excluye para que un respaldo no contenga a los anteriores.
            sql = SqlDumpGenerator.generar(con, Set.of("respaldo"));
        } catch (SQLException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "No se pudo generar el respaldo: " + e.getMessage(), e);
        }

        Respaldo r = new Respaldo();
        r.setNomrespaldo("respaldo_" + ZonedDateTime.now(BOLIVIA).format(FORMATO) + ".sql");
        r.setFecrespaldo(Instant.now());
        r.setTamrespaldo((long) sql.getBytes(StandardCharsets.UTF_8).length);
        r.setContenido(sql);
        r = repository.save(r);

        return new RespaldoDto(r.getCodrespaldo(), r.getNomrespaldo(), r.getFecrespaldo(), r.getTamrespaldo());
    }

    public Respaldo obtener(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Respaldo no encontrado"));
    }

    public void eliminar(Long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Respaldo no encontrado");
        }
        repository.deleteById(id);
    }
}
