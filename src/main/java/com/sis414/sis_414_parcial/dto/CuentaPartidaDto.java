package com.sis414.sis_414_parcial.dto;

public record CuentaPartidaDto(
    Long id,
    String partida,
    Integer gestion,
    Long grupoId
) {}
