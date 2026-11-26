package com.vsiaf.examen.repository;

import com.vsiaf.examen.dto.RespaldoDto;
import com.vsiaf.examen.entity.Respaldo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RespaldoRepository extends JpaRepository<Respaldo, Long> {

    /** Lista sin cargar la columna "contenido". */
    @Query("select new com.vsiaf.examen.dto.RespaldoDto(r.codrespaldo, r.nomrespaldo, r.fecrespaldo, r.tamrespaldo) "
            + "from Respaldo r order by r.fecrespaldo desc")
    List<RespaldoDto> listarResumen();
}
