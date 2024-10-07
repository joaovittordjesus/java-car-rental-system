package com.rental.model;

public class Carro {
    private int idCarro;
    private int numeroAgencia;
    private String placa;
    private String modelo;
    private int ano;
    private String tipo;
    private String disponibilidade;
    private double valorTotal;

    // Construtor
    public Carro(int idCarro, int numeroAgencia, String placa, String modelo, int ano, String tipo, String disponibilidade, double valorTotal) {
        this.idCarro = idCarro;
        this.numeroAgencia = numeroAgencia;
        this.placa = placa;
        this.modelo = modelo;
        this.ano = ano;
        this.tipo = tipo;
        this.disponibilidade = disponibilidade;
        this.valorTotal = valorTotal;
    }

    // Getters e Setters
    public int getIdCarro() {
        return idCarro;
    }

    public void setIdCarro(int idCarro) {
        this.idCarro = idCarro;
    }

    public int getNumeroAgencia() {
        return numeroAgencia;
    }

    public void setNumeroAgencia(int numeroAgencia) {
        this.numeroAgencia = numeroAgencia;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(String disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }
}
