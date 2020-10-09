package com.eventosapp.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
public class Evento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long codigo;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String local;

    @Column(nullable = false)
    private String data;

    @Column(nullable = false)
    private String horario;

    @OneToMany
    private List<Convidado> convidados;

}
