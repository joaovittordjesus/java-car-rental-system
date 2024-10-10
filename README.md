# Sistema de Gerenciamento de Locadora de Carros

## Descrição do Projeto

Este projeto tem como objetivo desenvolver um sistema informatizado para o gerenciamento de uma locadora de carros utilizando a linguagem Java. O sistema foi projetado para ser simples, com uma interface gráfica amigável e intuitiva, permitindo que os usuários realizem operações essenciais, como cadastro de clientes e carros, registro de locações, e geração de comprovantes de locação.

## Escopo

O sistema irá fornecer funcionalidades essenciais para o gerenciamento de uma locadora de veículos, incluindo:
- Cadastro de carros e clientes
- Efetuar Locação de carros.
- Registro e controle de locações.


## Requisitos

### Requisitos Funcionais
- **Cadastro de Carros**: O sistema permitirá o registro de novos veículos no banco de dados da locadora.
- **Cadastro de Clientes**: O sistema permitirá o cadastro de clientes, armazenando suas informações essenciais.
- **Realização de Locações**: Será possível registrar a locação de veículos, vinculando clientes aos carros alugados.
- **Registro de Locação de Carros**: O sistema manterá um histórico das locações realizadas.
- **Geração de Comprovantes**: O sistema gerará um comprovante em formato de arquivo de texto para cada locação realizada.

### Requisitos Não-Funcionais
- **Interface Gráfica**: O sistema contará com uma interface simples e amigável ao usuário, facilitando a navegação e a realização das operações.
- **Segurança**: O sistema deverá implementar mecanismos básicos de segurança para garantir a integridade e confidencialidade dos dados.
- **Desempenho**: O sistema deverá ser eficiente no processamento das operações, garantindo um tempo de resposta adequado.

## Arquitetura do Sistema

Este projeto segue a arquitetura **MVC (Model-View-Controller)**, separando a lógica de negócios (Model), a interface do usuário (View) e o controle das ações do usuário (Controller). Isso facilita a manutenção e escalabilidade do sistema.

- **Model**: Representa a estrutura de dados, utilizando classes que refletem as entidades do sistema, como `Carro`, `Cliente`, `Locacao`, `Comprovante`, `CarroDAO`, `ClienteDAO`, `LocacaoDAO`,`ComprovanteDAO`, `CarroService`, `ClienteService`, `LocacaoService`, `ComprovanteService`.
- **UI/View**: Refere-se à interface gráfica que será utilizada pelos funcionários para interagir com o sistema.
- **Controller/Service**: Gerencia as interações entre o usuário e o sistema, controlando o fluxo de dados entre o Model e a View.

## Persistência de Dados

Para a comunicação com a base de dados, foi utilizado **JDBC (Java Database Connectivity)**, uma API que permite a interação com o banco de dados PostgreSQL, garantindo a persistência e recuperação dos dados de forma eficiente.

- **JDBC**: Conexão direta com o banco de dados PostgreSQL para execução de operações CRUD (Create, Read, Update, Delete).
  
## Modelagem do Sistema

O desenvolvimento será guiado pela seguinte modelagem:

- **Diagrama de Classes + Entidades**: Define as principais classes do sistema e seus relacionamentos, refletindo a estrutura do código.
.

## Ferramentas e Tecnologias

As seguintes ferramentas e tecnologias serão utilizadas no desenvolvimento deste projeto:

- **IDE**: NetBeans 22, Visual Studio Code
- **Banco de Dados**: PostgreSQL
- **Linguagem de Programação**: Java
- **Conexão com Banco de Dados**: JDBC

## Instalação e Execução

