package com.backendattornatus.apigerenciarpessoas.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;

import java.util.List;

@Entity
@Table(name = "pessoa")
public class PessoaModels {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataNasc;



    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "pessoa_id")
    private List<EnderecoModels> enderecos;

    public PessoaModels() {

    }


    public PessoaModels(Long id, String nome, LocalDate dataNasc, List<EnderecoModels> enderecos) {
        this.id = id;
        this.nome = nome;
        this.dataNasc = dataNasc;
        this.enderecos = enderecos;
    }
    public PessoaModels( String nome, LocalDate dataNasc, List<EnderecoModels> enderecos) {

        this.nome = nome;
        this.dataNasc = dataNasc;
        this.enderecos = enderecos;
    }

    public PessoaModels(String nome, LocalDate dataNasc) {
        this.nome = nome;
        this.dataNasc = dataNasc;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(LocalDate dataNasc) {
        this.dataNasc = dataNasc;
    }


    public List<EnderecoModels> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<EnderecoModels> enderecos) {
        this.enderecos = enderecos;
    }
}
