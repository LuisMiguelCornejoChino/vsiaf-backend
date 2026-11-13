package com.sis414.sis_414_parcial.dto;

public record ReporteDto(
    Long id,
    String reportName,
    String contents,
    String modType,
    String notes
) {}
