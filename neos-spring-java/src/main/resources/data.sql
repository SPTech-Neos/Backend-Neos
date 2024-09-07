DROP DATABASE blume;
CREATE DATABASE blume;
USE blume;

CREATE TABLE Address(
  address_id INT PRIMARY KEY auto_increment,
  publicPlace VARCHAR(45) NOT NULL,
  city VARCHAR(45) NOT NULL,
  zipCode CHAR(8),
  UF CHAR(2) NOT NULL
);

INSERT INTO Address VALUES(1,'Rua Coração de Maçã', 'São Paulo', '08474230', 'SP');

CREATE TABLE Local(
  local_id INT PRIMARY KEY auto_increment,
  number VARCHAR(5) NOT NULL,
  floor INT,
  complement VARCHAR(45),
  block VARCHAR(2),
  fkAddress INT NOT NULL,
  FOREIGN KEY (fkAddress) REFERENCES Address(address_id) ON DELETE CASCADE
);

INSERT INTO Local VALUES (1, 211, 5, '52', 'C', 1);

CREATE TABLE Phone(
  phone_id INT PRIMARY KEY auto_increment,
  countryCode CHAR(2),
  areaCode CHAR(2),
  number VARCHAR(9)
);

CREATE TABLE Status(
  status_id INT PRIMARY KEY auto_increment,
  name VARCHAR(100),
  type VARCHAR(45)
);

CREATE TABLE Establishment (
  establishment_id INT PRIMARY KEY AUTO_INCREMENT,
  aditumId VARCHAR(400),
  name VARCHAR(45) NOT NULL,
  imgUrl VARCHAR(250),
  fkLocal INT NOT NULL,
  fkPhone INT NOT NULL,
  fkStatus INT,
  horarioEntrada TIME,
  horarioSaida TIME, 
  descricao VARCHAR(255),  
  cnpj VARCHAR(18), 
  FOREIGN KEY (fkPhone) REFERENCES Phone(phone_id) ON DELETE CASCADE,
  FOREIGN KEY (fkStatus) REFERENCES Status(status_id) ON DELETE CASCADE,
  FOREIGN KEY (fkLocal) REFERENCES Local(local_id) ON DELETE CASCADE
);


INSERT INTO Phone VALUES (1, '55', '11', '933357637');
INSERT INTO Status VALUES (1, 'Ativo', 'Estabelecimeto');
INSERT INTO Status VALUES (2, 'Inativo', 'Estabelecimeto');
INSERT INTO Establishment VALUES (1, 'aditumIdTeste', 'KevinSalon', 'url', 1, 1, 1, '08:00:00', '18:00:00', 'Salão de beleza Kevin', '12.345.678/0001-90');
INSERT INTO Establishment VALUES (2, 'aditumIdTeste2', 'LiraSalon', 'url2', 1, 1, 2, '09:00:00', '17:00:00', 'Salão de beleza Lira', '98.765.432/0001-12');

CREATE TABLE ProductType(
  product_type_id INT PRIMARY KEY auto_increment,
  name VARCHAR(45) NOT NULL,
  specification VARCHAR(45) NOT NULL
);

INSERT INTO ProductType VALUES (1, 'Cabelo', 'Shampoo');

CREATE TABLE Product(
  product_id INT PRIMARY KEY auto_increment,
  name VARCHAR(45),
  brand VARCHAR(45),
  imgUrl VARCHAR(100),
  price DECIMAL,
  fkProductType INT,
  fkEstablishment INT,
  fkStatus INT,
  FOREIGN KEY (fkProductType) REFERENCES ProductType(product_type_id) ON DELETE CASCADE,
  FOREIGN KEY (fkStatus) REFERENCES Status(status_id) ON DELETE CASCADE,
  FOREIGN KEY (fkEstablishment) REFERENCES Establishment(establishment_id) ON DELETE CASCADE
);

INSERT INTO Product VALUES (1, 'Shampoo Cachos Suaves', 'Seda', 'img.example', 120.0, 1 , 1, 1);

CREATE TABLE ServiceCategory(
  service_category_id INT PRIMARY KEY auto_increment,
  name VARCHAR(45) NOT NULL
);

INSERT INTO ServiceCategory VALUES(1, 'Cabelo');

CREATE TABLE ServiceType(
  service_type_id INT PRIMARY KEY auto_increment,
  name VARCHAR(45) NOT NULL,
  fkServiceCategory INT,
  FOREIGN KEY (fkServiceCategory) REFERENCES ServiceCategory(service_category_id) ON DELETE CASCADE
);

