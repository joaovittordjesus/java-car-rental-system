package main.java.com.rental.ui;

import com.rental.model.Cliente;
import com.rental.model.Carro;
import com.rental.model.Locacao;
import com.rental.service.ClienteService;
import com.rental.service.CarroService;
import com.rental.service.LocacaoService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;

public class MainUI extends JFrame {
    private ClienteService clienteService;
    private CarroService carroService;
    private LocacaoService locacaoService;

    // Campos para os formulários
    private JTextField nomeField, sobrenomeField, emailField, celularField, enderecoField, cidadeField, estadoField;
    private JTextField placaField, modeloField, anoField;
    private JTextField valorTotalField;

    public MainUI() {
        clienteService = new ClienteService();
        carroService = new CarroService();
        locacaoService = new LocacaoService();

        setTitle("Locadora de Carros");
        setSize(500, 600);  // Aumentado o tamanho para melhor acomodar todos os painéis
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(7, 1));

        // Painel de clientes
        JPanel clientePanel = new JPanel(new GridLayout(8, 2));
        clientePanel.setBorder(BorderFactory.createTitledBorder("Cadastro de Cliente"));

        nomeField = new JTextField();
        sobrenomeField = new JTextField();
        emailField = new JTextField();
        celularField = new JTextField();
        enderecoField = new JTextField();
        cidadeField = new JTextField();
        estadoField = new JTextField();

        clientePanel.add(new JLabel("Nome:"));
        clientePanel.add(nomeField);
        clientePanel.add(new JLabel("Sobrenome:"));
        clientePanel.add(sobrenomeField);
        clientePanel.add(new JLabel("Email:"));
        clientePanel.add(emailField);
        clientePanel.add(new JLabel("Celular:"));
        clientePanel.add(celularField);
        clientePanel.add(new JLabel("Endereço:"));
        clientePanel.add(enderecoField);
        clientePanel.add(new JLabel("Cidade:"));
        clientePanel.add(cidadeField);
        clientePanel.add(new JLabel("Estado:"));
        clientePanel.add(estadoField);

        JButton cadastrarClienteButton = new JButton("Cadastrar Cliente");
        clientePanel.add(cadastrarClienteButton);

        cadastrarClienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarCliente();
            }
        });

        // Painel de carros
        JPanel carroPanel = new JPanel(new GridLayout(4, 2));
        carroPanel.setBorder(BorderFactory.createTitledBorder("Cadastro de Carro"));

        placaField = new JTextField();
        modeloField = new JTextField();
        anoField = new JTextField();

        carroPanel.add(new JLabel("Placa:"));
        carroPanel.add(placaField);
        carroPanel.add(new JLabel("Modelo:"));
        carroPanel.add(modeloField);
        carroPanel.add(new JLabel("Ano:"));
        carroPanel.add(anoField);

        JButton cadastrarCarroButton = new JButton("Cadastrar Carro");
        carroPanel.add(cadastrarCarroButton);

        cadastrarCarroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarCarro();
            }
        });

        // Painel de locação
        JPanel locacaoPanel = new JPanel(new GridLayout(2, 2));
        locacaoPanel.setBorder(BorderFactory.createTitledBorder("Realizar Locação"));

        valorTotalField = new JTextField();

        locacaoPanel.add(new JLabel("Valor Total:"));
        locacaoPanel.add(valorTotalField);

        JButton realizarLocacaoButton = new JButton("Realizar Locação");
        locacaoPanel.add(realizarLocacaoButton);

        realizarLocacaoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarLocacao();
            }
        });

        // Adiciona os painéis à janela principal
        add(clientePanel);
        add(carroPanel);
        add(locacaoPanel);
    }

    // Método para cadastrar um cliente
    private void cadastrarCliente() {
        try {
            String nome = nomeField.getText();
            String sobrenome = sobrenomeField.getText();
            String email = emailField.getText();
            String celular = celularField.getText();
            String endereco = enderecoField.getText();
            String cidade = cidadeField.getText();
            String estado = estadoField.getText();

            Cliente cliente = new Cliente(0, email, celular, nome, sobrenome, endereco, cidade, estado);
            boolean sucesso = clienteService.cadastrarCliente(cliente);

            if (sucesso) {
                JOptionPane.showMessageDialog(this, "Cliente cadastrado com sucesso!");
                limparCamposCliente();
            } else {
                JOptionPane.showMessageDialog(this, "Falha ao cadastrar cliente.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar cliente: " + e.getMessage());
        }
    }

    // Método para cadastrar um carro
    private void cadastrarCarro() {
        try {
            String placa = placaField.getText();
            String modelo = modeloField.getText();
            int ano = Integer.parseInt(anoField.getText());

            // Usando LocalDate para representar a data de fabricação
            LocalDate dataFabricacao = LocalDate.of(ano, 1, 1);

            Carro carro = new Carro(0, 0, placa, modelo, Date.from(dataFabricacao.atStartOfDay(ZoneId.systemDefault()).toInstant()), "S", "DISPONIVEL", 0, 0);
            boolean sucesso = carroService.cadastrarCarro(carro);

            if (sucesso) {
                JOptionPane.showMessageDialog(this, "Carro cadastrado com sucesso!");
                limparCamposCarro();
            } else {
                JOptionPane.showMessageDialog(this, "Falha ao cadastrar carro.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar carro: Ano inválido.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar carro: " + e.getMessage());
        }
    }

    // Método para realizar locação
    private void realizarLocacao() {
        try {
            double valorTotal = Double.parseDouble(valorTotalField.getText());

            Locacao locacao = new Locacao(0, 0, 0, new Date(), new Date(), valorTotal, 0);
            boolean sucesso = locacaoService.realizarLocacao(locacao);

            if (sucesso) {
                JOptionPane.showMessageDialog(this, "Locação realizada com sucesso!");
                limparCamposLocacao();
            } else {
                JOptionPane.showMessageDialog(this, "Falha ao realizar locação.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Erro ao realizar locação: Valor total inválido.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao realizar locação: " + e.getMessage());
        }
    }

    // Métodos para limpar os campos de entrada
    private void limparCamposCliente() {
        nomeField.setText("");
        sobrenomeField.setText("");
        emailField.setText("");
        celularField.setText("");
        enderecoField.setText("");
        cidadeField.setText("");
        estadoField.setText("");
    }

    private void limparCamposCarro() {
        placaField.setText("");
        modeloField.setText("");
        anoField.setText("");
    }

    private void limparCamposLocacao() {
        valorTotalField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainUI mainUI = new MainUI();
            mainUI.setVisible(true);
        });
    }
}
