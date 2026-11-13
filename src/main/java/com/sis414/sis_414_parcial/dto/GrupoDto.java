package com.sis414.sis_414_parcial.dto;

import java.time.LocalDate;
public record GrupoDto(
    Long id,
    Integer codCont,
    String nombre,
    Integer vidaUtil,
    String observ,
    Boolean depreciar,
    Boolean actualizar,
    LocalDate feult,
    String usuar
) {}
