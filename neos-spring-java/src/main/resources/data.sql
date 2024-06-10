DROP DATABASE blume;
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
  img_url VARCHAR(250),
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
  img_url VARCHAR(250),
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
  img_url VARCHAR(250),
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
  img_url VARCHAR(250),
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
  img_url VARCHAR(250),
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
('Bella Vista', 1, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSu334ZQlSkSb78tgi9yUVS0EaK4N8iM5X9OQ&s', 1),
('Charme & Elegância', 2, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSIv_PcZ6EhC9NbapS0k0IjqESMt5f2pqrTxdUQ4Yhg5V3qIkiI1TVx_gCNg15IFEywIvI&usqp=CAU', 2),
('Barbealira', 1, 'https://www.guiadasemana.com.br/contentFiles/image/opt_w1024h1024/2017/02/FEA/49393_shutterstock-barbearia.jpg', 1),
('Cabeleireira Leila', 1, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSQJHbu_cfteLtz2lVnEpf29OQd4O1TrzlypHb5XUW0B7A_1pGuAXYWL20-BPYShn69CEs&usqp=CAU', 1);

INSERT INTO product_type (name, specification) VALUES
('Cabelo', 'Produtos para cabelo'),
('Maquiagem', 'Produtos de maquiagem'),
('Unhas', 'Produtos para unhas');

INSERT INTO product (name, brand, img_url, value, type_fk, establishment_fk) VALUES
('Shampoo', 'LOréal', 'https://images.tcdn.com.br/img/img_prod/1017481/kit_absolut_repair_shampoo_1_5l_mascara_500g_condicionador_1_5l_l_oreal_professional_2935_1_2ef166991604a8236b6fc08cb297bd6f.jpg', 50.0, 1, 1),
('Base líquida', 'MAC', 'https://epocacosmeticos.vteximg.com.br/arquivos/ids/568595-320-320/base-liquida-mac-studio-radiance37--1-.jpg?v=638304007998430000', 80.0, 2, 2),
('Esmalte', 'Risque', 'https://drogariaspacheco.vteximg.com.br/arquivos/ids/1199328-1000-1000/208647---esmalte-risque-cintilante-perola-8ml-5.jpg?v=638394706034030000', 20.0, 3, 1);

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
('Funcionário A', 'funcionario.a@example.com', '$2a$10$aii7/bEjM0F1cyEbgG4aQu6kwe0mraOmeXzI2z1/MRDimtgZYM7.W', 'https://nucleocursos.com.br/blog/wp-content/uploads/2022/10/Profissao-Cabeleireiro-Tudo-o-que-voce-precisa-saber.jpeg', 1, 1),
('Pedro Santos', 'pedro.santos@example.com', '$2a$10$aii7/bEjM0F1cyEbgG4aQu6kwe0mraOmeXzI2z1/MRDimtgZYM7.W', 'https://img.freepik.com/fotos-gratis/barbeiro-profissional-com-ferramentas-de-barbeiro-close-up_1303-20984.jpgg', 1, 2),
('Camila Oliveira', 'camila.oliveira@example.com', '$2a$10$aii7/bEjM0F1cyEbgG4aQu6kwe0mraOmeXzI2z1/MRDimtgZYM7.W', 'https://www.primecursos.com.br/arquivos/uploads/2013/08/cabeleireiro.jpg', 2, 3),
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
('Corte Masculino', 'https://static1.purepeople.com.br/articles/5/34/50/15/@/3932061-as-tendencias-em-corte-de-cabelo-masculi-1200x630-2.jpg', 1),
('Hidratação Capilar', 'https://itmae.com.br/wp-content/uploads/2016/01/cabelo-sal%C3%A3o-hidrata%C3%A7%C3%A3o.jpg', 1),
('Maquiagem para Festas', 'https://moda20.com.br/wp-content/uploads/2023/02/Tendencias-de-Moda.jpg', 2);

INSERT INTO employee_services (hours_spent, expertise, employee_fk, service_fk) VALUES
(NOW(), 5, 1, 1),
(NOW(), 6, 2, 2),
(NOW(), 7, 3, 1),
(NOW(), 8, 4, 1);

INSERT INTO filter (price, establishment_fk, service_fk) VALUES
(80.0, 1, 1),
(100.0, 2, 2);

INSERT INTO scheduling_status (description) VALUES
('Agendado'),
('Confirmado'),
('Cancelado');

INSERT INTO scheduling (date_time, value, service_fk, status_fk, client_fk, employee_fk) VALUES
(NOW(), 200.0, 1, 2, 1, 1),
(NOW() + INTERVAL 1 HOUR, 189.99, 1, 1, 1, 2),
(NOW(), 150.0, 1, 3, 2, 2),
(NOW() + INTERVAL 1 HOUR, 101.0, 1, 1, 2, 1);

INSERT INTO payment (value, date_payment, product_fk, client_fk, establishment_fk) VALUES
(150.0, NOW(), 1, 1, 1),
(120.0, NOW(), 2, 2, 2);
