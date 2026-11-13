package com.sis414.sis_414_parcial.dto;

import java.math.BigDecimal;
import java.util.List;

public record ReporteGeneradoDto(
        FechaCalculoDto calculadoA, BigDecimal ufv, Integer cantidad,
        BigDecimal totalCosto, BigDecimal totalValorActual, BigDecimal totalDepAcumulada, BigDecimal totalValorNeto,
        List<FilaReporteDto> filas
) {}
