package com.rental.model;

import java.util.Date;

public class Locacao {
    int idLocacao;
    private int idCliente;
    private int numeroAgencia;
    private Date dataLocacao;
    private Date dataDevolucao;
    private double valorTotal;

    // Construtor
    public Locacao(int idLocacao, int idCliente, int numeroAgencia, Date dataLocacao, Date dataDevolucao, double valorTotal) {
        this.idLocacao = idLocacao;
        this.idCliente = idCliente;
        this.numeroAgencia = numeroAgencia;
        this.dataLocacao = dataLocacao;
        this.dataDevolucao = dataDevolucao;
        this.valorTotal = valorTotal;
    }

    // Getters e Setters
    public int getIdLocacao() {
        return idLocacao;
    }

    public void setIdLocacao(int idLocacao) {
        this.idLocacao = idLocacao;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getNumeroAgencia() {
        return numeroAgencia;
    }

    public void setNumeroAgencia(int numeroAgencia) {
        this.numeroAgencia = numeroAgencia;
    }

    public Date getDataLocacao() {
        return dataLocacao;
    }

    public void setDataLocacao(Date dataLocacao) {
        this.dataLocacao = dataLocacao;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }
}