INSERT INTO ServiceType VALUES(1, 'Corte de cabelo', 1);

CREATE TABLE Service(
  service_id INT PRIMARY KEY auto_increment,
  specification VARCHAR(45) NOT NULL,
  aditumId VARCHAR(300),
  price DOUBLE,
  imgUrl VARCHAR(400),
  fkServiceType INT,
  FOREIGN KEY (fkServiceType) REFERENCES ServiceType(service_type_id) ON DELETE CASCADE
);

INSERT INTO Service VALUES(1, 'Mullet', 'aditumId', 90.0, 'teste', 1);

CREATE TABLE EmployeeType(
  employee_type_id INT PRIMARY KEY auto_increment,
  name VARCHAR(45) NOT NULL
);

INSERT INTO EmployeeType VALUES (1, 'Admin');

CREATE TABLE Employee(
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
  FOREIGN KEY (fkEstablishment) REFERENCES Establishment(establishment_id) ON DELETE CASCADE,
  FOREIGN KEY (fkStatus) REFERENCES Status(status_id) ON DELETE CASCADE,
  FOREIGN KEY (fkPhone) REFERENCES Phone(phone_id) ON DELETE CASCADE,
  FOREIGN KEY (fkEmployeeType) REFERENCES EmployeeType(employee_type_id) ON DELETE CASCADE,
  FOREIGN KEY (fkLocal) REFERENCES Local(local_id) ON DELETE CASCADE
);

INSERT INTO Employee VALUES (1, 'Kevin', 'keviin@email.com', '$2a$10$aii7/bEjM0F1cyEbgG4aQu6kwe0mraOmeXzI2z1/MRDimtgZYM7.W', 'teste', 1, 1, 1, 1, 1);

CREATE TABLE EmployeeServices(
  employee_services_id INT auto_increment,
  hoursSpent INT NOT NULL,
  expertise TINYINT NOT NULL,
  fkEmployee INT,
  fkService INT,
  fkStatus INT,
  FOREIGN KEY (fkEmployee) REFERENCES Employee(employee_id) ON DELETE CASCADE,
  FOREIGN KEY (fkService) REFERENCES Service(service_id) ON DELETE CASCADE,
  FOREIGN KEY (fkStatus) REFERENCES Status(status_id) ON DELETE CASCADE,
  PRIMARY KEY(employee_services_id, fkEmployee, fkService)
);

INSERT INTO EmployeeServices VALUES (1, 2, 5, 1, 1, 1);

CREATE TABLE Client(
  client_id INT PRIMARY KEY auto_increment,
  name VARCHAR(45) NOT NULL,
  email VARCHAR(45) NOT NULL,
  password VARCHAR(300) NOT NULL,
  imgUrl VARCHAR(100),
  cpf CHAR(11),
  fkLocal INT NOT NULL,
  fkPhone INT NOT NULL,
  FOREIGN KEY (fkLocal) REFERENCES Local(local_id) ON DELETE CASCADE,
  FOREIGN KEY (fkPhone) REFERENCES Phone(phone_id) ON DELETE CASCADE
);

INSERT INTO Client VALUES (1, 'Kevin', 'kevin@email.com', '$2a$10$aii7/bEjM0F1cyEbgG4aQu6kwe0mraOmeXzI2z1/MRDimtgZYM7.W', 'teste', '50709903812', 1, 1);

CREATE TABLE Rating(
  rating_id INT PRIMARY KEY auto_increment,
  avaliation INT NOT NULL,
  fkEstablishment INT,
  fkEmployee INT,
  fkService INT,
  fkClient INT NOT NULL,
  fkProduct INT,
  FOREIGN KEY (fkEstablishment) REFERENCES Establishment(establishment_id) ON DELETE CASCADE,
  FOREIGN KEY (fkClient) REFERENCES Client(client_id) ON DELETE CASCADE,
  FOREIGN KEY (fkService) REFERENCES Service(service_id) ON DELETE CASCADE,
  FOREIGN KEY (fkEmployee) REFERENCES Employee(employee_id) ON DELETE CASCADE,
  FOREIGN KEY (fkProduct) REFERENCES Product(product_id) ON DELETE CASCADE
);

INSERT INTO Rating VALUES (1, 5, 1, NULL, NULL, 1, NULL);

