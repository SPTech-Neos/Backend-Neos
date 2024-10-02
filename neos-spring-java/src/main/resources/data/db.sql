DROP DATABASE blume;
CREATE DATABASE blume;
USE blume;

CREATE TABLE IF NOT EXISTS IF NOT EXISTS Address(
  address_id INT PRIMARY KEY auto_increment,
  publicPlace VARCHAR(45) NOT NULL,
  city VARCHAR(45) NOT NULL,
  zipCode CHAR(8),
  UF CHAR(2) NOT NULL
);


CREATE TABLE IF NOT EXISTS IF NOT EXISTS Local(
  local_id INT PRIMARY KEY auto_increment,
  number VARCHAR(5) NOT NULL,
  floor INT,
  complement VARCHAR(45),
  block VARCHAR(2),
  fkAddress INT NOT NULL,
  FOREIGN KEY (fkAddress) REFERENCES Address(address_id) ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS Phone(
  phone_id INT PRIMARY KEY auto_increment,
  countryCode CHAR(2),
  areaCode CHAR(2),
  number VARCHAR(9)
);

CREATE TABLE IF NOT EXISTS Status(
  status_id INT PRIMARY KEY auto_increment,
  name VARCHAR(100),
  type VARCHAR(45)
);

CREATE TABLE IF NOT EXISTS Establishment (
  establishment_id INT PRIMARY KEY AUTO_INCREMENT,
  aditumId VARCHAR(400),
  name VARCHAR(45) NOT NULL,
  imgUrl VARCHAR(250),
  fkLocal INT NOT NULL,
  fkPhone INT NOT NULL,
  fkStatus INT,
  startShift TIME,
  endShift TIME, 
  description VARCHAR(255),  
  cnpj VARCHAR(18), 
  FOREIGN KEY (fkPhone) REFERENCES Phone(phone_id) ON DELETE CASCADE,
  FOREIGN KEY (fkStatus) REFERENCES Status(status_id) ON DELETE CASCADE,
  FOREIGN KEY (fkLocal) REFERENCES Local(local_id) ON DELETE CASCADE
);



CREATE TABLE IF NOT EXISTS ProductType(
  product_type_id INT PRIMARY KEY auto_increment,
  name VARCHAR(45) NOT NULL,
  specification VARCHAR(45) NOT NULL
);


CREATE TABLE IF NOT EXISTS Product(
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


CREATE TABLE IF NOT EXISTS ServiceCategory(
  service_category_id INT PRIMARY KEY auto_increment,
  name VARCHAR(45) NOT NULL
);


CREATE TABLE IF NOT EXISTS ServiceType(
  service_type_id INT PRIMARY KEY auto_increment,
  name VARCHAR(45) NOT NULL,
  fkServiceCategory INT,
  FOREIGN KEY (fkServiceCategory) REFERENCES ServiceCategory(service_category_id) ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS Service(
  service_id INT PRIMARY KEY auto_increment,
  specification VARCHAR(45) NOT NULL,
  aditumId VARCHAR(300),
  price DOUBLE,
  imgUrl VARCHAR(400),
  fkServiceType INT,
  FOREIGN KEY (fkServiceType) REFERENCES ServiceType(service_type_id) ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS EmployeeType(
  employee_type_id INT PRIMARY KEY auto_increment,
  name VARCHAR(45) NOT NULL
);

CREATE TABLE IF NOT EXISTS Employee(
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


CREATE TABLE IF NOT EXISTS EmployeeServices(
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

CREATE TABLE IF NOT EXISTS Client(
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


CREATE TABLE IF NOT EXISTS Rating(
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

/* CREATE TABLE IF NOT EXISTS filter(
  filter_id INT auto_increment,
  price FLOAT NOT NULL,
  fkEstablishment INT,
  fkService INT NOT NULL,
  FOREIGN KEY (fkEstablishment) REFERENCES Establishment(establishment_id) ON DELETE CASCADE,
  FOREIGN KEY (fkService) REFERENCES Service(service_id) ON DELETE CASCADE,
  PRIMARY KEY(filter_id, fkEstablishment, fkService)
); */

CREATE TABLE IF NOT EXISTS Schedule(
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


CREATE TABLE IF NOT EXISTS Orders(
  order_id INT auto_increment PRIMARY KEY,
  dateTime DATETIME,
  fkStatus INT,
  fkClient INT,
  FOREIGN KEY (fkStatus) REFERENCES Status(status_id) ON DELETE CASCADE,
  FOREIGN KEY (fkClient) REFERENCES Client(client_id) ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS Market(
  market_id INT auto_increment PRIMARY KEY,
  quantity INT,
  fkProduct INT,
  fkOrder INT,
  FOREIGN KEY (fkProduct) REFERENCES Product(product_id) ON DELETE CASCADE,
  FOREIGN KEY (fkOrder) REFERENCES Orders(order_id) ON DELETE CASCADE

);

CREATE TABLE IF NOT EXISTS Payment(
  payment_id INT auto_increment PRIMARY KEY,
  datePayment DATETIME,
  fkSchedule INT,
  fkMarket INT,
  fkStatus INT NOT NULL,
  FOREIGN KEY (fkStatus) REFERENCES Status(status_id) ON DELETE CASCADE,
  FOREIGN KEY (fkSchedule) REFERENCES Schedule(schedule_id) ON DELETE CASCADE,
  FOREIGN KEY (fkMarket) REFERENCES Market(market_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Image(
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