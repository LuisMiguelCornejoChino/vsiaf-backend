# vSIAF Backend (Spring Boot 4 · Java 21 · PostgreSQL)

Endpoints:

| Método | Ruta | Descripción |
|---|---|---|
| GET / POST | `/api/estado` | Listar / crear estado |
| GET / PUT / DELETE | `/api/estado/{id}` | Obtener / modificar / eliminar |
| GET | `/api/respaldo` | Historial de respaldos |
| POST | `/api/respaldo` | Generar un respaldo nuevo (script SQL) |
| GET | `/api/respaldo/{id}/descargar` | Descargar el .sql |
| DELETE | `/api/respaldo/{id}` | Eliminar un respaldo |
| GET | `/api/health` | Estado del servicio |

## Correr en local
1. Crear la base: `CREATE DATABASE vsiaf;`
2. Variables (opcionales, hay valores por defecto): `DB_URL`, `DB_USER`, `DB_PASSWORD`
3. `gradle bootRun` (o `./gradlew bootRun` si tienes el wrapper)

Las tablas `estado` y `respaldo` se crean solas (`ddl-auto=update`).

## Desplegar en Render
1. Subir este proyecto a un repositorio de GitHub (`git add . && git commit -m "backend" && git push`).
2. Render → **New → PostgreSQL** (si aún no tienes base). Copia host, base, usuario y contraseña.
3. Render → **New → Web Service** → elige el repositorio:
   - **Language / Runtime:** Docker
   - **Health Check Path:** `/api/health`
   - **Environment:**
     - `DB_URL` = `jdbc:postgresql://<host>:5432/<base>`
       (si usas el host *externo* de Render agrega `?sslmode=require`)
     - `DB_USER` = usuario
     - `DB_PASSWORD` = contraseña
     - `CORS_ORIGINS` (opcional) = `http://localhost:4200,https://jdka-suburbio.github.io`
4. Cada `git push` a la rama configurada vuelve a desplegar solo.
5. Probar: `https://<tu-servicio>.onrender.com/api/health` → `{"status":"UP"}`

## Frontend
En `src/app/core/config/api.config.ts` pon la URL de tu servicio:
`https://<tu-servicio>.onrender.com/api`
