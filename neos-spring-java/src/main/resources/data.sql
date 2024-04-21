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


INSERT INTO client (name, email, password, local_id_local) VALUES
('Cliente A', 'cliente.a@example.com', 'senha123', 1),
('Cliente B', 'cliente.b@example.com', 'senha456', 2),
('Cliente C', 'cliente.c@example.com', 'senha789', 3),
('Cliente D', 'cliente.d@example.com', 'senhaabc', 4);

