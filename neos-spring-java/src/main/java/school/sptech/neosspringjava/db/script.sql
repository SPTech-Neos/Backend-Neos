create database blume;
use blume;

CREATE TABLE empresa(
  id_empresa INT PRIMARY KEY AUTO_INCREMENT,
  nome VARCHAR(45) NOT NULL,
  cnpj CHAR(14) NOT NULL
);

CREATE TABLE endereco(
  id_endereco INT PRIMARY KEY AUTO_INCREMENT,
  logradouro VARCHAR(45) NOT NULL,
  cidade VARCHAR(45) NOT NULL,
  estado CHAR(2) NOT NULL
);

CREATE TABLE local(
  id_local INT PRIMARY KEY AUTO_INCREMENT,
  numero INT NOT NULL,
  andar INT,
  bloco VARCHAR(2),
  complemento VARCHAR(45),
  fk_endereco INT NOT NULL,
  FOREIGN KEY (fk_endereco) REFERENCES endereco(id_endereco)
);

CREATE TABLE estabelecimento(
  id_estabelecimento INT PRIMARY KEY AUTO_INCREMENT,
  nome VARCHAR(45) NOT NULL,
  fk_empresa INT,
  fk_local INT NOT NULL,
  FOREIGN KEY (fk_empresa) REFERENCES empresa(id_empresa),
  FOREIGN KEY (fk_local) REFERENCES local(id_local)
);

CREATE TABLE tipo_produto(
  id_tipo_produto INT PRIMARY KEY AUTO_INCREMENT,
  nome VARCHAR(45) NOT NULL,
  especificacao VARCHAR(45) NOT NULL
);

CREATE TABLE produto(
  idProduto INT PRIMARY KEY AUTO_INCREMENT,
  nome VARCHAR(45),
  marca VARCHAR(45),
  fk_tipo_produto INT,
  fk_estabelecimento INT,
  FOREIGN KEY (fk_tipo_produto) REFERENCES tipo_produto(id_tipo_produto),
  FOREIGN KEY (fk_estabelecimento) REFERENCES estabelecimento(id_estabelecimento)
);

CREATE TABLE cliente(
  id_cliente INT PRIMARY KEY AUTO_INCREMENT,
  nome VARCHAR(45) NOT NULL,
  email VARCHAR(45) NOT NULL,
  senha VARCHAR(45) NOT NULL,
  fk_local INT NOT NULL,
  FOREIGN KEY (fk_local) REFERENCES local(id_local)  
);

CREATE TABLE tipo_funcionario(
  id_tipo_funcionario INT PRIMARY KEY AUTO_INCREMENT,
  nome VARCHAR(45) NOT NULL
);

CREATE TABLE funcionario(
  id_funcionario INT PRIMARY KEY AUTO_INCREMENT,
  nome VARCHAR(45) NOT NULL,
  email VARCHAR(45) NOT NULL,
  senha VARCHAR(45) NOT NULL,
  fk_estabelecimento INT NOT NULL, 
  fkTipoFuncionario INT NOT NULL,
  FOREIGN KEY (fk_estabelecimento) REFERENCES estabelecimento(id_estabelecimento),
  FOREIGN KEY (fkTipoFuncionario) REFERENCES tipo_funcionario(id_tipo_funcionario)
);

CREATE TABLE categoria_servico(
  id_categoria_servico INT PRIMARY KEY AUTO_INCREMENT,
  nome VARCHAR(45) NOT NULL
);

CREATE TABLE tipo_servico(
  id_tipo_servico INT PRIMARY KEY AUTO_INCREMENT,
  nome VARCHAR(45) NOT NULL,
  fk_categoria INT,
  FOREIGN KEY (fk_categoria) REFERENCES categoria_servico(id_categoria_servico)
);

CREATE TABLE servico(
  id_servico INT PRIMARY KEY AUTO_INCREMENT,
  especificacao VARCHAR(45) NOT NULL,
  fk_tipo_servico INT,
  FOREIGN KEY (fk_tipo_servico) REFERENCES tipo_servico(id_tipo_servico)

);

CREATE TABLE servicos_funcionario(
  id_servicos_funcionario INT,
  horasGastas DATETIME NOT NULL,
  expertise TINYINT NOT NULL,
  fk_funcionario INT,
  fk_servico INT,
  FOREIGN KEY (fk_funcionario) REFERENCES funcionario(id_funcionario),
  FOREIGN KEY (fk_servico) REFERENCES servico(id_servico),
  PRIMARY KEY(id_servicos_funcionario, fk_funcionario, fk_servico)
);

CREATE TABLE filtro(
  id_filtro INT,
  preco DOUBLE NOT NULL,
  fk_estabelecimento INT,
  fk_servico INT  NOT NULL,
  FOREIGN KEY (fk_estabelecimento) REFERENCES estabelecimento(id_estabelecimento),
  FOREIGN KEY (fk_servico) REFERENCES servico(id_servico),
  PRIMARY KEY(id_filtro, fk_estabelecimento, fk_servico)
);

CREATE TABLE tipo_desconto(
  idtipo_desconto INT PRIMARY KEY AUTO_INCREMENT,
  nome VARCHAR(45)
);

CREATE TABLE cupom(
  id_cupom INT PRIMARY KEY AUTO_INCREMENT,
  nome VARCHAR(45)
);

CREATE TABLE cupom_disponivel(
  id_cupom_disponivel INT PRIMARY KEY AUTO_INCREMENT,
  data_expiracao DATETIME NOT NULL,
  desconto DOUBLE NOT NULL,
  fk_desconto INT NOT NULL,
  fk_estabelecimento INT NOT NULL,
  fkCupom INT NOT NULL,
  FOREIGN KEY (fk_desconto) REFERENCES tipo_desconto(idtipo_desconto),
  FOREIGN KEY (fk_estabelecimento) REFERENCES estabelecimento(id_estabelecimento),
  FOREIGN KEY (fkCupom) REFERENCES cupom(id_cupom)
);

CREATE TABLE agendamento(
  id_Agendamento INT,
  data_hora DATETIME NOT NULL,
  fk_servico INT,
  fkCliente INT,
  fk_funcionario INT,
  FOREIGN KEY (fk_servico) REFERENCES servico(id_servico),
  FOREIGN KEY (fkCliente) REFERENCES cliente(id_cliente),
  FOREIGN KEY (fk_funcionario) REFERENCES funcionario(id_funcionario),
  PRIMARY KEY (fk_servico, fkCliente, fk_funcionario)
);