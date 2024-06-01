CREATE DATABASE blume;
USE blume;
CREATE TABLE company(
  company_id INT PRIMARY KEY auto_increment ,
  name VARCHAR(45) NOT NULL,
  cnpj CHAR(14) NOT NULL
);

CREATE TABLE address(
  address_id INT PRIMARY KEY auto_increment ,
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
  FOREIGN KEY (address_fk) REFERENCES address(address_id)
);

CREATE TABLE establishment(
  establishment_id INT PRIMARY KEY auto_increment,
  name VARCHAR(45) NOT NULL,
  company_fk INT,
  img_url VARCHAR(100), 
  local_fk INT NOT NULL,
  FOREIGN KEY (company_fk) REFERENCES company(company_id),
  FOREIGN KEY (local_fk) REFERENCES local(local_id)
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
  FOREIGN KEY (type_fk) REFERENCES product_type(product_type_id),
  FOREIGN KEY (establishment_fk) REFERENCES establishment(establishment_id)
);

CREATE TABLE client(
  client_id INT PRIMARY KEY auto_increment,
  name VARCHAR(45) NOT NULL,
  email VARCHAR(45) NOT NULL,
  password VARCHAR(45) NOT NULL,
  img_url VARCHAR(100), 
  local_fk INT NOT NULL,
  FOREIGN KEY (local_fk) REFERENCES local(local_id)  
);

CREATE TABLE rating(
  rating_id INT auto_increment,
  nota INT NOT NULL,
  establishment_fk INT,
  client_fk INT,
  FOREIGN KEY (establishment_fk) REFERENCES establishment(establishment_id),
  FOREIGN KEY (client_fk) REFERENCES client(client_id),
  PRIMARY KEY (rating_id, establishment_fk, client_fk)
);

CREATE TABLE employeeType(
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
  FOREIGN KEY (establishment_fk) REFERENCES establishment(establishment_id),
  FOREIGN KEY (employee_type_fk) REFERENCES employeeType(employee_type_id)
);

CREATE TABLE serviceCategory(
  service_category_id INT PRIMARY KEY auto_increment,
  service_category_name VARCHAR(45) NOT NULL
);

CREATE TABLE serviceType(
  service_type_id INT PRIMARY KEY  auto_increment,
  service_type_name VARCHAR(45) NOT NULL,
  category_fk INT,
  FOREIGN KEY (category_fk) REFERENCES serviceCategory(service_category_id)
);

CREATE TABLE service(
  service_id INT PRIMARY KEY  auto_increment,
  specification VARCHAR(45) NOT NULL,
  img_url VARCHAR(100), 
  type_fk INT,
  FOREIGN KEY (type_fk) REFERENCES serviceType(service_type_id)

);

CREATE TABLE employeeServices(
  employee_services_id INT auto_increment,
  hours_spent DATETIME NOT NULL,
  expertise TINYINT NOT NULL,
  employee_fk INT,
  service_fk INT,
  FOREIGN KEY (employee_fk) REFERENCES employee(employee_id),
  FOREIGN KEY (service_fk) REFERENCES service(service_id),
  PRIMARY KEY(employee_services_id, employee_fk, service_fk)
);

CREATE TABLE filter(
  filter_id INT auto_increment,
  price FLOAT NOT NULL,
  establishment_fk INT,
  service_fk INT  NOT NULL,
  FOREIGN KEY (establishment_fk) REFERENCES establishment(establishment_id),
  FOREIGN KEY (service_fk) REFERENCES service(service_id),
  PRIMARY KEY(filter_id, establishment_fk, service_fk)
);

CREATE TABLE schedulingStatuS(
  schedulingStatus_id INT PRIMARY KEY auto_increment ,
  description VARCHAR(100)
);

CREATE TABLE scheduling(
  scheduling_id INT ,
  date_time DATETIME NOT NULL,
  value DECIMAL,
  service_fk INT,
  status_fk INT,
  client_fk INT,
  employee_fk INT,
  FOREIGN KEY (status_fk) REFERENCES schedulingStatus(schedulingStatus_id),
  FOREIGN KEY (service_fk) REFERENCES service(service_id),
  FOREIGN KEY (client_fk) REFERENCES client(client_id),
  FOREIGN KEY (employee_fk) REFERENCES employee(employee_id),
  PRIMARY KEY (service_fk, client_fk, employee_fk)
);


CREATE TABLE payment(
  payment_id INT ,
  value DECIMAL,
  date_payment DATETIME,
  product_fk INT, 
  client_fk INT,
  establishment_fk INT,
  FOREIGN KEY (client_fk) REFERENCES client(client_id),
  FOREIGN KEY (product_fk) REFERENCES product(product_id),
  FOREIGN KEY (establishment_fk) REFERENCES establishment(establishment_id),
  PRIMARY KEY (product_fk, payment_id, establishment_fk, client_fk)
);

Insert INTO Company (name, cnpj) VALUES
('Empresa A', '50037903000115');

