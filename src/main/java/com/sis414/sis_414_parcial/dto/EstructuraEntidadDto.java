package com.sis414.sis_414_parcial.dto;

public record EstructuraEntidadDto(
    Long id,
    Integer gestion,
    Integer sectorEnt,
    Integer subsecEnt,
    Integer areaEnt,
    Integer subareaEnt,
    Integer nivelInst,
    String desEstruc,
    String siglaEstru,
    String apropiable
) {}
