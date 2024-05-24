INSERT INTO address (id_address, street, city, state) VALUES
(1, 'Rua A', 'Cidade A', 'Estado A'),
(2, 'Rua B', 'Cidade B', 'Estado B'),
(3, 'Rua C', 'Cidade C', 'Estado C'),
(4, 'Rua D', 'Cidade D', 'Estado D'),
(5, 'Rua E', 'Cidade E', 'Estado E');

INSERT INTO local (id_local, number, floor, bloc, complement, address_id_address) VALUES
(1, 100, 1, 'Bloc A', 'Complement A', 1),
(2, 200, 2, 'Bloc B', 'Complement B', 2),
(3, 300, 3, 'Bloc C', 'Complement C', 3),
(4, 400, 4, 'Bloc D', 'Complement D', 4),
(5, 500, 5, 'Bloc E', 'Complement E', 5);


INSERT INTO client (client_id, name, email, password, local_id_local) VALUES
(1, 'Cliente A', 'clienteA@example.com', 'senhaA', 1),
(2, 'Cliente B', 'clienteB@example.com', 'senhaB', 2),
(3, 'Cliente C', 'clienteC@example.com', 'senhaC', 3),
(4, 'Cliente D', 'clienteD@example.com', 'senhaD', 4),
(5, 'Cliente E', 'clienteE@example.com', 'senhaE', 5);

INSERT INTO company (id_company, name, cnpj) VALUES
(1, 'Empresa A', '84.463.068/0001-81'),
(2, 'Empresa B', '11.444.777/0001-61'),
(3, 'Empresa C', '13.666.333/0001-51'),
(4, 'Empresa D', '23.555.111/0001-41'),
(5, 'Empresa E', '33.222.999/0001-31');

INSERT INTO coupon (id, name) VALUES
(1, 'Cupom A'),
(2, 'Cupom B'),
(3, 'Cupom C'),
(4, 'Cupom D'),
(5, 'Cupom E');

--  TODO: Trocar isso para algo corretos
-- INSERT INTO Coupon_available (id, expiration_date, discount, discount_type_id, establishment_id, coupon_id) VALUES
-- (1, '2022-12-31', 10.0, 1, 1, 1),
-- (2, '2022-12-31', 15.0, 2, 2, 2),
-- (3, '2022-12-31', 20.0, 3, 3, 3),
-- (4, '2022-12-31', 25.0, 4, 4, 4),
-- (5, '2022-12-31', 30.0, 5, 5, 5);

INSERT INTO discount_type (id, name) VALUES
(1, 'Desconto A'),
(2, 'Desconto B'),
(3, 'Desconto C'),
(4, 'Desconto D'),
(5, 'Desconto E');

--  TODO: Trocar isso para algo correto

-- INSERT INTO employee_services (id, hours_spent, expertise, employee_id, service_id) VALUES
-- (1, '2022-01-01 01:00:00', TRUE, 1, 1),
-- (2, '2022-01-01 02:00:00', FALSE, 2, 2),
-- (3, '2022-01-01 03:00:00', TRUE, 3, 3),
-- (4, '2022-01-01 04:00:00', FALSE, 4, 4),
-- (5, '2022-01-01 05:00:00', TRUE, 5, 5);


INSERT INTO employee_type (id, name) VALUES
(1, 'Tipo A'),
(2, 'Tipo B'),
(3, 'Tipo C'),
(4, 'Tipo D'),
(5, 'Tipo E');

--  TODO: Trocar isso para algo certo
--  ? startShift e endShift devem ser corrigidos para o tipo correto se pá um Time
-- INSERT INTO establishment (id, name, cnpj,startShift,endShift local_id, description) VALUES
-- (1, 'Estabelecimento A', '00.000.000/0001-91', null, null, 1, 'Descrição A'),
-- (2, 'Estabelecimento B', '00.000.000/0002-82', null, null, 2, 'Descrição B'),
-- (3, 'Estabelecimento C', '00.000.000/0003-73', null, null, 3, 'Descrição C'),
-- (4, 'Estabelecimento D', '00.000.000/0004-64', null, null, 4, 'Descrição D'),
-- (5, 'Estabelecimento E', '00.000.000/0005-55', null, null, 5, 'Descrição E');

-- INSERT INTO filter (id, price, establishment_id, service_id) VALUES
-- (1, 100.00, 1, 1),
-- (2, 200.00, 2, 2),
-- (3, 300.00, 3, 3),
-- (4, 400.00, 4, 4),
-- (5, 500.00, 5, 5);


INSERT INTO product_type (id, name, specification) VALUES
(1, 'Tipo A', 'Especificação A'),
(2, 'Tipo B', 'Especificação B'),
(3, 'Tipo C', 'Especificação C'),
(4, 'Tipo D', 'Especificação D'),
(5, 'Tipo E', 'Especificação E');
INSERT INTO product (id, name, brand, product_type_id, establishment_id) VALUES
(1, 'Produto A', 'Marca A', 1, null),
(2, 'Produto B', 'Marca B', 2, null),
(3, 'Produto C', 'Marca C', 3, null),
(4, 'Produto D', 'Marca D', 4, null),
(5, 'Produto E', 'Marca E', 5, null);

-- INSERT INTO scheduling (id, client_client_id, service_id, employee_id, date_time) VALUES
-- (1, 1, 1, 1, '2022-01-01T08:00:00'),
-- (2, 2, 2, 2, '2022-01-02T09:00:00'),
-- (3, 3, 3, 3, '2022-01-03T10:00:00'),
-- (4, 4, 4, 4, '2022-01-04T11:00:00'),
-- (5, 5, 5, 5, '2022-01-05T12:00:00');
-- INSERT INTO service (id, specification, service_type_id) VALUES
-- (1, 'Especificação A', 1),
-- (2, 'Especificação B', 2),
-- (3, 'Especificação C', 3),
-- (4, 'Especificação D', 4),
-- (5, 'Especificação E', 5);

INSERT INTO service_category (id, name) VALUES
(1, 'Categoria A'),
(2, 'Categoria B'),
(3, 'Categoria C'),
(4, 'Categoria D'),
(5, 'Categoria E');


INSERT INTO service_type (id, name, service_category_id) VALUES
(1, 'Tipo A', 1),
(2, 'Tipo B', 2),
(3, 'Tipo C', 3),
(4, 'Tipo D', 4),
(5, 'Tipo E', 5);