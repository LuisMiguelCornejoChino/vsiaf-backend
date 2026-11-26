package com.vsiaf.examen.service;

import com.vsiaf.examen.dto.EstadoRequest;
import com.vsiaf.examen.entity.Estado;
import com.vsiaf.examen.repository.EstadoRepository;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class EstadoService {

    private final EstadoRepository repository;

    public EstadoService(EstadoRepository repository) {
        this.repository = repository;
    }

    public List<Estado> listar() {
        return repository.findAll(Sort.by("codestado"));
    }

    public Estado obtener(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Estado no encontrado"));
    }

    public Estado crear(EstadoRequest request) {
        Estado e = new Estado();
        e.setNomestado(request.nomestado().trim());
        return repository.save(e);
    }

    public Estado actualizar(Long id, EstadoRequest request) {
        Estado e = obtener(id);
        e.setNomestado(request.nomestado().trim());
        return repository.save(e);
    }

    public void eliminar(Long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Estado no encontrado");
        }
        repository.deleteById(id);
    }
}
