package com.vsiaf.examen.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "estado")
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codestado;

    @Column(nullable = false)
    private String nomestado;

    public Long getCodestado() { return codestado; }
    public void setCodestado(Long codestado) { this.codestado = codestado; }

    public String getNomestado() { return nomestado; }
    public void setNomestado(String nomestado) { this.nomestado = nomestado; }
}
