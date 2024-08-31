DROP DATABASE blume;
CREATE DATABASE blume;
USE blume;

CREATE TABLE address(
  address_id INT PRIMARY KEY auto_increment,
  publicPlace VARCHAR(45) NOT NULL,
  city VARCHAR(45) NOT NULL,
  UF CHAR(2) NOT NULL
);

INSERT INTO address VALUES(1,'Rua Coração de Maçã', 'São Paulo', 'SP');

CREATE TABLE local(
  local_id INT PRIMARY KEY auto_increment,
  number INT NOT NULL,
  floor INT,
  complement VARCHAR(45),
  block VARCHAR(2),
  fkAddress INT NOT NULL,
  FOREIGN KEY (fkAddress) REFERENCES address(address_id) ON DELETE CASCADE
);

INSERT INTO local VALUES (1, 211, 5, '52', 'C', 1);

CREATE TABLE phone(
	phone_id INT PRIMARY KEY auto_increment,
    countryCode CHAR(2),
    areaCode CHAR(2),
	number VARCHAR(9)
);

CREATE TABLE status(
  status_id INT PRIMARY KEY auto_increment,
  name VARCHAR(100),
  type VARCHAR(45)
);

CREATE TABLE establishment(
  establishment_id INT PRIMARY KEY auto_increment,
  aditumId VARCHAR(400),
  name VARCHAR(45) NOT NULL,
  imgUrl VARCHAR(250),
  fkLocal INT NOT NULL,
  fkPhone INT NOT NULL,
  fkStatus INT,
  FOREIGN KEY (fkPhone) REFERENCES phone(phone_id) ON DELETE CASCADE,
  FOREIGN KEY (fkStatus) REFERENCES status(status_id) ON DELETE CASCADE,
  FOREIGN KEY (fkLocal) REFERENCES local(local_id) ON DELETE CASCADE
);

INSERT INTO phone VALUES (1, '55', '11', '933357637');
INSERT INTO status VALUES (1, 'Ativo', 'Estabelecimeto');
INSERT INTO status VALUES (2, 'Inativo', 'Estabelecimeto');
INSERT INTO establishment VALUES (1, 'aditumIdTeste', 'KevinSalon', 'url', 1,1,1);
INSERT INTO establishment VALUES (2, 'aditumIdTeste2', 'LiraSalon', 'url2', 1,1,2);

CREATE TABLE productType(
  product_type_id INT PRIMARY KEY auto_increment,
  name VARCHAR(45) NOT NULL,
  specification VARCHAR(45) NOT NULL
);

CREATE TABLE product(
  product_id INT PRIMARY KEY auto_increment,
  name VARCHAR(45),
  brand VARCHAR(45),
  imgUrl VARCHAR(100),
  price DECIMAL,
  fkProductType INT,
  fkEstablishment INT,
  fkStatus INT,
  FOREIGN KEY (fkProductType) REFERENCES productType(product_type_id) ON DELETE CASCADE,
  FOREIGN KEY (fkStatus) REFERENCES status(status_id) ON DELETE CASCADE,
  FOREIGN KEY (fkEstablishment) REFERENCES establishment(establishment_id) ON DELETE CASCADE
);

CREATE TABLE serviceCategory(
  service_category_id INT PRIMARY KEY auto_increment,
  name VARCHAR(45) NOT NULL
);

INSERT INTO serviceCategory VALUES(1, 'Cabelo');

CREATE TABLE serviceType(
  service_type_id INT PRIMARY KEY auto_increment,
  name VARCHAR(45) NOT NULL,
  fkServiceCategory INT,
  FOREIGN KEY (fkServiceCategory) REFERENCES serviceCategory(service_category_id) ON DELETE CASCADE
);

INSERT INTO serviceType VALUES(1, 'Corte de cabelo', 1);

CREATE TABLE service(
  service_id INT PRIMARY KEY auto_increment,
  specification VARCHAR(45) NOT NULL,
  aditumId VARCHAR(300),
  price DOUBLE,
  imgUrl VARCHAR(400),
  fkServiceType INT,
  FOREIGN KEY (fkServiceType) REFERENCES serviceType(service_type_id) ON DELETE CASCADE
);

INSERT INTO service VALUES(1, 'Mullet', 'aditumId', 90.0, 'teste', 1);

CREATE TABLE employeeType(
  employee_type_id INT PRIMARY KEY auto_increment,
  name VARCHAR(45) NOT NULL
);

INSERT INTO employeeType VALUES (1, 'Admin');

CREATE TABLE employee(
  employee_id INT PRIMARY KEY auto_increment,
  name VARCHAR(45) NOT NULL,
  email VARCHAR(45) NOT NULL,
  password VARCHAR(300) NOT NULL,
  imgUrl VARCHAR(100),
  fkEstablishment INT NOT NULL,
  fkEmployeeType INT NOT NULL,
  fkPhone INT NOT NULL,
  fkLocal INT NOT NULL,
  fkStatus INT NOT NULL,
  FOREIGN KEY (fkEstablishment) REFERENCES establishment(establishment_id) ON DELETE CASCADE,
  FOREIGN KEY (fkStatus) REFERENCES status(status_id) ON DELETE CASCADE,
  FOREIGN KEY (fkPhone) REFERENCES phone(phone_id) ON DELETE CASCADE,
  FOREIGN KEY (fkEmployeeType) REFERENCES employeeType(employee_type_id) ON DELETE CASCADE,
  FOREIGN KEY (fkLocal) REFERENCES local(local_id) ON DELETE CASCADE
);

