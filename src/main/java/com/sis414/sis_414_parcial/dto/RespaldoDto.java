package com.sis414.sis_414_parcial.dto;

import java.time.LocalDate;
public record RespaldoDto(
    Long id,
    LocalDate dia,
    String hora,
    String archivo,
    String observ,
    String usuar,
    LocalDate feult
) {}
