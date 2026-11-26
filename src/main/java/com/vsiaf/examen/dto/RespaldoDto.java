package com.vsiaf.examen.dto;

import java.time.Instant;

/** Resumen de un respaldo (sin el contenido, que puede ser grande). */
public record RespaldoDto(Long codrespaldo, String nomrespaldo, Instant fecrespaldo, Long tamrespaldo) {
}
