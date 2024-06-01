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
  password VARCHAR(45) NOT NULL,
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
  password VARCHAR(45) NOT NULL,
  img_url VARCHAR(100),
  establishment_fk INT NOT NULL,
  employee_type_fk INT NOT NULL,
  FOREIGN KEY (establishment_fk) REFERENCES establishment(establishment_id) ON DELETE CASCADE,
  FOREIGN KEY (employee_type_fk) REFERENCES employee_type(employee_type_id) ON DELETE CASCADE
);

CREATE TABLE serviceCategory(
  service_category_id INT PRIMARY KEY auto_increment,
  service_category_name VARCHAR(45) NOT NULL
);

CREATE TABLE serviceType(
  service_type_id INT PRIMARY KEY auto_increment,
  service_type_name VARCHAR(45) NOT NULL,
  category_fk INT,
  FOREIGN KEY (category_fk) REFERENCES serviceCategory(service_category_id) ON DELETE CASCADE
);

CREATE TABLE service(
  service_id INT PRIMARY KEY auto_increment,
  specification VARCHAR(45) NOT NULL,
  img_url VARCHAR(100),
  type_fk INT,
  FOREIGN KEY (type_fk) REFERENCES serviceType(service_type_id) ON DELETE CASCADE
);

CREATE TABLE employeeServices(
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

CREATE TABLE schedulingStatus(
  schedulingStatus_id INT PRIMARY KEY auto_increment,
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
  FOREIGN KEY (status_fk) REFERENCES schedulingStatus(schedulingStatus_id) ON DELETE CASCADE,
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
('Empresa A', '50037903000115');

INSERT INTO address (public_place, city, state, street) VALUES
('123 Main St', 'Anytown', 'ST', '123 Main St');

INSERT INTO local (number, floor, block, complement, address_fk) VALUES
(101, 1, 'A', 'Near the entrance', 1);

INSERT INTO establishment (name, company_fk, img_url, local_fk) VALUES
('Estabelecimento A', 1, 'http://example.com/image.jpg', 1);

INSERT INTO product_type (name, specification) VALUES
('Tipo de Produto A', 'Especificação A');

INSERT INTO product (name, brand, img_url, value, type_fk, establishment_fk) VALUES
('Produto A', 'Marca A', 'http://example.com/product.jpg', 100.0, 1, 1);

INSERT INTO client (name, email, password, img_url, local_fk) VALUES
('Cliente A', 'cliente.a@example.com', 'senha123', 'http://example.com/client.jpg', 1);

INSERT INTO rating (nota, establishment_fk, client_fk) VALUES
(5, 1, 1);

INSERT INTO employee_type (name) VALUES
('Tipo de Funcionário A');

INSERT INTO employee (name, email, password, img_url, establishment_fk, employee_type_fk) VALUES
('Funcionário A', 'funcionario.a@example.com', 'senha123', 'http://example.com/employee.jpg', 1, 1);

INSERT INTO serviceCategory (service_category_name) VALUES
('Categoria de Serviço A');

INSERT INTO serviceType (service_type_name, category_fk) VALUES
('Tipo de Serviço A', 1);

INSERT INTO service (specification, img_url, type_fk) VALUES
('Especificação do Serviço A', 'http://example.com/service.jpg', 1);

INSERT INTO employeeServices (hours_spent, expertise, employee_fk, service_fk) VALUES
(NOW(), 5, 1, 1);

INSERT INTO filter (price, establishment_fk, service_fk) VALUES
(100.0, 1, 1);

INSERT INTO schedulingStatus (description) VALUES
('Status de Agendamento A');

INSERT INTO scheduling (date_time, value, service_fk, status_fk, client_fk, employee_fk) VALUES
(NOW(), 200.0, 1, 1, 1, 1);

INSERT INTO payment (value, date_payment, product_fk, client_fk, establishment_fk) VALUES
(150.0, NOW(), 1, 1, 1);
