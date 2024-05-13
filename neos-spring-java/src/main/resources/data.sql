-- -- massa de dados para teste do sistema     

INSERT INTO address (street, city, state) VALUES
('Rua A', 'Cidade A', 'AA'),
('Rua B', 'Cidade B', 'BB'),
('Rua C', 'Cidade C', 'CC'),
('Rua D', 'Cidade D', 'DD');

 INSERT INTO local (number, floor, bloc, complement, address_id_address) VALUES
 (1, 1, 'A', 'Casa', 1),
 (2, 2, 'B', 'Apartamento', 2),
 (3, 3, 'C', 'Casa', 3),
 (4, 4, 'D', 'Apartamento', 4);


INSERT INTO client (name, email, password) VALUES
('Cliente A', 'cliente.a@example.com', 'senha123'),
('Cliente B', 'cliente.b@example.com', 'senha456'),
('Cliente C', 'cliente.c@example.com', 'senha789'),
('Cliente D', 'cliente.d@example.com', 'senhaabc'),
('Cliente T', 'cliente.T@example.com', '$2a$10$sgcxPkMU39rBgarTZKdRBO.zFd6VcwyjSaQN15IhbZaqt5//Sosra'),
('Cliente Funcionario', 'cliente.funcionario@example.com', '$2a$10$LKDiQGRIF9YiGivySF3UreeGcfbEL8XucUSNU6aUxEe9foyZhg9lC');

INSERT INTO establishment (name, local_id_local) VALUES
('Estabelecimento A',1),
('Estabelecimento B',2);

INSERT INTO employee_type (name) VALUES
('Gerente'),
('Funcionario');

INSERT INTO employee (name, email, password, employee_type_id, establishment_id_establishment) VALUES
('Funcionario A', 'gg@gmail.com', '123', 2, 1),
('Gerente A', 'dd@gmail.com', '123', 1, 1);

INSERT INTO company (name, cnpj) VALUES
('lirasalon', '222222222'),
('luizsalon', '111111111'),
('yukiosalon', '0000000000');

INSERT INTO service_category (name) VALUES ('Categoria 1');
INSERT INTO service_category (name) VALUES ('Categoria 2');

INSERT INTO service_type (name, service_category_id) VALUES ('Tipo de Serviço 1', 1);
INSERT INTO service_type (name, service_category_id) VALUES ('Tipo de Serviço 2', 2);

INSERT INTO service (specification, service_type_id) VALUES ('Serviço 1', 1);
INSERT INTO service (specification, service_type_id) VALUES ('Serviço 2', 2);


INSERT INTO filter (price, establishment_id_establishment, service_id) VALUES (50.0, 1, 1);
INSERT INTO filter (price, establishment_id_establishment, service_id) VALUES (50.0, 1, 2);

INSERT INTO scheduling (service_id, employee_id, date_time) VALUES (1, 1, '2024-05-10T10:30:00');
INSERT INTO scheduling (service_id, employee_id, date_time) VALUES (2, 1, '2024-05-12T10:40:00');
