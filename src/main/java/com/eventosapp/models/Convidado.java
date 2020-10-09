package com.eventosapp.models;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
public class Convidado {

    @Id
    @Column(nullable = false)
    private String rg;

    @Column(nullable = false)
    private String nomeConvidado;

    @ManyToOne
    private Evento evento;

}
