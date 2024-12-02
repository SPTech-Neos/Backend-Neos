DROP DATABASE blume;
CREATE DATABASE blume;
USE blume;

CREATE TABLE IF NOT EXISTS address (
  address_id INT PRIMARY KEY auto_increment,
  public_place VARCHAR(45) NOT NULL,
  city VARCHAR(45) NOT NULL,
  zip_code CHAR(8),
  uf CHAR(2) NOT NULL
);

CREATE TABLE IF NOT EXISTS local (
  local_id INT PRIMARY KEY auto_increment,
  number VARCHAR(5) NOT NULL,
  floor INT,
  complement VARCHAR(45),
  block VARCHAR(2),
  fk_address INT NOT NULL,
  FOREIGN KEY (fk_address) REFERENCES address(address_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS phone (
  phone_id INT PRIMARY KEY auto_increment,
  country_code CHAR(3),
  area_code CHAR(3),
  number VARCHAR(9)
);

CREATE TABLE IF NOT EXISTS status (
  status_id INT PRIMARY KEY auto_increment,
  name VARCHAR(100),
  type VARCHAR(45)
);

CREATE TABLE IF NOT EXISTS establishment (
  establishment_id INT PRIMARY KEY AUTO_INCREMENT,
  aditum_id VARCHAR(400),
  name VARCHAR(45) NOT NULL,
  img_url VARCHAR(500),
  fk_local INT NOT NULL,
  fk_phone INT NOT NULL,
  fk_status INT,
  start_shift TIME,
  end_shift TIME, 
  description VARCHAR(255),  
  cnpj VARCHAR(18), 
  FOREIGN KEY (fk_phone) REFERENCES phone(phone_id) ON DELETE CASCADE,
  FOREIGN KEY (fk_status) REFERENCES status(status_id) ON DELETE CASCADE,
  FOREIGN KEY (fk_local) REFERENCES local(local_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS product_type (
  product_type_id INT PRIMARY KEY auto_increment,
  name VARCHAR(45) NOT NULL,
  specification VARCHAR(45) NOT NULL
);

CREATE TABLE IF NOT EXISTS product (
  product_id INT PRIMARY KEY auto_increment,
  name VARCHAR(45),
  aditum_id VARCHAR(300),
  brand VARCHAR(45),
  img_url VARCHAR(500),
  price DECIMAL,
  fk_product_type INT,
  fk_establishment INT,
  fk_status INT,
  FOREIGN KEY (fk_product_type) REFERENCES product_type(product_type_id) ON DELETE CASCADE,
  FOREIGN KEY (fk_status) REFERENCES status(status_id) ON DELETE CASCADE,
  FOREIGN KEY (fk_establishment) REFERENCES establishment(establishment_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS service_category (
  service_category_id INT PRIMARY KEY auto_increment,
  name VARCHAR(45) NOT NULL
);

CREATE TABLE IF NOT EXISTS service_type (
  service_type_id INT PRIMARY KEY auto_increment,
  name VARCHAR(45) NOT NULL,
  fk_service_category INT,
  FOREIGN KEY (fk_service_category) REFERENCES service_category(service_category_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS service (
  service_id INT PRIMARY KEY auto_increment,
  specification VARCHAR(45) NOT NULL,
  aditum_id VARCHAR(300),
  price DOUBLE,
  img_url VARCHAR(500),
  fk_service_type INT,
  fk_status INT,
  FOREIGN KEY (fk_service_type) REFERENCES service_type(service_type_id) ON DELETE CASCADE,
  FOREIGN KEY (fk_status) REFERENCES status(status_id)
);

CREATE TABLE IF NOT EXISTS employee_type (
  employee_type_id INT PRIMARY KEY auto_increment,
  name VARCHAR(45) NOT NULL
);

CREATE TABLE IF NOT EXISTS employee (
  employee_id INT PRIMARY KEY auto_increment,
  name VARCHAR(45) NOT NULL,
  email VARCHAR(45) NOT NULL,
  password VARCHAR(300) NOT NULL,
  img_url VARCHAR(500),
  fk_establishment INT NOT NULL,
  fk_employee_type INT NOT NULL,
  fk_phone INT NOT NULL,
  fk_local INT NOT NULL,
  fk_status INT NOT NULL,
  FOREIGN KEY (fk_establishment) REFERENCES establishment(establishment_id) ON DELETE CASCADE,
  FOREIGN KEY (fk_status) REFERENCES status(status_id) ON DELETE CASCADE,
  FOREIGN KEY (fk_phone) REFERENCES phone(phone_id) ON DELETE CASCADE,
  FOREIGN KEY (fk_employee_type) REFERENCES employee_type(employee_type_id) ON DELETE CASCADE,
  FOREIGN KEY (fk_local) REFERENCES local(local_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS employee_services (
  employee_services_id INT auto_increment,
  hours_spent INT NOT NULL,
  expertise TINYINT NOT NULL,
  fk_employee INT,
  fk_service INT,
  fk_status INT,
  FOREIGN KEY (fk_employee) REFERENCES employee(employee_id) ON DELETE CASCADE,
  FOREIGN KEY (fk_service) REFERENCES service(service_id) ON DELETE CASCADE,
  FOREIGN KEY (fk_status) REFERENCES status(status_id) ON DELETE CASCADE,
  PRIMARY KEY(employee_services_id, fk_employee, fk_service)
);

CREATE TABLE IF NOT EXISTS client (
  client_id INT PRIMARY KEY auto_increment,
  name VARCHAR(45) NOT NULL,
  email VARCHAR(45) NOT NULL,
  password VARCHAR(300) NOT NULL,
  img_url VARCHAR(500),
  cpf CHAR(11),
  fk_local INT NOT NULL,
  fk_phone INT NOT NULL,
  FOREIGN KEY (fk_local) REFERENCES local(local_id) ON DELETE CASCADE,
  FOREIGN KEY (fk_phone) REFERENCES phone(phone_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS rating (
  rating_id INT PRIMARY KEY auto_increment,
  avaliation INT NOT NULL,
  fk_establishment INT,
  fk_employee INT,
  fk_service INT,
  fk_client INT NOT NULL,
  fk_product INT,
  FOREIGN KEY (fk_establishment) REFERENCES establishment(establishment_id) ON DELETE CASCADE,
  FOREIGN KEY (fk_client) REFERENCES client(client_id) ON DELETE CASCADE,
  FOREIGN KEY (fk_service) REFERENCES service(service_id) ON DELETE CASCADE,
  FOREIGN KEY (fk_employee) REFERENCES employee(employee_id) ON DELETE CASCADE,
  FOREIGN KEY (fk_product) REFERENCES product(product_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS schedule (
  schedule_id INT auto_increment PRIMARY KEY,
  date_time DATETIME NOT NULL,
  fk_service INT,
  fk_status INT,
  fk_client INT,
  fk_employee INT,
  FOREIGN KEY (fk_status) REFERENCES status(status_id) ON DELETE CASCADE,
  FOREIGN KEY (fk_service) REFERENCES service(service_id) ON DELETE CASCADE,
  FOREIGN KEY (fk_client) REFERENCES client(client_id) ON DELETE CASCADE,
  FOREIGN KEY (fk_employee) REFERENCES employee(employee_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS orders (
  order_id INT auto_increment PRIMARY KEY,
  date_time DATETIME,
  fk_status INT,
  fk_client INT,
  FOREIGN KEY (fk_status) REFERENCES status(status_id) ON DELETE CASCADE,
  FOREIGN KEY (fk_client) REFERENCES client(client_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS market (
  market_id INT auto_increment PRIMARY KEY,
  quantity INT,
  fk_product INT,
  fk_order INT,
  FOREIGN KEY (fk_product) REFERENCES product(product_id) ON DELETE CASCADE,
  FOREIGN KEY (fk_order) REFERENCES orders(order_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS payment (
  payment_id INT auto_increment PRIMARY KEY,
  date_payment DATETIME,
  fk_schedule INT,
  fk_market INT,
  fk_status INT NOT NULL,
  FOREIGN KEY (fk_status) REFERENCES status(status_id) ON DELETE CASCADE,
  FOREIGN KEY (fk_schedule) REFERENCES schedule(schedule_id) ON DELETE CASCADE,
  FOREIGN KEY (fk_market) REFERENCES market(market_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS image (
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
  FOREIGN KEY (fk_client) REFERENCES client(client_id),
  FOREIGN KEY (fk_product) REFERENCES product(product_id),
  FOREIGN KEY (fk_service) REFERENCES service(service_id),
  FOREIGN KEY (fk_employee) REFERENCES employee(employee_id),
  FOREIGN KEY (fk_establishment) REFERENCES establishment(establishment_id)
);
