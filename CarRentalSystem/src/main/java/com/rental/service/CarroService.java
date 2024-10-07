package com.rental.service;

import com.rental.dao.CarroDAO;
import com.rental.model.Carro;

public class CarroService {

    private CarroDAO carroDAO;

    // Construtor da classe CarroService
    public CarroService() {
        // Inicializa a instância do DAO que será usada para comunicação com o banco de dados
        this.carroDAO = new CarroDAO();
    }

    // Método para cadastrar um carro
    public boolean cadastrarCarro(Carro carro) {
        try {
            // Valida os dados do carro antes de realizar a inserção
            if (validarCarro(carro)) {
                carroDAO.adicionarCarro(carro); // Adiciona o carro no banco de dados
                System.out.println("Carro cadastrado com sucesso!");
                return true;
            }
        } catch (Exception e) {
            // Tratamento de exceções com mensagens úteis ao desenvolvedor
            System.err.println("Erro ao cadastrar carro: " + e.getMessage());
        }
        return false;
    }

    // Método para validar as informações do carro
    private boolean validarCarro(Carro carro) {
        // Valida se a placa não é nula ou vazia
        if (carro.getPlaca() == null || carro.getPlaca().isEmpty()) {
            System.out.println("Placa inválida.");
            return false;
        }

        // Valida se o modelo não é nulo ou vazio
        if (carro.getModelo() == null || carro.getModelo().isEmpty()) {
            System.out.println("Modelo inválido.");
            return false;
        }

        /*// Valida o ano do carro (assumindo que o campo ano é uma String, verificar se está no formato correto)
        if (carro.getAno() == null || carro.getAno().toString().isEmpty()) {
            System.out.println("Ano inválido.");
            return false;
        }*/
        // Valida o ano do carro (assumindo que o campo ano é um inteiro)
        if (carro.getAno() <= 0) {
            System.out.println("Ano inválido.");
            return false;
        }

        // Valida a disponibilidade (certifica que o status do carro está correto)
        if (carro.getDisponibilidade() == null || carro.getDisponibilidade().isEmpty()) {
            System.out.println("Disponibilidade inválida.");
            return false;
        }

        return true; // Retorna true se todas as validações passarem
    }

    // Método para buscar um carro por ID
    public Carro buscarCarroPorId(int id) {
        try {
            // Busca o carro no banco de dados usando o DAO
            Carro carro = carroDAO.buscarCarroPorId(id);
            if (carro != null) {
                return carro;
            } else {
                System.out.println("Carro não encontrado.");
            }
        } catch (Exception e) {
            // Tratamento de exceções
            System.err.println("Erro ao buscar carro: " + e.getMessage());
        }
        return null; // Retorna null se o carro não for encontrado ou houver erro
    }

    // Método para atualizar as informações de um carro existente
    public boolean atualizarCarro(Carro carro) {
        try {
            // Verifica se o carro passado é válido
            if (validarCarro(carro)) {
                carroDAO.atualizarCarro(carro); // Atualiza o carro no banco de dados
                System.out.println("Carro atualizado com sucesso!");
                return true;
            }
        } catch (Exception e) {
            // Tratamento de exceções
            System.err.println("Erro ao atualizar carro: " + e.getMessage());
        }
        return false;
    }

    // Método para remover um carro pelo ID
    public boolean removerCarro(int id) {
        try {
            // Verifica se o carro com o ID fornecido existe
            Carro carro = carroDAO.buscarCarroPorId(id);
            if (carro != null) {
                carroDAO.removerCarro(id); // Deleta o carro do banco de dados
                System.out.println("Carro deletado com sucesso!");
                return true;
            } else {
                System.out.println("Carro não encontrado.");
            }
        } catch (Exception e) {
            // Tratamento de exceções
            System.err.println("Erro ao remover carro: " + e.getMessage());
        }
        return false;
    }

    // Outros métodos relacionados ao Carro podem ser adicionados aqui
}
