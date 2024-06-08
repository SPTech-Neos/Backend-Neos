CREATE DATABASE blume;
USE blume;

CREATE TABLE company(
  company_id INT PRIMARY KEY auto_increment,
  name VARCHAR(45) NOT NULL,
  cnpj CHAR(14) NOT NULL
);

CREATE TABLE address(
  address_id INT PRIMARY KEY auto_increment,
  public_place VARCHAR(45) NOT NULL,
  city VARCHAR(45) NOT NULL,
  state CHAR(2) NOT NULL,
  street VARCHAR(45) NOT NULL
);

CREATE TABLE local(
  local_id INT PRIMARY KEY auto_increment,
  number INT NOT NULL,
  floor INT,
  block VARCHAR(2),
  complement VARCHAR(45),
  address_fk INT NOT NULL,
  FOREIGN KEY (address_fk) REFERENCES address(address_id) ON DELETE CASCADE
);

CREATE TABLE establishment(
  establishment_id INT PRIMARY KEY auto_increment,
  name VARCHAR(45) NOT NULL,
  company_fk INT,
  img_url VARCHAR(100),
  local_fk INT NOT NULL,
  FOREIGN KEY (company_fk) REFERENCES company(company_id) ON DELETE CASCADE,
  FOREIGN KEY (local_fk) REFERENCES local(local_id) ON DELETE CASCADE
);

CREATE TABLE product_type(
  product_type_id INT PRIMARY KEY auto_increment,
  name VARCHAR(45) NOT NULL,
  specification VARCHAR(45) NOT NULL
);

CREATE TABLE product(
  product_id INT PRIMARY KEY auto_increment,
  name VARCHAR(45),
  brand VARCHAR(45),
  img_url VARCHAR(100),
  value DECIMAL,
  type_fk INT,
  establishment_fk INT,
  FOREIGN KEY (type_fk) REFERENCES product_type(product_type_id) ON DELETE CASCADE,
  FOREIGN KEY (establishment_fk) REFERENCES establishment(establishment_id) ON DELETE CASCADE
);

CREATE TABLE client(
  client_id INT PRIMARY KEY auto_increment,
  name VARCHAR(45) NOT NULL,
  email VARCHAR(45) NOT NULL,
  password VARCHAR(300) NOT NULL,
  img_url VARCHAR(100),
  local_fk INT NOT NULL,
  FOREIGN KEY (local_fk) REFERENCES local(local_id) ON DELETE CASCADE
);

CREATE TABLE rating(
  rating_id INT auto_increment,
  nota INT NOT NULL,
  establishment_fk INT,
  client_fk INT,
  FOREIGN KEY (establishment_fk) REFERENCES establishment(establishment_id) ON DELETE CASCADE,
  FOREIGN KEY (client_fk) REFERENCES client(client_id) ON DELETE CASCADE,
  PRIMARY KEY (rating_id, establishment_fk, client_fk)
);

CREATE TABLE employee_type(
  employee_type_id INT PRIMARY KEY auto_increment,
  name VARCHAR(45) NOT NULL
);

CREATE TABLE employee(
  employee_id INT PRIMARY KEY auto_increment,
  name VARCHAR(45) NOT NULL,
  email VARCHAR(45) NOT NULL,
  password VARCHAR(300) NOT NULL,
  img_url VARCHAR(100),
  establishment_fk INT NOT NULL,
  employee_type_fk INT NOT NULL,
  FOREIGN KEY (establishment_fk) REFERENCES establishment(establishment_id) ON DELETE CASCADE,
  FOREIGN KEY (employee_type_fk) REFERENCES employee_type(employee_type_id) ON DELETE CASCADE
);

CREATE TABLE service_category(
  service_category_id INT PRIMARY KEY auto_increment,
  service_category_name VARCHAR(45) NOT NULL
);

