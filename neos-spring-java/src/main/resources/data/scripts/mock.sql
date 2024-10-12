-- Inserir dados na tabela 'address'
INSERT INTO address (public_place, city, zip_code, uf) VALUES
('Rua das Flores', 'São Paulo', '12345678', 'SP'),
('Av. dos Estudantes', 'Rio de Janeiro', '87654321', 'RJ');

-- Inserir dados na tabela 'local'
INSERT INTO local (number, floor, complement, block, fk_address) VALUES
('123', 2, 'Apto 202', 'B', 1),
('456', 1, 'Loja 12', 'A', 2);

-- Inserir dados na tabela 'phone'
INSERT INTO phone (country_code, area_code, number) VALUES
('+55', '11', '912345678'),
('+55', '21', '987654321');

-- Inserir dados na tabela 'status'
INSERT INTO status (name, type) VALUES
('Ativo', 'Funcionário'),
('Inativo', 'Funcionário'),
('Ativo', 'Produto'),
('Inativo', 'Produto'),
('Agendado', 'Serviço'),
('Cancelado', 'Serviço');

-- Inserir dados na tabela 'establishment'
INSERT INTO establishment (aditum_id, name, img_url, fk_local, fk_phone, fk_status, start_shift, end_shift, description, cnpj) VALUES
('EST-001', 'Bar do Zé', 'http://image-url.com/bar_do_ze.jpg', 1, 1, 1, '18:00:00', '02:00:00', 'Bar com diversas opções de bebidas', '12.345.678/0001-90'),
('EST-002', 'Padaria Central', 'http://image-url.com/padaria_central.jpg', 2, 2, 1, '06:00:00', '20:00:00', 'Padaria com pães frescos e lanches', '23.456.789/0001-91');

-- Inserir dados na tabela 'employee_type'
INSERT INTO employee_type (name) VALUES
('Garçom'),
('Padeiro'),
('Gerente');

-- Inserir 5 funcionários para cada estabelecimento
INSERT INTO employee (name, email, password, img_url, fk_establishment, fk_employee_type, fk_phone, fk_local, fk_status) VALUES
('João Silva', 'joao@barze.com', '$2a$10$Job3zEC6.Xtfbl4TNRDRBOUltMpyO0QiozXMQPmvRCCMowQYiCBAC', 'http://image-url.com/joao.jpg', 1, 1, 1, 1, 1),
('Maria Souza', 'maria@barze.com', '$2a$10$Job3zEC6.Xtfbl4TNRDRBOUltMpyO0QiozXMQPmvRCCMowQYiCBAC', 'http://image-url.com/maria.jpg', 1, 1, 2, 2, 1),
('Carlos Dias', 'carlos@barze.com', '$2a$10$Job3zEC6.Xtfbl4TNRDRBOUltMpyO0QiozXMQPmvRCCMowQYiCBAC', 'http://image-url.com/carlos.jpg', 1, 2, 1, 1, 1),
('Ana Lima', 'ana@barze.com', '$2a$10$Job3zEC6.Xtfbl4TNRDRBOUltMpyO0QiozXMQPmvRCCMowQYiCBAC', 'http://image-url.com/ana.jpg', 1, 2, 2, 2, 1),
('Pedro Oliveira', 'pedro@barze.com', '$2a$10$Job3zEC6.Xtfbl4TNRDRBOUltMpyO0QiozXMQPmvRCCMowQYiCBAC', 'http://image-url.com/pedro.jpg', 1, 3, 1, 1, 1),
('Lucas Pereira', 'lucas@padariacentral.com', '$2a$10$Job3zEC6.Xtfbl4TNRDRBOUltMpyO0QiozXMQPmvRCCMowQYiCBAC', 'http://image-url.com/lucas.jpg', 2, 1, 2, 2, 1),
('Fernanda Costa', 'fernanda@padariacentral.com', '$2a$10$Job3zEC6.Xtfbl4TNRDRBOUltMpyO0QiozXMQPmvRCCMowQYiCBAC', 'http://image-url.com/fernanda.jpg', 2, 1, 1, 1, 1),
('Marcos Silva', 'marcos@padariacentral.com', '$2a$10$Job3zEC6.Xtfbl4TNRDRBOUltMpyO0QiozXMQPmvRCCMowQYiCBAC', 'http://image-url.com/marcos.jpg', 2, 2, 2, 2, 1),
('Sofia Ribeiro', 'sofia@padariacentral.com', '$2a$10$Job3zEC6.Xtfbl4TNRDRBOUltMpyO0QiozXMQPmvRCCMowQYiCBAC', 'http://image-url.com/sofia.jpg', 2, 2, 1, 1, 1),
('Clara Mendes', 'clara@padariacentral.com', '$2a$10$Job3zEC6.Xtfbl4TNRDRBOUltMpyO0QiozXMQPmvRCCMowQYiCBAC', 'http://image-url.com/clara.jpg', 2, 3, 2, 2, 1);

