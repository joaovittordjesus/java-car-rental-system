package com.rental.model;

public class Comprovante {
    private int idComprovante;
    private String comprovante;
    private int idLocacao;  // ID da locação associada

    // Construtor
    public Comprovante(int idComprovante, String comprovante, int idLocacao) {
        this.idComprovante = idComprovante;
        this.comprovante = comprovante;
        this.idLocacao = idLocacao; // Inicializa idLocacao
    }

    // Getters e Setters
    public int getIdComprovante() {
        return idComprovante;
    }

    public void setIdComprovante(int idComprovante) {
        this.idComprovante = idComprovante;
    }

    public String getComprovante() {
        return comprovante;
    }

    public void setComprovante(String comprovante) {
        this.comprovante = comprovante;
    }

    public int getIdLocacao() {
        return idLocacao;
    }

    public void setIdLocacao(int idLocacao) {
        this.idLocacao = idLocacao;
    }
}
