DROP DATABASE blume;
CREATE DATABASE blume;
USE blume;

CREATE TABLE IF NOT EXISTS  Address(
  address_id INT PRIMARY KEY auto_increment,
  public_place VARCHAR(45) NOT NULL,
  city VARCHAR(45) NOT NULL,
  zip_code CHAR(8),
  uf CHAR(2) NOT NULL
);


CREATE TABLE IF NOT EXISTS  Local(
  local_id INT PRIMARY KEY auto_increment,
  number VARCHAR(5) NOT NULL,
  floor INT,
  complement VARCHAR(45),
  block VARCHAR(2),
  fk_address INT NOT NULL,
  FOREIGN KEY (fk_address) REFERENCES Address(address_id) ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS Phone(
  phone_id INT PRIMARY KEY auto_increment,
  country_code CHAR(2),
  area_code CHAR(2),
  number VARCHAR(9)
);

CREATE TABLE IF NOT EXISTS Status(
  status_id INT PRIMARY KEY auto_increment,
  name VARCHAR(100),
  type VARCHAR(45)
);

CREATE TABLE IF NOT EXISTS Establishment (
  establishment_id INT PRIMARY KEY AUTO_INCREMENT,
  aditum_id VARCHAR(400),
  name VARCHAR(45) NOT NULL,
  imgUrl VARCHAR(250),
  fk_local INT NOT NULL,
  fk_phone INT NOT NULL,
  fk_status INT,
  start_shift TIME,
  end_shift TIME, 
  description VARCHAR(255),  
  cnpj VARCHAR(18), 
  FOREIGN KEY (fk_phone) REFERENCES Phone(phone_id) ON DELETE CASCADE,
  FOREIGN KEY (fk_status) REFERENCES Status(status_id) ON DELETE CASCADE,
  FOREIGN KEY (fk_local) REFERENCES Local(local_id) ON DELETE CASCADE
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
  img_url VARCHAR(100),
  price DECIMAL,
  fk_product_type INT,
  fk_establishment INT,
  fk_status INT,
  FOREIGN KEY (fk_product_type) REFERENCES ProductType(product_type_id) ON DELETE CASCADE,
  FOREIGN KEY (fk_status) REFERENCES Status(status_id) ON DELETE CASCADE,
  FOREIGN KEY (fk_establishment) REFERENCES Establishment(establishment_id) ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS ServiceCategory(
  service_category_id INT PRIMARY KEY auto_increment,
  name VARCHAR(45) NOT NULL
);


CREATE TABLE IF NOT EXISTS ServiceType(
  service_type_id INT PRIMARY KEY auto_increment,
  name VARCHAR(45) NOT NULL,
  fk_service_category INT,
  FOREIGN KEY (fk_service_category) REFERENCES ServiceCategory(service_category_id) ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS Service(
  service_id INT PRIMARY KEY auto_increment,
  specification VARCHAR(45) NOT NULL,
  aditum_id VARCHAR(300),
  price DOUBLE,
  img_url VARCHAR(400),
  fk_service_type INT,
  fk_status INT,
  FOREIGN KEY (fk_service_type) REFERENCES ServiceType(service_type_id) ON DELETE CASCADE,
  FOREIGN KEY (fk_status) REFERENCES Status(status_id)
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
  img_url VARCHAR(100),
  fk_establishment INT NOT NULL,
  fk_employee_type INT NOT NULL,
  fk_phone INT NOT NULL,
  fk_local INT NOT NULL,
  fk_status INT NOT NULL,
  FOREIGN KEY (fk_establishment) REFERENCES Establishment(establishment_id) ON DELETE CASCADE,
  FOREIGN KEY (fk_status) REFERENCES Status(status_id) ON DELETE CASCADE,
  FOREIGN KEY (fk_phone) REFERENCES Phone(phone_id) ON DELETE CASCADE,
  FOREIGN KEY (fk_employee_type) REFERENCES EmployeeType(employee_type_id) ON DELETE CASCADE,
  FOREIGN KEY (fk_local) REFERENCES Local(local_id) ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS EmployeeServices(
  employee_services_id INT auto_increment,
  hours_spent INT NOT NULL,
  expertise TINYINT NOT NULL,
  fk_employee INT,
  fk_service INT,
  fk_status INT,
  FOREIGN KEY (fk_employee) REFERENCES Employee(employee_id) ON DELETE CASCADE,
  FOREIGN KEY (fk_service) REFERENCES Service(service_id) ON DELETE CASCADE,
  FOREIGN KEY (fk_status) REFERENCES Status(status_id) ON DELETE CASCADE,
  PRIMARY KEY(employee_services_id, fk_employee, fk_service)
);



CREATE TABLE IF NOT EXISTS Client(
  client_id INT PRIMARY KEY auto_increment,
  name VARCHAR(45) NOT NULL,
  email VARCHAR(45) NOT NULL,
  password VARCHAR(300) NOT NULL,
  imgUrl VARCHAR(100),
  cpf CHAR(11),
  fk_local INT NOT NULL,
  fk_phone INT NOT NULL,
  FOREIGN KEY (fk_local) REFERENCES Local(local_id) ON DELETE CASCADE,
  FOREIGN KEY (fk_phone) REFERENCES Phone(phone_id) ON DELETE CASCADE
);



CREATE TABLE IF NOT EXISTS Rating(
  rating_id INT PRIMARY KEY auto_increment,
  avaliation INT NOT NULL,
  fk_establishment INT,
  fk_employee INT,
  fk_service INT,
  fk_client INT NOT NULL,
  fk_product INT,
  FOREIGN KEY (fk_establishment) REFERENCES Establishment(establishment_id) ON DELETE CASCADE,
  FOREIGN KEY (fk_client) REFERENCES Client(client_id) ON DELETE CASCADE,
  FOREIGN KEY (fk_service) REFERENCES Service(service_id) ON DELETE CASCADE,
  FOREIGN KEY (fk_employee) REFERENCES Employee(employee_id) ON DELETE CASCADE,
  FOREIGN KEY (fk_product) REFERENCES Product(product_id) ON DELETE CASCADE
);



/* CREATE TABLE IF NOT EXISTS filter(
  filter_id INT auto_increment,
  price FLOAT NOT NULL,
  fk_establishment INT,
  fk_service INT NOT NULL,
  FOREIGN KEY (fk_establishment) REFERENCES Establishment(establishment_id) ON DELETE CASCADE,
  FOREIGN KEY (fk_service) REFERENCES Service(service_id) ON DELETE CASCADE,
  PRIMARY KEY(filter_id, fk_establishment, fk_service)
); */

CREATE TABLE IF NOT EXISTS Schedule(
  schedule_id INT auto_increment PRIMARY KEY,
  date_time DATETIME NOT NULL,
  fk_service INT,
  fk_status INT,
  fk_client INT,
  fk_employee INT,
  FOREIGN KEY (fk_status) REFERENCES Status(status_id) ON DELETE CASCADE,
  FOREIGN KEY (fk_service) REFERENCES Service(service_id) ON DELETE CASCADE,
  FOREIGN KEY (fk_client) REFERENCES Client(client_id) ON DELETE CASCADE,
  FOREIGN KEY (fk_employee) REFERENCES Employee(employee_id) ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS Orders(
  order_id INT auto_increment PRIMARY KEY,
  date_time DATETIME,
  fk_status INT,
  fk_client INT,
  FOREIGN KEY (fk_status) REFERENCES Status(status_id) ON DELETE CASCADE,
  FOREIGN KEY (fk_client) REFERENCES Client(client_id) ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS Market(
  market_id INT auto_increment PRIMARY KEY,
  quantity INT,
  fk_product INT,
  fk_order INT,
  FOREIGN KEY (fk_product) REFERENCES Product(product_id) ON DELETE CASCADE,
  FOREIGN KEY (fk_order) REFERENCES Orders(order_id) ON DELETE CASCADE

);

CREATE TABLE IF NOT EXISTS Payment(
  payment_id INT auto_increment PRIMARY KEY,
  date_payment DATETIME,
  fk_schedule INT,
  fk_market INT,
  fk_status INT NOT NULL,
  FOREIGN KEY (fk_status) REFERENCES Status(status_id) ON DELETE CASCADE,
  FOREIGN KEY (fk_schedule) REFERENCES Schedule(schedule_id) ON DELETE CASCADE,
  FOREIGN KEY (fk_market) REFERENCES Market(market_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Image(
    image_id INT auto_increment PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    path VARCHAR(250) NOT NULL,
    file_extension VARCHAR(4),
    file_size FLOAT,
    fk_client INT,
    fk_product INT,
    fk_service INT,
    fk_employee INT,
    fk_establishment INT,
    FOREIGN KEY (fk_client) REFERENCES Client(client_id),
    FOREIGN KEY (fk_product) REFERENCES Product(product_id),
    FOREIGN KEY (fk_service) REFERENCES Service(service_id),
    FOREIGN KEY (fk_employee) REFERENCES Employee(employee_id),
    FOREIGN KEY (fk_establishment) REFERENCES Establishment(establishment_id)
);