INSERT INTO employee VALUES (1, 'Kevin', 'keviin@email.com', '1245senha', 'teste', 1, 1, 1, 1, 1);

CREATE TABLE employeeServices(
  employee_services_id INT auto_increment,
  hoursSpent DATETIME NOT NULL,
  expertise TINYINT NOT NULL,
  fkEmployee INT,
  fkService INT,
  fkStatus INT,
  FOREIGN KEY (fkEmployee) REFERENCES employee(employee_id) ON DELETE CASCADE,
  FOREIGN KEY (fkService) REFERENCES service(service_id) ON DELETE CASCADE,
  FOREIGN KEY (fkStatus) REFERENCES status(status_id) ON DELETE CASCADE,
  PRIMARY KEY(employee_services_id, fkEmployee, fkService)
);

CREATE TABLE client(
  client_id INT PRIMARY KEY auto_increment,
  name VARCHAR(45) NOT NULL,
  email VARCHAR(45) NOT NULL,
  password VARCHAR(300) NOT NULL,
  imgUrl VARCHAR(100),
  cpf CHAR(11),
  fkLocal INT NOT NULL,
  fkPhone INT NOT NULL,
  FOREIGN KEY (fkLocal) REFERENCES local(local_id) ON DELETE CASCADE,
  FOREIGN KEY (fkPhone) REFERENCES phone(phone_id) ON DELETE CASCADE
);

INSERT INTO client VALUES (1, 'Kevin', 'kevin@email.com', '123Senha', 'teste', '50709903812', 1, 1);

CREATE TABLE rating(
  rating_id INT PRIMARY KEY auto_increment,
  avaliation INT NOT NULL,
  fkEstablishment INT,
  fkEmployee INT,
  fkService INT,
  fkClient INT NOT NULL,
  fkProduct INT,
  FOREIGN KEY (fkEstablishment) REFERENCES establishment(establishment_id) ON DELETE CASCADE,
  FOREIGN KEY (fkClient) REFERENCES client(client_id) ON DELETE CASCADE,
  FOREIGN KEY (fkService) REFERENCES service(service_id) ON DELETE CASCADE,
  FOREIGN KEY (fkEmployee) REFERENCES employee(employee_id) ON DELETE CASCADE,
  FOREIGN KEY (fkProduct) REFERENCES product(product_id) ON DELETE CASCADE
);

INSERT INTO rating VALUES (1, 5, 1, NULL, NULL, 1, NULL);

/* CREATE TABLE filter(
  filter_id INT auto_increment,
  price FLOAT NOT NULL,
  fkEstablishment INT,
  fkService INT NOT NULL,
  FOREIGN KEY (fkEstablishment) REFERENCES establishment(establishment_id) ON DELETE CASCADE,
  FOREIGN KEY (fkService) REFERENCES service(service_id) ON DELETE CASCADE,
  PRIMARY KEY(filter_id, fkEstablishment, fkService)
); */

CREATE TABLE schedule(
  schedule_id INT auto_increment PRIMARY KEY,
  dateTime DATETIME NOT NULL,
  value DECIMAL,
  fkService INT,
  fkStatus INT,
  fkClient INT,
  fkEmployee INT,
  FOREIGN KEY (fkStatus) REFERENCES status(status_id) ON DELETE CASCADE,
  FOREIGN KEY (fkService) REFERENCES service(service_id) ON DELETE CASCADE,
  FOREIGN KEY (fkClient) REFERENCES client(client_id) ON DELETE CASCADE,
  FOREIGN KEY (fkEmployee) REFERENCES employee(employee_id) ON DELETE CASCADE
);

CREATE TABLE orders(
	order_id INT auto_increment PRIMARY KEY,
    dateTime DATETIME,
    fkStatus INT,
    fkClient INT,
    FOREIGN KEY (fkStatus) REFERENCES status(status_id) ON DELETE CASCADE,
    FOREIGN KEY (fkClient) REFERENCES client(client_id) ON DELETE CASCADE
);

CREATE TABLE market(
	market_id INT auto_increment PRIMARY KEY,
    quantity INT,
    fkProduct INT,
    fkOrder INT,
    FOREIGN KEY (fkProduct) REFERENCES product(product_id) ON DELETE CASCADE,
    FOREIGN KEY (fkOrder) REFERENCES orders(order_id) ON DELETE CASCADE

);
CREATE TABLE payment(
  payment_id INT auto_increment PRIMARY KEY,
  total DECIMAL,
  datePayment DATETIME,
  fkSchedule INT,
  fkOrder INT,
  fkStatus INT,
  FOREIGN KEY (fkStatus) REFERENCES status(status_id) ON DELETE CASCADE,
  FOREIGN KEY (fkSchedule) REFERENCES schedule(schedule_id) ON DELETE CASCADE,
  FOREIGN KEY (fkOrder) REFERENCES orders(order_id) ON DELETE CASCADE
);

SELECT * FROM establishment;
SELECT * FROM rating;
SELECT * FROM employee;
SELECT * FROM product;
SELECT * FROM phone;

SELECT establishment_id, establishment.name, TRUNCATE(AVG(avaliation), 1) as media FROM rating  JOIN establishment ON fkEstablishment = establishment_id WHERE fkEstablishment IS true GROUP BY fkEstablishment ORDER BY media DESC		;
SELECT AVG(avaliation) as media FROM rating WHERE fkEstablishment IS NOT NULL ORDER BY media;
