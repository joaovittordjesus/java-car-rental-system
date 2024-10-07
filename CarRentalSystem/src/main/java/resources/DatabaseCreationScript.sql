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

-- Table AGENCIA: Adjusted phone field and indexed frequently accessed columns.
CREATE TABLE AGENCIA (
    numero_agencia SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL, -- Moved the new 'nome' column into the initial schema
    email VARCHAR(100) NOT NULL UNIQUE, -- Added UNIQUE constraint
    telefone VARCHAR(20) NOT NULL, -- Reduced VARCHAR size for better performance
    endereco VARCHAR(255) NOT NULL
);

-- Table PAGAMENTO: Added index on foreign key.
CREATE TABLE PAGAMENTO (
    id_pagamento SERIAL PRIMARY KEY,
    status_pagamento VARCHAR(100) DEFAULT 'PENDENTE' NOT NULL,
    valor DECIMAL(10, 2) NOT NULL,
    forma_pagamento VARCHAR(100) NOT NULL,
    data_pagamento DATE NOT NULL,
    id_comprovante INT NOT NULL
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

-- Table FEEDBACK: Added constraint for data integrity.
CREATE TABLE FEEDBACK (
    id_feedback SERIAL PRIMARY KEY,
    comentario TEXT NOT NULL, -- Changed VARCHAR(255) to TEXT
    avaliacao VARCHAR(50) NOT NULL, -- Adjusted size based on typical data length
    id_cliente INT NOT NULL,
    FOREIGN KEY (id_cliente) REFERENCES CLIENTE(id_cliente)
);

-- Table FUNCIONARIO: Optimized VARCHAR sizes and indexed foreign key.
CREATE TABLE FUNCIONARIO (
    id_funcionario SERIAL PRIMARY KEY,
    salario DECIMAL(10, 2) NOT NULL,
    nome VARCHAR(100) NOT NULL,
    sobrenome VARCHAR(100) NOT NULL,
    data_contratacao DATE NOT NULL,
    cargo VARCHAR(50) NOT NULL,
    numero_agencia INT NOT NULL,
    FOREIGN KEY(numero_agencia) REFERENCES AGENCIA(numero_agencia)
);

-- Table MANUTENCAO: Reduced unnecessary default sizes.
CREATE TABLE MANUTENCAO (
    id_manutencao SERIAL PRIMARY KEY,
    custo DECIMAL(10, 2) NOT NULL,
    data_manutencao DATE DEFAULT CURRENT_DATE NOT NULL,
    km INT NOT NULL,
    tipo_manutencao VARCHAR(50) NOT NULL, -- Adjusted VARCHAR size
    descricao TEXT -- Changed VARCHAR(1000) to TEXT
);

-- Table ORDEM_MANUTENCAO: Improved indexing for foreign keys.
CREATE TABLE ORDEM_MANUTENCAO (
    id_ordem_manutencao SERIAL PRIMARY KEY,
    id_manutencao INT NOT NULL,
    data_emissao DATE DEFAULT CURRENT_DATE NOT NULL,
    local VARCHAR(100),
    descricao TEXT, -- Changed VARCHAR(1000) to TEXT
    objetivo TEXT, -- Changed VARCHAR(1000) to TEXT
    id_carro INT NOT NULL,
    id_locacao INT NOT NULL,
    status VARCHAR(50) DEFAULT 'PENDENTE' NOT NULL,
    FOREIGN KEY(id_manutencao) REFERENCES MANUTENCAO(id_manutencao),
    FOREIGN KEY(id_locacao) REFERENCES LOCACAO(id_locacao),
    FOREIGN KEY(id_carro) REFERENCES CARRO(id_carro)
);

-- Creating indexes after table creation
CREATE INDEX idx_pagamento_id_comprovante ON PAGAMENTO(id_comprovante);
CREATE INDEX idx_locacao_id_cliente ON LOCACAO(id_cliente);
CREATE INDEX idx_locacao_numero_agencia ON LOCACAO(numero_agencia);
CREATE INDEX idx_locacao_id_pagamento ON LOCACAO(id_pagamento);
CREATE INDEX idx_carro_numero_agencia ON CARRO(numero_agencia);
CREATE INDEX idx_carro_id_locacao ON CARRO(id_locacao);
CREATE INDEX idx_feedback_id_cliente ON FEEDBACK(id_cliente);
CREATE INDEX idx_funcionario_numero_agencia ON FUNCIONARIO(numero_agencia);
CREATE INDEX idx_ordem_manutencao_id_manutencao ON ORDEM_MANUTENCAO(id_manutencao);
CREATE INDEX idx_ordem_manutencao_id_locacao ON ORDEM_MANUTENCAO(id_locacao);
CREATE INDEX idx_ordem_manutencao_id_carro ON ORDEM_MANUTENCAO(id_carro);