INSERT into rating (nota, establishment_fk, client_fk) VALUES
(5, 1, 1),
(4, 1, 2);





-- -- Inserir endereços
-- INSERT INTO Address (street, city, state) VALUES ('123 Main St', 'Anytown', 'State');

-- -- Inserir locais
-- INSERT INTO Local (cep, number, floor, bloc, complement, address_id) VALUES ('CEP A', 101, 1, 'A', 'Near the entrance', (SELECT id FROM Address WHERE street='123 Main St'));

-- -- Inserir categorias de serviço
-- INSERT INTO Service_Category (name) VALUES ('Healthcare');

-- -- Inserir tipos de serviço
-- INSERT INTO Service_Type (name, service_category_id) VALUES ('General Checkup', (SELECT id FROM Service_Category WHERE name='Healthcare'));

-- -- Inserir serviços
-- INSERT INTO Service (specification, service_type_id) VALUES ('Complete physical examination', (SELECT id FROM Service_Type WHERE name='General Checkup'));

-- -- Inserir filtros
-- INSERT INTO Filter (price, service_id) VALUES (99.99, (SELECT id FROM Service WHERE specification='Complete physical examination'));

-- -- Inserir clientes
-- INSERT INTO Client (name, email, password, local_id) VALUES ('John Doe', 'john.doe@example.com', 'password123', (SELECT id FROM Local WHERE number=101));

-- -- Insert data into Employee_Type
-- INSERT INTO Employee_Type (name) VALUES ('Doctor');

-- -- Insert data into Establishment
-- INSERT INTO Establishment (name, assessment, end_shift, local_id, qtd_assessment, start_shift, cnpj, description)
-- VALUES ('Health Clinic', 4.5, '18:00:00', (SELECT id FROM Local WHERE number=101), 20, '08:00:00', '12345678901234', 'A general health clinic');

-- -- Insert data into Employee
-- INSERT INTO Employee (name, email, password, employee_type_id, establishment_id)
-- VALUES ('Dr. Smith', 'dr.smith@example.com', 'password456',
--         (SELECT id FROM Employee_Type WHERE name='manicure'),
--         (SELECT id FROM Establishment WHERE name='Health Clinic'));

-- -- Inserir produtos (exemplo)
-- INSERT INTO Product (name, price) VALUES ('Blood Test', 49.99);

-- -- Inserir agendamentos
-- INSERT INTO Scheduling (client_client_id, service_id, employee_id, date_time)
-- VALUES (
--            (SELECT client_id FROM Client WHERE name='John Doe'),
--            (SELECT id FROM Service WHERE specification='Complete physical examination'),
--            (SELECT id FROM Employee WHERE name='Dr. Smith'),
--            '2024-05-24T08:00:00'
--        );



-- INSERT INTO address (street, city, state) VALUES
-- ('Rua A', 'Cidade A', 'AA'),
-- ('Rua B', 'Cidade B', 'BB'),
-- ('Rua C', 'Cidade C', 'CC'),
-- ('Rua D', 'Cidade D', 'DD');

-- INSERT INTO local (cep, number, floor, bloc, complement, address_id) VALUES
--                                                                                  ('CEP A', 1, 1, 'A', 'Casa', 1),
--                                                                                  ('CEP B', 2, 2, 'B', 'Apartamento', 2),
--                                                                                  ('CEP C', 3, 3, 'C', 'Casa', 3),
--                                                                                  ('CEP D', 4, 4, 'D', 'Apartamento', 4);

-- INSERT INTO client (name, email, password) VALUES
-- ('Cliente A', 'cliente.a@example.com', '$2a$10$1h3qTcdIfxbfPyMKrDUAie5SzNfKyTk/HgvrtUSI.3ZyElQGy75We'),
-- ('Cliente B', 'cliente.b@example.com', 'senha456'),
-- ('Cliente C', 'cliente.c@example.com', 'senha789'),
-- ('Cliente D', 'cliente.d@example.com', 'senhaabc'),
-- ('Cliente Funcionario', 'cliente.funcionario@example.com', '$2a$10$kb6w6bL6NG.p8ZjjMIQWs.496gUWEC9cXA1StRkgtRcJz1HOWQubS');


-- INSERT INTO establishment (name,end_shift, start_shift, local_id, qtd_assessment, assessment, cnpj) VALUES
-- ('Estabelecimento A', '18:00:00', '08:00:00', 1, 20, 4.8, '12345678901239'),
-- ('Estabelecimento B', '18:00:00', '08:00:00', 2, 20, 4.5, '12385678901239');

-- INSERT Into Employee_type (name) VALUES
-- ('Gerente'),
-- ('Funcionario');

-- INSERT INTO employee (name,email, password, employee_type_id, establishment_id) VALUES
-- ('Funcionario A', 'gg@gmail.com', '123', 2, 2),
-- ('Gerente A', 'dd@gmail.com', '123', 1, 3),
-- ('Cliente Funcionario', 'cliente.funcionario@example.com', '123senha', 2, 3);


