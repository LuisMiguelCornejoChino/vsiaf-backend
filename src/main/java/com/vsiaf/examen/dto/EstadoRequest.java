package com.vsiaf.examen.dto;

import jakarta.validation.constraints.NotBlank;

/** Cuerpo para crear / modificar un Estado (el id lo genera la BD). */
public record EstadoRequest(@NotBlank(message = "El nombre es obligatorio") String nomestado) {
}
