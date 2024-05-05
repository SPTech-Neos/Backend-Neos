-- massa de dados para teste do sistema     

INSERT INTO company (name, cnpj) VALUES
('Empresa A', '12345678901234'),
('Empresa B', '98765432109876'),
('Empresa C', '56789012345678'),
('Empresa D', '43210987654321');

INSERT INTO address (street, city, state) VALUES
('Rua A', 'Cidade A', 'AA'),
('Rua B', 'Cidade B', 'BB'),
('Rua C', 'Cidade C', 'CC'),
('Rua D', 'Cidade D', 'DD');

INSERT INTO local (number, floor, bloc, complement, fk_address) VALUES
(1, 1, 'A', 'Complemento A', 1),
(2, 2, 'B', 'Complemento B', 2),
(3, 3, 'C', 'Complemento C', 3),
(4, 4, 'D', 'Complemento D', 4);

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

INSERT INTO establishment (name, fk_establishment, fk_local) VALUES
('Estabelecimento A', 1, 1),
('Estabelecimento B', 2, 2);

INSERT Into Employee_type (name) VALUES
('Gerente'),
('Funcionario');

INSERT INTO employee (name,email, passaword, fk_employee_type, fk_establishment) VALUES
('Funcionario A', 'gg@gmail.com', '123', 2, 1),
('Gerente A', 'dd@gmail.com', '123', 1, 2),
('Cliente Funcionario', 'cliente.funcionario@example.com', '123senha', 2, 2);