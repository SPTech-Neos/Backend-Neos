CREATE DATABASE blume;
USE blume;

CREATE TABLE company (
  company_id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(45) NOT NULL,
  cnpj CHAR(14) NOT NULL
);

CREATE TABLE address (
  address_id INT PRIMARY KEY AUTO_INCREMENT,
  street VARCHAR(45) NOT NULL,
  city VARCHAR(45) NOT NULL,
  state CHAR(2) NOT NULL
);

CREATE TABLE location (
  location_id INT PRIMARY KEY AUTO_INCREMENT,
  number INT NOT NULL,
  floor INT,
  block VARCHAR(2),
  complement VARCHAR(45),
  fk_address INT NOT NULL,
  FOREIGN KEY (fk_address) REFERENCES address(address_id)
);

CREATE TABLE establishment (
  establishment_id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(45) NOT NULL,
  fk_company INT,
  fk_location INT NOT NULL,
  FOREIGN KEY (fk_company) REFERENCES company(company_id),
  FOREIGN KEY (fk_location) REFERENCES location(location_id)
);

CREATE TABLE product_type (
  type_id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(45) NOT NULL,
  specification VARCHAR(45) NOT NULL
);

CREATE TABLE product (
  product_id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(45),
  brand VARCHAR(45),
  fk_product_type INT,
  fk_establishment INT,
  FOREIGN KEY (fk_product_type) REFERENCES product_type(type_id),
  FOREIGN KEY (fk_establishment) REFERENCES establishment(establishment_id)
);

CREATE TABLE client (
  client_id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(45) NOT NULL,
  email VARCHAR(45) NOT NULL,
  password VARCHAR(45) NOT NULL,
  fk_location INT NOT NULL,
  FOREIGN KEY (fk_location) REFERENCES location(location_id)
);

CREATE TABLE employee_type (
  type_id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(45) NOT NULL
);

CREATE TABLE employee (
  employee_id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(45) NOT NULL,
  email VARCHAR(45) NOT NULL,
  password VARCHAR(45) NOT NULL,
  fk_establishment INT NOT NULL,
  fk_employee_type INT NOT NULL,
  FOREIGN KEY (fk_establishment) REFERENCES establishment(establishment_id),
  FOREIGN KEY (fk_employee_type) REFERENCES employee_type(type_id)
);

CREATE TABLE service_category (
  category_id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(45) NOT NULL
);

CREATE TABLE service_type (
  type_id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(45) NOT NULL,
  fk_category INT,
  FOREIGN KEY (fk_category) REFERENCES service_category(category_id)
);

CREATE TABLE service (
  service_id INT PRIMARY KEY AUTO_INCREMENT,
  specification VARCHAR(45) NOT NULL,
  fk_service_type INT,
  FOREIGN KEY (fk_service_type) REFERENCES service_type(type_id)
);

CREATE TABLE employee_services (
  employee_services_id INT,
  hours_spent DATETIME NOT NULL,
  expertise TINYINT NOT NULL,
  fk_employee INT,
  fk_service INT,
  FOREIGN KEY (fk_employee) REFERENCES employee(employee_id),
  FOREIGN KEY (fk_service) REFERENCES service(service_id),
  PRIMARY KEY (employee_services_id, fk_employee, fk_service)
);

CREATE TABLE filter (
  filter_id INT,
  price DOUBLE NOT NULL,
  fk_establishment INT,
  fk_service INT NOT NULL,
  FOREIGN KEY (fk_establishment) REFERENCES establishment(establishment_id),
  FOREIGN KEY (fk_service) REFERENCES service(service_id),
  PRIMARY KEY (filter_id, fk_establishment, fk_service)
);

CREATE TABLE discount_type (
  type_id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(45)
);

CREATE TABLE coupon (
  coupon_id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(45)
);

CREATE TABLE available_coupon (
  available_coupon_id INT PRIMARY KEY AUTO_INCREMENT,
  expiration_date DATETIME NOT NULL,
  discount DOUBLE NOT NULL,
  fk_discount_type INT NOT NULL,
  fk_establishment INT NOT NULL,
  fk_coupon INT NOT NULL,
  FOREIGN KEY (fk_discount_type) REFERENCES discount_type(type_id),
  FOREIGN KEY (fk_establishment) REFERENCES establishment(establishment_id),
  FOREIGN KEY (fk_coupon) REFERENCES coupon(coupon_id)
);

CREATE TABLE appointment (
  appointment_id INT,
  date_time DATETIME NOT NULL,
  fk_service INT,
  fk_client INT,
  fk_employee INT,
  FOREIGN KEY (fk_service) REFERENCES service(service_id),
  FOREIGN KEY (fk_client) REFERENCES client(client_id),
  FOREIGN KEY (fk_employee) REFERENCES employee(employee_id),
  PRIMARY KEY (fk_service, fk_client, fk_employee)
);