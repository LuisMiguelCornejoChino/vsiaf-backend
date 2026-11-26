package com.vsiaf.examen.controller;

import com.vsiaf.examen.dto.EstadoRequest;
import com.vsiaf.examen.entity.Estado;
import com.vsiaf.examen.service.EstadoService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/estado")
public class EstadoController {

    private final EstadoService service;

    public EstadoController(EstadoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Estado> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Estado obtener(@PathVariable("id") Long id) {
        return service.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Estado crear(@Valid @RequestBody EstadoRequest request) {
        return service.crear(request);
    }

    @PutMapping("/{id}")
    public Estado actualizar(@PathVariable("id") Long id, @Valid @RequestBody EstadoRequest request) {
        return service.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable("id") Long id) {
        service.eliminar(id);
    }
}
