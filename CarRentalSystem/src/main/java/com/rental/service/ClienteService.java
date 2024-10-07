package com.rental.service;

import com.rental.dao.ClienteDAO;
import com.rental.model.Cliente;

public class ClienteService {

    private ClienteDAO clienteDAO;

    // Construtor da classe ClienteService
    public ClienteService() {
        // Inicializa a instância do DAO que será usada para comunicação com o banco de dados
        this.clienteDAO = new ClienteDAO();
    }

    // Método para cadastrar um cliente
    public boolean cadastrarCliente(Cliente cliente) {
        try {
            // Valida o cliente antes de realizar a inserção
            if (validarCliente(cliente)) {
                clienteDAO.adicionarCliente(cliente); // Adiciona o cliente no banco de dados
                System.out.println("Cliente cadastrado com sucesso!");
                return true;
            }
        } catch (Exception e) {
            // Tratamento de exceções com mensagens úteis ao desenvolvedor
            System.err.println("Erro ao cadastrar cliente: " + e.getMessage());
        }
        return false;
    }

    // Método para validar as informações do cliente
    private boolean validarCliente(Cliente cliente) {
        // Valida se o nome não é nulo ou vazio
        if (cliente.getNome() == null || cliente.getNome().isEmpty()) {
            System.out.println("Nome inválido.");
            return false;
        }

        // Valida se o email não é nulo ou vazio
        if (cliente.getEmail() == null || cliente.getEmail().isEmpty()) {
            System.out.println("Email inválido.");
            return false;
        }

        // Valida se o celular não é nulo ou vazio
        if (cliente.getCelular() == null || cliente.getCelular().isEmpty()) {
            System.out.println("Celular inválido.");
            return false;
        }

        // Verifica outros campos necessários (endereço, cidade, estado)
        if (cliente.getEndereco() == null || cliente.getEndereco().isEmpty()) {
            System.out.println("Endereço inválido.");
            return false;
        }

        if (cliente.getCidade() == null || cliente.getCidade().isEmpty()) {
            System.out.println("Cidade inválida.");
            return false;
        }

        if (cliente.getEstado() == null || cliente.getEstado().length() != 2) {
            System.out.println("Estado inválido.");
            return false;
        }

        return true; // Se todos os campos forem válidos, retorna true
    }

    // Método para buscar um cliente por ID
    public Cliente buscarClientePorId(int id) {
        try {
            // Busca o cliente no banco de dados usando o DAO
            Cliente cliente = clienteDAO.buscarClientePorId(id);
            if (cliente != null) {
                return cliente;
            } else {
                System.out.println("Cliente não encontrado.");
            }
        } catch (Exception e) {
            // Tratamento de exceções
            System.err.println("Erro ao buscar cliente: " + e.getMessage());
        }
        return null; // Retorna null caso ocorra algum erro ou o cliente não seja encontrado
    }

    // Método para atualizar um cliente existente
    public boolean atualizarCliente(Cliente cliente) {
        try {
            // Verifica se o cliente passado é válido
            if (validarCliente(cliente)) {
                clienteDAO.atualizarCliente(cliente); // Atualiza o cliente no banco de dados
                System.out.println("Cliente atualizado com sucesso!");
                return true;
            }
        } catch (Exception e) {
            // Tratamento de exceções
            System.err.println("Erro ao atualizar cliente: " + e.getMessage());
        }
        return false;
    }

    // Método para remover um cliente pelo ID
    public boolean removerCliente(int id) {
        try {
            // Verifica se o cliente com o ID fornecido existe
            Cliente cliente = clienteDAO.buscarClientePorId(id);
            if (cliente != null) {
                clienteDAO.removerCliente(id); // Deleta o cliente do banco de dados
                System.out.println("Cliente deletado com sucesso!");
                return true;
            } else {
                System.out.println("Cliente não encontrado.");
            }
        } catch (Exception e) {
            // Tratamento de exceções
            System.err.println("Erro ao remover cliente: " + e.getMessage());
        }
        return false;
    }

    // Outros métodos relacionados ao Cliente podem ser adicionados aqui
}