/* CREATE TABLE filter(
  filter_id INT auto_increment,
  price FLOAT NOT NULL,
  fkEstablishment INT,
  fkService INT NOT NULL,
  FOREIGN KEY (fkEstablishment) REFERENCES Establishment(establishment_id) ON DELETE CASCADE,
  FOREIGN KEY (fkService) REFERENCES Service(service_id) ON DELETE CASCADE,
  PRIMARY KEY(filter_id, fkEstablishment, fkService)
); */

CREATE TABLE Schedule(
  schedule_id INT auto_increment PRIMARY KEY,
  dateTime DATETIME NOT NULL,
  fkService INT,
  fkStatus INT,
  fkClient INT,
  fkEmployee INT,
  FOREIGN KEY (fkStatus) REFERENCES Status(status_id) ON DELETE CASCADE,
  FOREIGN KEY (fkService) REFERENCES Service(service_id) ON DELETE CASCADE,
  FOREIGN KEY (fkClient) REFERENCES Client(client_id) ON DELETE CASCADE,
  FOREIGN KEY (fkEmployee) REFERENCES Employee(employee_id) ON DELETE CASCADE
);

INSERT INTO Schedule VALUES (1,'2024-08-31 22:00:00',1,1,1,1 );

CREATE TABLE Orders(
  order_id INT auto_increment PRIMARY KEY,
  dateTime DATETIME,
  fkStatus INT,
  fkClient INT,
  FOREIGN KEY (fkStatus) REFERENCES Status(status_id) ON DELETE CASCADE,
  FOREIGN KEY (fkClient) REFERENCES Client(client_id) ON DELETE CASCADE
);

INSERT INTO Orders VALUES (1, '2024-08-31 22:00:00', 1, 1);

CREATE TABLE Market(
  market_id INT auto_increment PRIMARY KEY,
  quantity INT,
  fkProduct INT,
  fkOrder INT,
  FOREIGN KEY (fkProduct) REFERENCES Product(product_id) ON DELETE CASCADE,
  FOREIGN KEY (fkOrder) REFERENCES Orders(order_id) ON DELETE CASCADE

);

INSERT INTO Market VALUES (1, 1, 1, 1);
CREATE TABLE Payment(
  payment_id INT auto_increment PRIMARY KEY,
  datePayment DATETIME,
  fkSchedule INT,
  fkMarket INT,
  fkStatus INT NOT NULL,
  FOREIGN KEY (fkStatus) REFERENCES Status(status_id) ON DELETE CASCADE,
  FOREIGN KEY (fkSchedule) REFERENCES Schedule(schedule_id) ON DELETE CASCADE,
  FOREIGN KEY (fkMarket) REFERENCES Market(market_id) ON DELETE CASCADE
);

INSERT INTO Payment ( datePayment, fkMarket, fkStatus) VALUES ( '2024-08-31 22:00:00', 1, 1);
INSERT INTO Payment ( datePayment, fkSchedule, fkStatus) VALUES ( '2024-08-31 22:00:00', 1, 1);

CREATE TABLE Image(
    image_id INT auto_increment PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    path VARCHAR(250) NOT NULL,
    fileExtension VARCHAR(4),
    fileSize FLOAT,
    fkClient INT,
    fkProduct INT,
    fkService INT,
    fkEmployee INT,
    fkEstablishment INT,
    FOREIGN KEY (fkClient) REFERENCES Client(client_id),
    FOREIGN KEY (fkProduct) REFERENCES Product(product_id),
    FOREIGN KEY (fkService) REFERENCES Service(service_id),
    FOREIGN KEY (fkEmployee) REFERENCES Employee(employee_id),
    FOREIGN KEY (fkEstablishment) REFERENCES Establishment(establishment_id)
);

SELECT * FROM Product;
SELECT * FROM Orders;
SELECT * FROM Market;

SELECT m.quantity, p.datePayment, Product.price FROM Payment as p
  JOIN Market as m ON p.fkMarket= market_id
  JOIN Product ON m.fkProduct = product_id
  JOIN Establishment ON fkEstablishment = establishment_id;

SELECT * FROM Establishment;
SELECT * FROM Rating;
SELECT * FROM Employee;
SELECT * FROM Product;
SELECT * FROM Phone;

SELECT establishment_id, Establishment.name, TRUNCATE(AVG(avaliation), 1) as media FROM Rating
  JOIN Establishment ON fkEstablishment = establishment_id WHERE fkEstablishment IS true GROUP BY fkEstablishment ORDER BY media DESC		;
SELECT AVG(avaliation) as media FROM Rating WHERE fkEstablishment IS NOT NULL ORDER BY media;
