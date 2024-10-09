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
