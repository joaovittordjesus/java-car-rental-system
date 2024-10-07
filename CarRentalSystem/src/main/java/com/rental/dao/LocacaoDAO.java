package com.rental.dao;


import com.rental.model.Locacao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;

public class LocacaoDAO {
    private Connection connection;

    public LocacaoDAO() {
        this.connection = connect();
    }

    // Método para conectar ao banco de dados
    private Connection connect() {
        try {
            Properties properties = new Properties();
            try (InputStream input = getClass().getClassLoader().getResourceAsStream("database.properties")) {
                properties.load(input);
            }
            String url = properties.getProperty("url");
            String user = properties.getProperty("user");
            String password = properties.getProperty("password");
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Método para adicionar uma locação
    public void adicionarLocacao(Locacao locacao) {
        String sql = "INSERT INTO locacoes (id_cliente, numero_agencia, data_locacao, data_devolucao, valor_total) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, locacao.getIdCliente());
            stmt.setInt(2, locacao.getNumeroAgencia());
            stmt.setDate(3, new java.sql.Date(locacao.getDataLocacao().getTime()));
            stmt.setDate(4, new java.sql.Date(locacao.getDataDevolucao().getTime()));
            stmt.setDouble(5, locacao.getValorTotal());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para listar todas as locações
    public List<Locacao> listarLocacoes() {
        List<Locacao> locacoes = new ArrayList<>();
        String sql = "SELECT * FROM locacoes";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Locacao locacao = new Locacao(
                        rs.getInt("id_locacao"),
                        rs.getInt("id_cliente"),
                        rs.getInt("numero_agencia"),
                        rs.getDate("data_locacao"),
                        rs.getDate("data_devolucao"),
                        rs.getDouble("valor_total")
                );
                locacoes.add(locacao);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return locacoes;
    }

    // Método para atualizar uma locação
    public void atualizarLocacao(Locacao locacao) {
        String sql = "UPDATE locacoes SET id_cliente = ?, numero_agencia = ?, data_locacao = ?, data_devolucao = ?, valor_total = ? WHERE id_locacao = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, locacao.getIdCliente());
            stmt.setInt(2, locacao.getNumeroAgencia());
            stmt.setDate(3, new java.sql.Date(locacao.getDataLocacao().getTime()));
            stmt.setDate(4, new java.sql.Date(locacao.getDataDevolucao().getTime()));
            stmt.setDouble(5, locacao.getValorTotal());
            stmt.setInt(6, locacao.getIdLocacao());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para remover uma locação
    public void removerLocacao(int idLocacao) {
        String sql = "DELETE FROM locacoes WHERE id_locacao = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idLocacao);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
