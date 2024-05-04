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

INSERT INTO establishment (name, local_id_local) VALUES
('Estabelecimento A', 1),
('Estabelecimento B', 2);

INSERT Into Employee_type (name) VALUES
('Gerente'),
('Funcionario');

INSERT INTO employee (name,email, passaword, employee_type_id, establishment_id_establishment) VALUES
('Funcionario A', 'gg@gmail.com', '123', 2, 1),
('Gerente A', 'dd@gmail.com', '123', 1, 2);



