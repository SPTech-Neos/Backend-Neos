-- massa de dados para teste do sistema     

-- Populando a tabela 'local'

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

INSERT INTO client (name, email, password, local_id_local) VALUES
('Cliente A', 'cliente.a@example.com', 'senha123', 1),
('Cliente B', 'cliente.b@example.com', 'senha456', 2),
('Cliente C', 'cliente.c@example.com', 'senha789', 3),
('Cliente D', 'cliente.d@example.com', 'senhaabc', 4),
('Cliente T', 'cliente.T@example.com', '$2a$10$sgcxPkMU39rBgarTZKdRBO.zFd6VcwyjSaQN15IhbZaqt5//Sosra', 3),
('Cliente Funcionario', 'cliente.funcionario@example.com', '$2a$10$LKDiQGRIF9YiGivySF3UreeGcfbEL8XucUSNU6aUxEe9foyZhg9lC', 4);

INSERT INTO establishment (name, local_id_local) VALUES
('Estabelecimento A', 1),
('Estabelecimento B', 2);

INSERT Into Employee_type (name) VALUES
('Gerente'),
('Funcionario');

INSERT INTO employee (name,email, passaword, employee_type_id, establishment_id_establishment) VALUES
('Funcionario A', 'gg@gmail.com', '123', 2, 1),
('Gerente A', 'dd@gmail.com', '123', 1, 2);

INSERT INTO company (name, cnpj)
VALUES
('lirasalon', '222222222'),
('luizsalon', '111111111'),
('yukiosalon', '0000000000');

INSERT INTO client (name, email, password) VALUES
('Cliente A', 'cliente.a@example.com', '$2a$10$0/TKTGxdREbWaWjWYhwf6e9P1fPOAMMNqEnZgOG95jnSkHSfkkIrC'),
('Cliente B', 'cliente.b@example.com', 'senha456'),
('Cliente C', 'cliente.c@example.com', 'senha789'),
('Cliente D', 'cliente.d@example.com', 'senhaabc'),
('Cliente Funcionario', 'cliente.funcionario@example.com', '$2a$10$LKDiQGRIF9YiGivySF3UreeGcfbEL8XucUSNU6aUxEe9foyZhg9lC');


