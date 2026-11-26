package com.vsiaf.examen.controller;

import com.vsiaf.examen.dto.RespaldoDto;
import com.vsiaf.examen.entity.Respaldo;
import com.vsiaf.examen.service.RespaldoService;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/respaldo")
public class RespaldoController {

    private final RespaldoService service;

    public RespaldoController(RespaldoService service) {
        this.service = service;
    }

    /** GET /api/respaldo */
    @GetMapping
    public List<RespaldoDto> listar() {
        return service.listar();
    }

    /** POST /api/respaldo -> genera un respaldo nuevo */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RespaldoDto crear() {
        return service.crear();
    }

    /** GET /api/respaldo/{id}/descargar -> archivo .sql */
    @GetMapping("/{id}/descargar")
    public ResponseEntity<byte[]> descargar(@PathVariable("id") Long id) {
        Respaldo r = service.obtener(id);
        byte[] data = r.getContenido().getBytes(StandardCharsets.UTF_8);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        ContentDisposition.attachment().filename(r.getNomrespaldo()).build().toString())
                .contentType(MediaType.parseMediaType("application/sql;charset=UTF-8"))
                .contentLength(data.length)
                .body(data);
    }

    /** DELETE /api/respaldo/{id} */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable("id") Long id) {
        service.eliminar(id);
    }
}
