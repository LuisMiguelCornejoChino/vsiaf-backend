package com.sis414.sis_414_parcial.dto;

public record UnidadAdminDto(
    Long id,
    String unidad,
    String descrip,
    String ciudad,
    Integer estadoUni,
    Long entidadId
) {}
