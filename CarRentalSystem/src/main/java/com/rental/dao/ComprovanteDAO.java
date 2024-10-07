package com.rental.dao;

import com.rental.model.Comprovante;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;

public class ComprovanteDAO {
    private Connection connection;

    public ComprovanteDAO() {
        this.connection = connect();
    }

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
    
    //Verifica se o idLocação existe
    public boolean verificarIdLocacao(int idLocacao) {
        String sql = "SELECT COUNT(*) FROM LOCACAO WHERE id_locacao = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idLocacao);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Retorna true se existir pelo menos um registro
            }
        } catch (SQLException e) {
            System.err.println("Erro ao verificar idLocacao: " + e.getMessage());
        }
        return false; // Retorna false se houver algum erro ou se não existir
    }

    public void adicionarComprovante(Comprovante comprovante) {
        String sql = "INSERT INTO comprovantes (comprovante, id_locacao) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, comprovante.getComprovante());
            stmt.setInt(2, comprovante.getIdLocacao()); // Adiciona o idLocacao
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Comprovante> listarComprovantes() {
        List<Comprovante> comprovantes = new ArrayList<>();
        String sql = "SELECT * FROM comprovantes";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Comprovante comprovante = new Comprovante(
                        rs.getInt("id_comprovante"),
                        rs.getString("comprovante"),
                        rs.getInt("id_locacao") // Adiciona idLocacao na construção
                );
                comprovantes.add(comprovante);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comprovantes;
    }

    public Comprovante buscarComprovantePorId(int idComprovante) {
        String sql = "SELECT * FROM comprovantes WHERE id_comprovante = ?";
        Comprovante comprovante = null;
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idComprovante);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    comprovante = new Comprovante(
                            rs.getInt("id_comprovante"),
                            rs.getString("comprovante"),
                            rs.getInt("id_locacao") // Adiciona idLocacao na construção
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comprovante;
    }

    public void removerComprovante(int idComprovante) {
        String sql = "DELETE FROM comprovantes WHERE id_comprovante = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idComprovante);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
