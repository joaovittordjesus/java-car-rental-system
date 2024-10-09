package com.rental.ui;


import com.rental.model.Carro;
import com.rental.model.Cliente;
import com.rental.model.Locacao;
import com.rental.service.ClienteService;
import com.rental.service.CarroService;
import com.rental.service.LocacaoService;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class MainCLI {
    
    private ClienteService clienteService;
    private CarroService carroService;
    private LocacaoService locacaoService;
    private Scanner scanner;

    public MainCLI() {
        clienteService = new ClienteService();
        carroService = new CarroService();
        locacaoService = new LocacaoService();
        scanner = new Scanner(System.in);
    }

    public void iniciar() {
        while (true) {
            System.out.println("Escolha uma opção:");
            System.out.println("1 - Cadastrar Cliente");
            System.out.println("2 - Cadastrar Carro");
            System.out.println("3 - Realizar Locação");
            System.out.println("4 - Sair");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer do scanner

            switch (opcao) {
                case 1:
                    cadastrarCliente();
                    break;
                case 2:
                    cadastrarCarro();
                    break;
                case 3:
                    realizarLocacao();
                    break;
                case 4:
                    System.out.println("Saindo...");
                    return; // Sai do loop e encerra
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    // Método para cadastrar um cliente
    private void cadastrarCliente() {
        try {
            System.out.println("Digite o Nome:");
            String nome = scanner.nextLine();
            System.out.println("Digite o Sobrenome:");
            String sobrenome = scanner.nextLine();
            System.out.println("Digite o Email:");
            String email = scanner.nextLine();
            System.out.println("Digite o Celular:");
            String celular = scanner.nextLine();
            System.out.println("Digite o Endereço:");
            String endereco = scanner.nextLine();
            System.out.println("Digite a Cidade:");
            String cidade = scanner.nextLine();
            System.out.println("Digite o Estado:");
            String estado = scanner.nextLine();

            Cliente cliente = new Cliente(0, email, celular, nome, sobrenome, endereco, cidade, estado);
            boolean sucesso = clienteService.cadastrarCliente(cliente);

            if (sucesso) {
                System.out.println("Cliente cadastrado com sucesso!");
            } else {
                System.out.println("Falha ao cadastrar cliente.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar cliente: " + e.getMessage());
        }
    }

    // Método para cadastrar um carro
    private void cadastrarCarro() {
        try {
            System.out.println("Digite a Placa:");
            String placa = scanner.nextLine();
            System.out.println("Digite o Modelo:");
            String modelo = scanner.nextLine();
            System.out.println("Digite o Ano:");
            int ano = Integer.parseInt(scanner.nextLine());

            // Cria um carro usando o construtor correto
            Carro carro = new Carro(0, 0, placa, modelo, ano, "S", "DISPONIVEL", 0);
            boolean sucesso = carroService.cadastrarCarro(carro);

            if (sucesso) {
                System.out.println("Carro cadastrado com sucesso!");
            } else {
                System.out.println("Falha ao cadastrar carro.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Erro ao cadastrar carro: Ano inválido.");
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar carro: " + e.getMessage());
        }
    }

    // Método para realizar locação
    private void realizarLocacao() {
        try {
            System.out.println("Digite o ID do Cliente:");
            int idCliente = Integer.parseInt(scanner.nextLine());
            System.out.println("Digite o ID do Carro:");
            int idCarro = Integer.parseInt(scanner.nextLine());
            System.out.println("Digite o Valor Total:");
            double valorTotal = Double.parseDouble(scanner.nextLine());

            // Obtendo a data atual
            Date dataLocacao = new Date(); // Data atual
            // Definindo uma data de devolução, se necessário. Por exemplo, 7 dias após a locação.
            Date dataDevolucao = Date.from(LocalDate.now().plusDays(7).atStartOfDay(ZoneId.systemDefault()).toInstant());

            Locacao locacao = new Locacao(0, idCliente, idCarro, 0, dataLocacao, dataDevolucao, valorTotal);
            boolean sucesso = locacaoService.realizarLocacao(locacao);

            if (sucesso) {
                System.out.println("Locação realizada com sucesso!");
            } else {
                System.out.println("Falha ao realizar locação.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Erro ao realizar locação: Valor total ou IDs inválidos.");
        } catch (Exception e) {
            System.out.println("Erro ao realizar locação: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        MainCLI cli = new MainCLI();
        cli.iniciar();
    }
}
