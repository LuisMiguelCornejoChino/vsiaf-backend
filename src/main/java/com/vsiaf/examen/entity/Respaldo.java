package com.vsiaf.examen.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Instant;

@Entity
@Table(name = "respaldo")
public class Respaldo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codrespaldo;

    @Column(nullable = false)
    private String nomrespaldo;

    @Column(nullable = false)
    private Instant fecrespaldo;

    /** Tamaño del script en bytes. */
    @Column(nullable = false)
    private Long tamrespaldo;

    /** Script SQL completo. Se guarda en la BD porque el disco de Render es efímero. */
    @Column(nullable = false, columnDefinition = "text")
    private String contenido;

    public Long getCodrespaldo() { return codrespaldo; }
    public void setCodrespaldo(Long codrespaldo) { this.codrespaldo = codrespaldo; }

    public String getNomrespaldo() { return nomrespaldo; }
    public void setNomrespaldo(String nomrespaldo) { this.nomrespaldo = nomrespaldo; }

    public Instant getFecrespaldo() { return fecrespaldo; }
    public void setFecrespaldo(Instant fecrespaldo) { this.fecrespaldo = fecrespaldo; }

    public Long getTamrespaldo() { return tamrespaldo; }
    public void setTamrespaldo(Long tamrespaldo) { this.tamrespaldo = tamrespaldo; }

    public String getContenido() { return contenido; }
    public void setContenido(String contenido) { this.contenido = contenido; }
}
