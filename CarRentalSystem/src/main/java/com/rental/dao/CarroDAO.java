package com.rental.dao;


import com.rental.model.Carro;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;


public class CarroDAO {
    private Connection connection;

    public CarroDAO() {
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

    // Método para adicionar um carro
    public void adicionarCarro(Carro carro) {
        String sql = "INSERT INTO carros (numero_agencia, placa, modelo, ano, tipo, disponibilidade, valor_total) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, carro.getNumeroAgencia());
            stmt.setString(2, carro.getPlaca());
            stmt.setString(3, carro.getModelo());
            stmt.setInt(4, carro.getAno());
            stmt.setString(5, carro.getTipo());
            stmt.setString(6, carro.getDisponibilidade());
            stmt.setDouble(7, carro.getValorTotal());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para listar todos os carros
    public List<Carro> listarCarros() {
        List<Carro> carros = new ArrayList<>();
        String sql = "SELECT * FROM carros";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Carro carro = new Carro(
                        rs.getInt("id_carro"),
                        rs.getInt("numero_agencia"),
                        rs.getString("placa"),
                        rs.getString("modelo"),
                        rs.getInt("ano"),
                        rs.getString("tipo"),
                        rs.getString("disponibilidade"),
                        rs.getDouble("valor_total")
                );
                carros.add(carro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carros;
    }
    
    // Método para buscar um carro por ID
public Carro buscarCarroPorId(int idCarro) {
    String sql = "SELECT * FROM carros WHERE id_carro = ?";
    Carro carro = null;
    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
        stmt.setInt(1, idCarro);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                carro = new Carro(
                        rs.getInt("id_carro"),
                        rs.getInt("numero_agencia"),
                        rs.getString("placa"),
                        rs.getString("modelo"),
                        rs.getInt("ano"),
                        rs.getString("tipo"),
                        rs.getString("disponibilidade"),
                        rs.getDouble("valor_total")
                );
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return carro; // Retorna null se não encontrar
}


    // Método para atualizar um carro
    public void atualizarCarro(Carro carro) {
        String sql = "UPDATE carros SET numero_agencia = ?, placa = ?, modelo = ?, ano = ?, tipo = ?, disponibilidade = ?, valor_total = ? WHERE id_carro = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, carro.getNumeroAgencia());
            stmt.setString(2, carro.getPlaca());
            stmt.setString(3, carro.getModelo());
            stmt.setInt(4, carro.getAno());
            stmt.setString(5, carro.getTipo());
            stmt.setString(6, carro.getDisponibilidade());
            stmt.setDouble(7, carro.getValorTotal());
            stmt.setInt(8, carro.getIdCarro());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para remover um carro
    public void removerCarro(int idCarro) {
        String sql = "DELETE FROM carros WHERE id_carro = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idCarro);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
