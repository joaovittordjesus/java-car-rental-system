package com.rental.service;

import com.rental.dao.LocacaoDAO;
import com.rental.model.Locacao;
import java.util.Date;
import java.util.List;

public class LocacaoService {

    private LocacaoDAO locacaoDAO;

    // Construtor da classe LocacaoService
    public LocacaoService() {
        this.locacaoDAO = new LocacaoDAO(); // Inicializa o DAO que será utilizado para acessar o banco de dados
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

        // Verifica se a data de locação é válida (nula ou no passado não são permitidas)
        if (locacao.getDataLocacao() == null || locacao.getDataLocacao().before(new Date())) {
            System.out.println("Data de locação inválida.");
            return false;
        }

        // Verifica se a data de devolução é válida e após a data de locação
        if (locacao.getDataDevolucao() == null || locacao.getDataDevolucao().before(locacao.getDataLocacao())) {
            System.out.println("Data de devolução inválida.");
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

    // Método para cancelar uma locação
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

    // Método para atualizar uma locação
    public boolean atualizarLocacao(Locacao locacao) {
        try {
            if (validarLocacao(locacao)) {
                locacaoDAO.atualizarLocacao(locacao); // Atualiza a locação no banco de dados
                System.out.println("Locação atualizada com sucesso!");
                return true;
            }
        } catch (Exception e) {
            // Tratamento de exceções ao atualizar locação
            System.err.println("Erro ao atualizar locação: " + e.getMessage());
        }
        return false;
    }

    // Método para listar todas as locações
    public List<Locacao> listarLocacoes() {
        try {
            List<Locacao> locacoes = locacaoDAO.listarLocacoes(); // Busca todas as locações no banco de dados
            return locacoes;
        } catch (Exception e) {
            // Tratamento de exceções ao listar locações
            System.err.println("Erro ao listar locações: " + e.getMessage());
        }
        return null;
    }

    // Outros métodos relacionados à Locação podem ser adicionados aqui
}