CREATE TABLE service_type(
  service_type_id INT PRIMARY KEY auto_increment,
  service_type_name VARCHAR(45) NOT NULL,
  category_fk INT,
  FOREIGN KEY (category_fk) REFERENCES service_category(service_category_id) ON DELETE CASCADE
);

CREATE TABLE service(
  service_id INT PRIMARY KEY auto_increment,
  specification VARCHAR(45) NOT NULL,
  img_url VARCHAR(100),
  type_fk INT,
  FOREIGN KEY (type_fk) REFERENCES service_type(service_type_id) ON DELETE CASCADE
);

CREATE TABLE employee_services(
  employee_services_id INT auto_increment,
  hours_spent DATETIME NOT NULL,
  expertise TINYINT NOT NULL,
  employee_fk INT,
  service_fk INT,
  FOREIGN KEY (employee_fk) REFERENCES employee(employee_id) ON DELETE CASCADE,
  FOREIGN KEY (service_fk) REFERENCES service(service_id) ON DELETE CASCADE,
  PRIMARY KEY(employee_services_id, employee_fk, service_fk)
);

CREATE TABLE filter(
  filter_id INT auto_increment,
  price FLOAT NOT NULL,
  establishment_fk INT,
  service_fk INT NOT NULL,
  FOREIGN KEY (establishment_fk) REFERENCES establishment(establishment_id) ON DELETE CASCADE,
  FOREIGN KEY (service_fk) REFERENCES service(service_id) ON DELETE CASCADE,
  PRIMARY KEY(filter_id, establishment_fk, service_fk)
);

CREATE TABLE scheduling_status(
  scheduling_status_id INT PRIMARY KEY auto_increment,
  description VARCHAR(100)
);

CREATE TABLE scheduling(
  scheduling_id INT auto_increment PRIMARY KEY,
  date_time DATETIME NOT NULL,
  value DECIMAL,
  service_fk INT,
  status_fk INT,
  client_fk INT,
  employee_fk INT,
  FOREIGN KEY (status_fk) REFERENCES scheduling_status(scheduling_status_id) ON DELETE CASCADE,
  FOREIGN KEY (service_fk) REFERENCES service(service_id) ON DELETE CASCADE,
  FOREIGN KEY (client_fk) REFERENCES client(client_id) ON DELETE CASCADE,
  FOREIGN KEY (employee_fk) REFERENCES employee(employee_id) ON DELETE CASCADE
);

CREATE TABLE payment(
  payment_id INT auto_increment PRIMARY KEY,
  value DECIMAL,
  date_payment DATETIME,
  product_fk INT,
  client_fk INT,
  establishment_fk INT,
  FOREIGN KEY (client_fk) REFERENCES client(client_id) ON DELETE CASCADE,
  FOREIGN KEY (product_fk) REFERENCES product(product_id) ON DELETE CASCADE,
  FOREIGN KEY (establishment_fk) REFERENCES establishment(establishment_id) ON DELETE CASCADE
);

-- Inserir dados de exemplo
INSERT INTO company (name, cnpj) VALUES
('Salão de Beleza Bella Vista', '12345678901234'),
('Salão de Beleza Charme & Elegância', '98765432109876');

INSERT INTO address (public_place, city, state, street) VALUES
('Rua das Flores, 123', 'Cidade Alegria', 'SP', 'Rua das Flores'),
('Avenida Central, 456', 'Vila da Beleza', 'RJ', 'Avenida Central');

INSERT INTO local (number, floor, block, complement, address_fk) VALUES
(101, 1, NULL, 'Próximo à recepção', 1),
(202, 2, NULL, 'Próximo à área de atendimento', 2);

INSERT INTO establishment (name, company_fk, img_url, local_fk) VALUES
('Bella Vista', 1, 'http://example.com/bella_vista.jpg', 1),
('Charme & Elegância', 2, 'http://example.com/charme_e_elegancia.jpg', 2);

INSERT INTO product_type (name, specification) VALUES
('Cabelo', 'Produtos para cabelo'),
('Maquiagem', 'Produtos de maquiagem'),
('Unhas', 'Produtos para unhas');

