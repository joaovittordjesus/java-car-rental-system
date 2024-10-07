package com.rental.service;

import com.rental.dao.ComprovanteDAO;
import com.rental.model.Comprovante;

public class ComprovanteService {
    private ComprovanteDAO comprovanteDAO;

    public ComprovanteService() {
        this.comprovanteDAO = new ComprovanteDAO();
    }

    public boolean registrarComprovante(Comprovante comprovante) {
        try {
            if (validarComprovante(comprovante)) {
                comprovanteDAO.adicionarComprovante(comprovante); // Agora inclui o idLocacao
                System.out.println("Comprovante registrado com sucesso!");
                return true;
            }
        } catch (Exception e) {
            System.err.println("Erro ao registrar comprovante: " + e.getMessage());
        }
        return false;
    }

    private boolean validarComprovante(Comprovante comprovante) {
        if (comprovante.getComprovante() == null || comprovante.getComprovante().isEmpty()) {
            System.out.println("Comprovante inválido.");
            return false;
        }

        if (comprovante.getIdLocacao() <= 0) {
            System.out.println("ID de Locação inválido.");
            return false;
        }

        // Verifique se o idLocacao realmente existe no banco de dados
        if (!comprovanteDAO.verificarIdLocacao(comprovante.getIdLocacao())) {
            System.out.println("ID de Locação não encontrado no banco de dados.");
            return false;
        }

        return true;
    }

    public Comprovante buscarComprovantePorId(int id) {
        try {
            Comprovante comprovante = comprovanteDAO.buscarComprovantePorId(id);
            if (comprovante != null) {
                return comprovante;
            } else {
                System.out.println("Comprovante não encontrado.");
            }
        } catch (Exception e) {
            System.err.println("Erro ao buscar comprovante: " + e.getMessage());
        }
        return null;
    }

    public boolean removerComprovante(int id) {
        try {
            Comprovante comprovante = comprovanteDAO.buscarComprovantePorId(id);
            if (comprovante != null) {
                comprovanteDAO.removerComprovante(id);
                System.out.println("Comprovante removido com sucesso!");
                return true;
            } else {
                System.out.println("Comprovante não encontrado.");
            }
        } catch (Exception e) {
            System.err.println("Erro ao remover comprovante: " + e.getMessage());
        }
        return false;
    }
}