1. **Clonar o repositório**: Faça o clone deste repositório localmente.
   ```bash
   git clone https://github.com/joaovittordjesus/java-car-rental-system.git

2. ** Configuração do Banco de Dados**

Antes de executar o sistema, é necessário configurar o banco de dados PostgreSQL. O banco de dados deve ser criado e as tabelas necessárias devem ser definidas com as seguintes instruções SQL.

### Passos para a Criação do Banco de Dados

1. **Instale o PostgreSQL**: Certifique-se de que o PostgreSQL está instalado e em execução no seu sistema.
2. **Crie um banco de dados**: Você pode criar um banco de dados utilizando o comando abaixo no terminal do PostgreSQL:
   ```sql
   CREATE DATABASE car-rental-system-db;

3. **Crie as tabelas do banco**
    -- Table CLIENTE: Removed redundancy by indexing relevant fields.
    CREATE TABLE CLIENTE (
        id_cliente SERIAL PRIMARY KEY,
        email VARCHAR(100) NOT NULL UNIQUE, -- Added UNIQUE constraint
        celular VARCHAR(20) NOT NULL,
        nome VARCHAR(100) NOT NULL,
        sobrenome VARCHAR(50) NOT NULL,
        endereco VARCHAR(100) NOT NULL,
        cidade VARCHAR(100) NOT NULL,
        estado CHAR(2) NOT NULL
    );

    -- Table COMPROVANTE: Improved foreign key relationship.
    CREATE TABLE COMPROVANTE (
        id_comprovante SERIAL PRIMARY KEY,
        comprovante TEXT NOT NULL, -- Changed VARCHAR(1000) to TEXT for better flexibility
        id_pagamento INT NOT NULL, -- Adjusted constraint ordering
        id_cliente INT NOT NULL,
        FOREIGN KEY (id_pagamento) REFERENCES PAGAMENTO(id_pagamento),
        FOREIGN KEY (id_cliente) REFERENCES CLIENTE(id_cliente)
    );

    -- Table LOCACAO: Indexed frequently accessed foreign keys.
    CREATE TABLE LOCACAO (
        id_locacao SERIAL PRIMARY KEY,
        id_cliente INT NOT NULL,
        numero_agencia INT NOT NULL,
        data_devolucao DATE DEFAULT CURRENT_DATE NOT NULL,
        data_locacao DATE DEFAULT CURRENT_DATE NOT NULL,
        valor_total DECIMAL(10, 2) NOT NULL,
        id_pagamento INT NOT NULL,
        FOREIGN KEY (id_cliente) REFERENCES CLIENTE(id_cliente),
        FOREIGN KEY (numero_agencia) REFERENCES AGENCIA(numero_agencia),
        FOREIGN KEY (id_pagamento) REFERENCES PAGAMENTO(id_pagamento)
    );

    -- Table CARRO: Optimized constraints and added indexes for foreign keys.
    CREATE TABLE CARRO (
        id_carro SERIAL PRIMARY KEY,
        numero_agencia INT NOT NULL,
        placa VARCHAR(20) NOT NULL UNIQUE, -- Added UNIQUE constraint and adjusted size
        modelo VARCHAR(100) NOT NULL,
        ano DATE NOT NULL, -- Changed data type to DATE
        tipo CHAR(1), -- Changed to CHAR(1) to save space
        disponibilidade VARCHAR(50) DEFAULT 'DISPONIVEL' NOT NULL,
        id_locacao INT,
        valor_total DECIMAL(10, 2) NOT NULL,
        FOREIGN KEY (numero_agencia) REFERENCES AGENCIA(numero_agencia),
        FOREIGN KEY (id_locacao) REFERENCES LOCACAO(id_locacao)
    );


    -- Creating indexes after table creation
    CREATE INDEX idx_locacao_id_cliente ON LOCACAO(id_cliente);
    CREATE INDEX idx_locacao_numero_agencia ON LOCACAO(numero_agencia);
    CREATE INDEX idx_locacao_id_pagamento ON LOCACAO(id_pagamento);
    CREATE INDEX idx_carro_numero_agencia ON CARRO(numero_agencia);
    CREATE INDEX idx_carro_id_locacao ON CARRO(id_locacao);


4. **Inicio o projeto com a tecla f5 no Visual Studio Code, ou F6 no NetBeans 22**