-- Inserir 5 clientes
INSERT INTO client (name, email, password, img_url, cpf, fk_local, fk_phone) VALUES
('Gabriel Santos', 'gabriel@cliente.com', '$2a$10$Job3zEC6.Xtfbl4TNRDRBOUltMpyO0QiozXMQPmvRCCMowQYiCBAC', 'http://image-url.com/gabriel.jpg', '12345678901', 1, 1),
('Renata Lima', 'renata@cliente.com', '$2a$10$Job3zEC6.Xtfbl4TNRDRBOUltMpyO0QiozXMQPmvRCCMowQYiCBAC', 'http://image-url.com/renata.jpg', '23456789012', 2, 2),
('Ricardo Alves', 'ricardo@cliente.com', '$2a$10$Job3zEC6.Xtfbl4TNRDRBOUltMpyO0QiozXMQPmvRCCMowQYiCBAC', 'http://image-url.com/ricardo.jpg', '34567890123', 1, 1),
('Amanda Souza', 'amanda@cliente.com', '$2a$10$Job3zEC6.Xtfbl4TNRDRBOUltMpyO0QiozXMQPmvRCCMowQYiCBAC', 'http://image-url.com/amanda.jpg', '45678901234', 2, 2),
('Fernando Dias', 'fernando@cliente.com', '$2a$10$Job3zEC6.Xtfbl4TNRDRBOUltMpyO0QiozXMQPmvRCCMowQYiCBAC', 'http://image-url.com/fernando.jpg', '56789012345', 1, 1);

-- Inserir tipos de produto
INSERT INTO product_type (name, specification) VALUES
('Bebida', 'Alcoólica'),
('Comida', 'Salgado'),
('Doce', 'Sobremesa');

-- Inserir produtos para os estabelecimentos
INSERT INTO product (name, brand, img_url, price, fk_product_type, fk_establishment, fk_status) VALUES
('Cerveja', 'Skol', 'http://image-url.com/cerveja.jpg', 5.50, 1, 1, 1),
('Refrigerante', 'Coca-Cola', 'http://image-url.com/refrigerante.jpg', 4.00, 1, 2, 1),
('Coxinha', 'Padaria Central', 'http://image-url.com/coxinha.jpg', 3.00, 2, 2, 1),
('Brigadeiro', 'Padaria Central', 'http://image-url.com/brigadeiro.jpg', 2.50, 3, 2, 1);

-- Inserir categorias de serviço
INSERT INTO service_category (name) VALUES
('Bar'),
('Restaurante');

-- Inserir tipos de serviço
INSERT INTO service_type (name, fk_service_category) VALUES
('Atendimento de mesa', 1),
('Entrega a domicílio', 2);

-- Inserir serviços
INSERT INTO service (specification, aditum_id, price, img_url, fk_service_type, fk_status) VALUES
('Atendimento no Bar do Zé', 'SRV-001', 0.00, 'http://image-url.com/atendimento.jpg', 1, 1),
('Entrega na Padaria Central', 'SRV-002', 5.00, 'http://image-url.com/entrega.jpg', 2, 1);

-- Inserir agendamentos
INSERT INTO schedule (date_time, fk_service, fk_status, fk_client, fk_employee) VALUES
('2024-10-10 18:00:00', 1, 1, 1, 1),
('2024-10-11 10:00:00', 2, 1, 2, 6);

-- Inserir pedidos
INSERT INTO orders (date_time, fk_status, fk_client) VALUES
('2024-10-10 19:00:00', 1, 3),
('2024-10-11 11:00:00', 1, 4);

-- Inserir mercado (associação de produtos com pedidos)
INSERT INTO market (quantity, fk_product, fk_order) VALUES
(2, 1, 1),
(3, 2, 2);
