package com.rental.service;

import com.rental.dao.LocacaoDAO;
import com.rental.model.Locacao;

public class LocacaoService {

    private LocacaoDAO locacaoDAO;

    // Construtor da classe LocacaoService
    public LocacaoService() {
        // Inicializa o DAO que será utilizado para acessar o banco de dados
        this.locacaoDAO = new LocacaoDAO();
    }

    // Método para realizar uma locação
    public boolean realizarLocacao(Locacao locacao) {
        try {
            // Valida a locação antes de adicioná-la no banco de dados
            if (validarLocacao(locacao)) {
                locacaoDAO.adicionarLocacao(locacao); // Adiciona a locação no banco de dados
                System.out.println("Locação realizada com sucesso!");
                return true;
            }
        } catch (Exception e) {
            // Tratamento de exceções, captura erros que possam ocorrer durante a locação
            System.err.println("Erro ao realizar locação: " + e.getMessage());
        }
        return false;
    }

    // Método para validar as informações da locação
    private boolean validarLocacao(Locacao locacao) {
        // Verifica se o ID do cliente é válido
        if (locacao.getIdCliente() <= 0) {
            System.out.println("ID de Cliente inválido.");
            return false;
        }

        // Verifica se o ID do carro é válido
        if (locacao.getIdCarro() <= 0) {
            System.out.println("ID de Carro inválido.");
            return false;
        }

        // Verifica se a data de início é válida (nula ou no passado não são permitidas)
        if (locacao.getDataInicio() == null || locacao.getDataInicio().isBefore(java.time.LocalDate.now())) {
            System.out.println("Data de início inválida.");
            return false;
        }

        // Verifica se a data de término é válida e após a data de início
        if (locacao.getDataFim() == null || locacao.getDataFim().isBefore(locacao.getDataInicio())) {
            System.out.println("Data de término inválida.");
            return false;
        }

        // Verifica se o valor total é maior que zero
        if (locacao.getValorTotal() <= 0) {
            System.out.println("Valor total inválido.");
            return false;
        }

        // Todas as validações passaram
        return true;
    }

    // Método para buscar uma locação pelo ID
    public Locacao buscarLocacaoPorId(int id) {
        try {
            // Busca a locação no banco de dados pelo ID fornecido
            Locacao locacao = locacaoDAO.buscarLocacaoPorId(id);
            if (locacao != null) {
                return locacao;
            } else {
                System.out.println("Locação não encontrada.");
            }
        } catch (Exception e) {
            // Tratamento de exceções ao buscar locação
            System.err.println("Erro ao buscar locação: " + e.getMessage());
        }
        return null;
    }

    // Método para cancelar uma locação (exemplo de funcionalidade adicional)
    public boolean cancelarLocacao(int id) {
        try {
            // Verifica se a locação existe antes de tentar cancelar
            Locacao locacao = locacaoDAO.buscarLocacaoPorId(id);
            if (locacao != null) {
                locacaoDAO.removerLocacao(id); // Remove a locação do banco de dados
                System.out.println("Locação cancelada com sucesso!");
                return true;
            } else {
                System.out.println("Locação não encontrada.");
            }
        } catch (Exception e) {
            // Tratamento de exceções ao cancelar a locação
            System.err.println("Erro ao cancelar locação: " + e.getMessage());
        }
        return false;
    }

    // Outros métodos relacionados à Locação podem ser adicionados aqui
}
