package com.sis414.sis_414_parcial.dto;

import java.time.LocalDate;
public record TransferenciaDto(
    Long id,
    String codigo,
    LocalDate feult,
    String usuar,
    Long unidadId,
    Long activoId,
    Long oficinaId,
    Long responsableId,
    Long unidadInicialId
) {}
