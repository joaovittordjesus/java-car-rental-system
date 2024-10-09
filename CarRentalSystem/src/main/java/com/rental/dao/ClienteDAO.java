package com.rental.dao;

import com.rental.model.Cliente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;

public class ClienteDAO {
    private Connection connection;

    public ClienteDAO() {
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

    // Método para adicionar um cliente
    public void adicionarCliente(Cliente cliente) {
        String sql = "INSERT INTO clientes (email, celular, nome, sobrenome, endereco, cidade, estado) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cliente.getEmail());
            stmt.setString(2, cliente.getCelular());
            stmt.setString(3, cliente.getNome());
            stmt.setString(4, cliente.getSobrenome());
            stmt.setString(5, cliente.getEndereco());
            stmt.setString(6, cliente.getCidade());
            stmt.setString(7, cliente.getEstado());
            stmt.executeUpdate();
            connection.commit(); // Confirma a transação
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para listar todos os clientes
    public List<Cliente> listarClientes() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM clientes";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Cliente cliente = new Cliente(
                        rs.getInt("id_cliente"),
                        rs.getString("email"),
                        rs.getString("celular"),
                        rs.getString("nome"),
                        rs.getString("sobrenome"),
                        rs.getString("endereco"),
                        rs.getString("cidade"),
                        rs.getString("estado")
                );
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }

    // Método para buscar um cliente pelo ID
    public Cliente buscarClientePorId(int idCliente) {
        String sql = "SELECT * FROM clientes WHERE id_cliente = ?";
        Cliente cliente = null;
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idCliente);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    cliente = new Cliente(
                            rs.getInt("id_cliente"),
                            rs.getString("email"),
                            rs.getString("celular"),
                            rs.getString("nome"),
                            rs.getString("sobrenome"),
                            rs.getString("endereco"),
                            rs.getString("cidade"),
                            rs.getString("estado")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cliente;
    }

    // Método para atualizar um cliente
    public void atualizarCliente(Cliente cliente) {
        String sql = "UPDATE clientes SET email = ?, celular = ?, nome = ?, sobrenome = ?, endereco = ?, cidade = ?, estado = ? WHERE id_cliente = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cliente.getEmail());
            stmt.setString(2, cliente.getCelular());
            stmt.setString(3, cliente.getNome());
            stmt.setString(4, cliente.getSobrenome());
            stmt.setString(5, cliente.getEndereco());
            stmt.setString(6, cliente.getCidade());
            stmt.setString(7, cliente.getEstado());
            stmt.setInt(8, cliente.getIdCliente());
            stmt.executeUpdate();
            connection.commit(); // Confirma a transação
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para remover um cliente
    public void removerCliente(int idCliente) {
        String sql = "DELETE FROM clientes WHERE id_cliente = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idCliente);
            stmt.executeUpdate();
            connection.commit(); // Confirma a transação
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Fechar a conexão ao final da aplicação
    public void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
