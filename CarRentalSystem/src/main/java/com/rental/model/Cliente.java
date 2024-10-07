package com.rental.model;

public class Cliente {
    private int idCliente;
    private String email;
    private String celular;
    private String nome;
    private String sobrenome;
    private String endereco;
    private String cidade;
    private String estado;

    // Construtor
    public Cliente(int idCliente, String email, String celular, String nome, String sobrenome, String endereco, String cidade, String estado) {
        this.idCliente = idCliente;
        this.email = email;
        this.celular = celular;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.endereco = endereco;
        this.cidade = cidade;
        this.estado = estado;
    }

    // Getters e Setters
    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