INSERT INTO product (name, brand, img_url, value, type_fk, establishment_fk) VALUES
('Shampoo', 'LOréal', 'http://example.com/shampoo.jpg', 50.0, 1, 1),
('Base líquida', 'MAC', 'http://example.com/base.jpg', 80.0, 2, 2),
('Esmalte', 'Risque', 'http://example.com/esmalte.jpg', 20.0, 3, 1);

INSERT INTO client (name, email, password, img_url, local_fk) VALUES
('Cliente A', 'cliente.a@example.com', 'senha123', 'http://example.com/client.jpg', 1),
('Cliente', 'cliente@example.com', '$2a$10$18fD7KSKFmwwW8lwIPZhw.nSXWxKhU/ifh4DKaNGxOByludy3Q4Di', 'http://example.com/client.jpg', 2);


INSERT INTO rating (nota, establishment_fk, client_fk) VALUES
(5, 1, 1),
(4, 2, 2);

INSERT INTO employee_type (name) VALUES
('Cabeleireiro(a)'),
('Esteticista'),
('Maquiador(a)');

-- senha123

INSERT INTO employee (name, email, password, img_url, establishment_fk, employee_type_fk) VALUES
('Funcionário A', 'funcionario.a@example.com', '$2a$10$aii7/bEjM0F1cyEbgG4aQu6kwe0mraOmeXzI2z1/MRDimtgZYM7.W', 'http://example.com/employee.jpg', 1, 1),
('Pedro Santos', 'pedro.santos@example.com', '$2a$10$aii7/bEjM0F1cyEbgG4aQu6kwe0mraOmeXzI2z1/MRDimtgZYM7.W', 'http://example.com/pedro_santos.jpg', 1, 2),
('Camila Oliveira', 'camila.oliveira@example.com', '$2a$10$aii7/bEjM0F1cyEbgG4aQu6kwe0mraOmeXzI2z1/MRDimtgZYM7.W', 'http://example.com/camila_oliveira.jpg', 2, 3),
('Rafael Lima', 'rafael.lima@example.com', '$2a$10$aii7/bEjM0F1cyEbgG4aQu6kwe0mraOmeXzI2z1/MRDimtgZYM7.W', 'http://example.com/rafael_lima.jpg', 2, 1);

INSERT INTO service_category (service_category_name) VALUES
('Cabelo'),
('Estética Facial'),
('Maquiagem');

INSERT INTO service_type (service_type_name, category_fk) VALUES
('Corte de Cabelo', 1),
('Limpeza de Pele', 2),
('Maquiagem Social', 3);

INSERT INTO service (specification, img_url, type_fk) VALUES
('Corte Masculino', 'http://example.com/corte_masculino.jpg', 4),
('Hidratação Capilar', 'http://example.com/hidratacao_capilar.jpg', 5),
('Maquiagem para Festas', 'http://example.com/maquiagem_festas.jpg', 6);

INSERT INTO employee_services (hours_spent, expertise, employee_fk, service_fk) VALUES
(NOW(), 5, 3, 4),
(NOW(), 6, 4, 5),
(NOW(), 7, 2, 6),
(NOW(), 8, 3, 5);

INSERT INTO filter (price, establishment_fk, service_fk) VALUES
(80.0, 1, 5),
(100.0, 2, 6);

INSERT INTO scheduling_status (description) VALUES
('Agendado'),
('Confirmado'),
('Cancelado');

INSERT INTO scheduling (date_time, value, service_fk, status_fk, client_fk, employee_fk) VALUES
(NOW(), 200.0, 1, 1, 1, 1),
(NOW(), 150.0, 6, 1, 2, 3);

INSERT INTO payment (value, date_payment, product_fk, client_fk, establishment_fk) VALUES
(150.0, NOW(), 1, 1, 1),
(120.0, NOW(), 2, 2, 2);;
