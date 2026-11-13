package com.sis414.sis_414_parcial.dto;

import java.util.List;

/** Pantalla OFICINA: oficina, estado (ACTIVO/INACTIVO), observacion y grilla de responsables. */
public record OficinaDetalleDto(Long id, Integer codigo, String nombre, String estado, String observacion, List<ResponsableFilaDto> responsables) {}
