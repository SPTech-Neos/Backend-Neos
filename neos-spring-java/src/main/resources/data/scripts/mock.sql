use blume;


-- crie uma massa de dados com 20 addresses
INSERT INTO address (public_place, city, zip_code, uf) VALUES
('Rua 1', 'São Paulo', '01010101', 'SP'),
('Rua 2', 'São Paulo', '02020202', 'SP'),
('Rua 3', 'São Paulo', '03030303', 'SP'),
('Rua 4', 'São Paulo', '04040404', 'SP'),
('Rua 5', 'São Paulo', '05050505', 'SP'),
('Rua 6', 'São Paulo', '06060606', 'SP'),
('Rua 7', 'São Paulo', '07070707', 'SP'),
('Rua 8', 'São Paulo', '08080808', 'SP'),
('Rua 9', 'São Paulo', '09090909', 'SP'),
('Rua 10', 'São Paulo', '10101010', 'SP'),
('Rua 11', 'São Paulo', '11111111', 'SP'),
('Rua 12', 'São Paulo', '12121212', 'SP'),
('Rua 13', 'São Paulo', '13131313', 'SP'),
('Rua 14', 'São Paulo', '14141414', 'SP'),
('Rua 15', 'São Paulo', '15151515', 'SP'),
('Rua 16', 'São Paulo', '16161616', 'SP'),
('Rua 17', 'São Paulo', '17171717', 'SP'),
('Rua 18', 'São Paulo', '18181818', 'SP'),
('Rua 19', 'São Paulo', '19191919', 'SP'),
('Rua 20', 'São Paulo', '20202020', 'SP');

-- crie uma massa de dados com 20 locals

INSERT INTO local (number, floor, complement, block, fk_address) VALUES
('1', 1, 'Apto 101', 'A', 1),
('2', 2, 'Apto 202', 'B', 2),
('3', 3, 'Apto 303', 'C', 3),
('4', 4, 'Apto 404', 'D', 4),
('5', 5, 'Apto 505', 'E', 5),
('6', 6, 'Apto 606', 'F', 6),
('7', 7, 'Apto 707', 'G', 7),
('8', 8, 'Apto 808', 'H', 8),
('9', 9, 'Apto 909', 'I', 9),
('10', 10, 'Apto 1010', 'J', 10),
('11', 11, 'Apto 1111', 'K', 11),
('12', 12, 'Apto 1212', 'L', 12),
('13', 13, 'Apto 1313', 'M', 13),
('14', 14, 'Apto 1414', 'N', 14),
('15', 15, 'Apto 1515', 'O', 15),
('16', 16, 'Apto 1616', 'P', 16),
('17', 17, 'Apto 1717', 'Q', 17),
('18', 18, 'Apto 1818', 'R', 18),
('19', 19, 'Apto 1919', 'S', 19),
('20', 20, 'Apto 2020', 'T', 20);

-- crie uma massa de dados com 20 phones

INSERT INTO phone (country_code, area_code, number) VALUES
('+55', '11', '912345678'),
('+55', '11', '987654321'),
('+55', '11', '912345678'),
('+55', '11', '987654321'),
('+55', '11', '912345678'),
('+55', '11', '987654321'),
('+55', '11', '912345678'),
('+55', '11', '987654321'),
('+55', '11', '912345678'),
('+55', '11', '987654321'),
('+55', '11', '912345678'),
('+55', '11', '987654321'),
('+55', '11', '912345678'),
('+55', '11', '987654321'),
('+55', '11', '912345678'),
('+55', '11', '987654321'),
('+55', '11', '912345678'),
('+55', '11', '987654321'),
('+55', '11', '912345678'),
('+55', '11', '987654321');

insert INTO status (name, type) VALUES
('Ativo', 'active'),
('Inativo', 'inactive'),
('Pendente', 'pending'),
('Cancelado', 'canceled'),
('Concluído', 'completed');


-- crie uma massa de dados com 20 establishments


INSERT INTO establishment (aditum_id, name, img_url, fk_local, fk_phone, fk_status, start_shift, end_shift, description, cnpj) VALUES
('EST-001', 'Salão de Beleza 1', 'https://universoretro.com.br/wp-content/uploads/2020/10/salao-new-order-hair.jpg', 1, 1, 1, '08:00:00', '18:00:00', 'Salão de beleza com diversos serviços de beleza', '12.345.678/0001-90'),
('EST-002', 'Salão de Beleza 2', 'https://www.contabilizei.com.br/contabilidade-online/wp-content/webp-express/webp-images/uploads/2020/05/guilherme-petri-PtOfbGkU3uI-unsplash-768x512.jpg.webp', 2, 2, 1, '08:00:00', '18:00:00', 'Salão de beleza com diversos serviços de beleza', '23.456.789/0001-91'),
('EST-003', 'Salão de Beleza 3', 'https://assets.zyrosite.com/cdn-cgi/image/format=auto,w=1920,fit=crop/YleLaQRbgbC0zVDR/photo_4992381796763151353_y-dOqXor13xWiOqwNb.jpg', 3, 3, 1, '08:00:00', '18:00:00', 'Salão de beleza com diversos serviços de beleza', '34.567.890/0001-92'),
('EST-004', 'Salão de Beleza 4', 'http://image-url.com/salao4.jpg', 4, 4, 1, '08:00:00', '18:00:00', 'Salão de beleza com diversos serviços de beleza', '45.678.901/0001-93'),
('EST-005', 'Salão de Beleza 5', 'http://image-url.com/salao5.jpg', 5, 5, 1, '08:00:00', '18:00:00', 'Salão de beleza com diversos serviços de beleza', '56.789.012/0001-94'),
('EST-006', 'Salão de Beleza 6', 'http://image-url.com/salao6.jpg', 6, 6, 1, '08:00:00', '18:00:00', 'Salão de beleza com diversos serviços de beleza', '67.890.123/0001-95'),
('EST-007', 'Salão de Beleza 7', 'http://image-url.com/salao7.jpg', 7, 7, 1, '08:00:00', '18:00:00', 'Salão de beleza com diversos serviços de beleza', ''),
('EST-008', 'Salão de Beleza 8', 'http://image-url.com/salao8.jpg', 8, 8, 1, '08:00:00', '18:00:00', 'Salão de beleza com diversos serviços de beleza', ''),
('EST-009', 'Salão de Beleza 9', 'http://image-url.com/salao9.jpg', 9, 9, 1, '08:00:00', '18:00:00', 'Salão de beleza com diversos serviços de beleza', ''),
('EST-010', 'Salão de Beleza 10', 'http://image-url.com/salao10.jpg', 10, 10, 1, '08:00:00', '18:00:00', 'Salão de beleza com diversos serviços de beleza', ''),
('EST-011', 'Salão de Beleza 11', 'http://image-url.com/salao11.jpg', 11, 11, 1, '08:00:00', '18:00:00', 'Salão de beleza com diversos serviços de beleza', ''),
('EST-012', 'Salão de Beleza 12', 'http://image-url.com/salao12.jpg', 12, 12, 1, '08:00:00', '18:00:00', 'Salão de beleza com diversos serviços de beleza', ''),
('EST-013', 'Salão de Beleza 13', 'http://image-url.com/salao13.jpg', 13, 13, 1, '08:00:00', '18:00:00', 'Salão de beleza com diversos serviços de beleza', ''),
('EST-014', 'Salão de Beleza 14', 'http://image-url.com/salao14.jpg', 14, 14, 1, '08:00:00', '18:00:00', 'Salão de beleza com diversos serviços de beleza', '');

-- crie uma massa de dados com 20 product_types com o conexto abaixo
-- Tenho uma aplicação onde o foco é salão de beleza e agendamentos no salão entao a ideia seria montar um mock de dados para esse sql com o foco em salao de beleza, blume é o nome do projeto

INSERT INTO product_type (name, specification) VALUES
('Shampoo', 'Cabelo'),
('Condicionador', 'Cabelo'),
('Máscara', 'Cabelo'),
('Finalizador', 'Cabelo'),
('Óleo', 'Cabelo'),
('Creme', 'Cabelo'),
('Tônico', 'Cabelo'),
('Leave-in', 'Cabelo'),
('Protetor', 'Cabelo'),
('Gel', 'Cabelo'),
('Pomada', 'Cabelo'),
('Spray', 'Cabelo'),
('Mousse', 'Cabelo'),
('Creme de pentear', 'Cabelo'),
('Creme de tratamento', 'Cabelo'),
('Creme de hidratação', 'Cabelo'),
('Creme de reconstrução', 'Cabelo'),
('Creme de nutrição', 'Cabelo'),
('Creme de definição', 'Cabelo'),
('Creme de modelagem', 'Cabelo');

-- crie uma massa de dados com 20 products

INSERT INTO product (aditum_id, name, brand, img_url, price, fk_product_type, fk_establishment, fk_status) VALUES
('f905651c-53ea-498a-a32b-5eceb939895d', 'Shampoo', 'Loreal', 'https://images.tcdn.com.br/img/img_prod/943474/loreal_pro_longer_shampoo_renovacao_crescimento_300ml_35809_1_b8c6e7275571aaf215f5393b189f5e82.jpg', 5.50, 1, 1, 1),
('AAA-AA0', 'Condicionador', 'Loreal', 'https://images.tcdn.com.br/img/img_prod/943474/loreal_pro_longer_shampoo_renovacao_crescimento_300ml_35809_1_b8c6e7275571aaf215f5393b189f5e82.jpg', 4.00, 2, 1, 1),
('BBB-BB1', 'Máscara', 'Loreal', 'https://images.tcdn.com.br/img/img_prod/943474/loreal_pro_longer_shampoo_renovacao_crescimento_300ml_35809_1_b8c6e7275571aaf215f5393b189f5e82.jpg', 3.00, 3, 1, 1),
('CCC-CC3', 'Finalizador', 'Loreal', 'https://images.tcdn.com.br/img/img_prod/943474/loreal_pro_longer_shampoo_renovacao_crescimento_300ml_35809_1_b8c6e7275571aaf215f5393b189f5e82.jpg', 2.50, 4, 1, 1),
('DDD-DD4', 'Óleo', 'Loreal', 'https://images.tcdn.com.br/img/img_prod/943474/loreal_pro_longer_shampoo_renovacao_crescimento_300ml_35809_1_b8c6e7275571aaf215f5393b189f5e82.jpg', 5.50, 5, 1, 1),
('EEE-EE5', 'Creme', 'Loreal', 'https://images.tcdn.com.br/img/img_prod/943474/loreal_pro_longer_shampoo_renovacao_crescimento_300ml_35809_1_b8c6e7275571aaf215f5393b189f5e82.jpg', 4.00, 6, 1, 1),
('FFF-FF6', 'Tônico', 'Loreal', 'https://images.tcdn.com.br/img/img_prod/943474/loreal_pro_longer_shampoo_renovacao_crescimento_300ml_35809_1_b8c6e7275571aaf215f5393b189f5e82.jpg', 3.00, 7, 1, 1),
('GGG-GG7', 'Leave-in', 'Loreal', 'https://images.tcdn.com.br/img/img_prod/943474/loreal_pro_longer_shampoo_renovacao_crescimento_300ml_35809_1_b8c6e7275571aaf215f5393b189f5e82.jpg.jpg', 2.50, 8, 1, 1),
('HHH-HH8', 'Protetor', 'Loreal', 'https://images.tcdn.com.br/img/img_prod/943474/loreal_pro_longer_shampoo_renovacao_crescimento_300ml_35809_1_b8c6e7275571aaf215f5393b189f5e82.jpg', 5.50, 9, 1, 1),
('III-II9', 'Gel', 'Loreal', 'https://images.tcdn.com.br/img/img_prod/943474/loreal_pro_longer_shampoo_renovacao_crescimento_300ml_35809_1_b8c6e7275571aaf215f5393b189f5e82.jpg', 4.00, 10, 1, 1),
('KKK-KK2', 'Pomada', 'Loreal', 'https://images.tcdn.com.br/img/img_prod/943474/loreal_pro_longer_shampoo_renovacao_crescimento_300ml_35809_1_b8c6e7275571aaf215f5393b189f5e82.jpg', 3.00, 11, 1, 1),
('JJJ-JJ1', 'Spray', 'Loreal', 'https://images.tcdn.com.br/img/img_prod/943474/loreal_pro_longer_shampoo_renovacao_crescimento_300ml_35809_1_b8c6e7275571aaf215f5393b189f5e82.jpg', 2.50, 12, 1, 1),
('LLL-LL1', 'Mousse', 'Loreal', 'https://images.tcdn.com.br/img/img_prod/943474/loreal_pro_longer_shampoo_renovacao_crescimento_300ml_35809_1_b8c6e7275571aaf215f5393b189f5e82.jpg', 5.50, 13, 1, 1),
('MMM-MM2', 'Creme de pentear', 'Loreal', 'https://images.tcdn.com.br/img/img_prod/943474/loreal_pro_longer_shampoo_renovacao_crescimento_300ml_35809_1_b8c6e7275571aaf215f5393b189f5e82.jpg-pentear.jpg', 4.00, 14, 1, 1),
('NNN-NN4', 'Creme de tratamento', 'Loreal', 'https://images.tcdn.com.br/img/img_prod/943474/loreal_pro_longer_shampoo_renovacao_crescimento_300ml_35809_1_b8c6e7275571aaf215f5393b189f5e82.jpg-tratamento.jpg', 3.00, 15, 1, 1),
('OOO-OO1', 'Creme de hidratação', 'Loreal', 'https://images.tcdn.com.br/img/img_prod/943474/loreal_pro_longer_shampoo_renovacao_crescimento_300ml_35809_1_b8c6e7275571aaf215f5393b189f5e82.jpg-hidratacao.jpg', 2.50, 16, 1, 1),
('PPP-PP2', 'Creme de reconstrução', 'Loreal', 'https://images.tcdn.com.br/img/img_prod/943474/loreal_pro_longer_shampoo_renovacao_crescimento_300ml_35809_1_b8c6e7275571aaf215f5393b189f5e82.jpg-reconstrucao.jpg', 5.50, 17, 1, 1),
('AAA-N12', 'Creme de nutrição', 'Loreal', 'https://images.tcdn.com.br/img/img_prod/943474/loreal_pro_longer_shampoo_renovacao_crescimento_300ml_35809_1_b8c6e7275571aaf215f5393b189f5e82.jpg-nutricao.jpg', 4.00, 18, 1, 1),
('KKL-02A', 'Creme de definição', 'Loreal', 'https://images.tcdn.com.br/img/img_prod/943474/loreal_pro_longer_shampoo_renovacao_crescimento_300ml_35809_1_b8c6e7275571aaf215f5393b189f5e82.jpg-definicao.jpg', 3.00, 19, 1, 1),
('AAA-AA0', 'Clear men', 'Clear men', 'https://www.drogariaminasbrasil.com.br/media/product/bed/shampoo-anticaspa-men-limpeza-profunda-400ml-clear-d6a.jpg', 2.50, 20, 1, 1);

-- crie uma massa de dados com 20 service_categories

INSERT INTO service_category (name) VALUES
('Cabelo'),
('Estética'),
('Unhas'),
('Maquiagem'),
('Massagem'),
('Depilação'),
('Barbearia'),
('Penteados'),
('Design de sobrancelhas'),
('Dia da noiva'),
('Dia do noivo'),
('Dia da debutante');

-- crie uma massa de dados com 20 service_types

INSERT INTO service_type (name, fk_service_category) VALUES
('Corte de cabelo', 1),
('Coloração', 1),
('Escova', 1),
('Progressiva', 1),
('Luzes', 1),
('Mechas', 1),
('Hidratação', 1),
('Reconstrução', 1),
('Nutrição', 1),
('Definição', 1),
('Modelagem', 1),
('Penteados', 1),
('Maquiagem', 4),
('Massagem relaxante', 5),
('Depilação', 6),
('Barba', 7),
('Unhas', 3),
('Design de sobrancelhas', 9),
('Dia da noiva', 10),
('Dia do noivo', 11);

-- crie uma massa de dados com 20 services

INSERT INTO service (specification, aditum_id, price, img_url, fk_service_type, fk_status) VALUES
('Corte de cabelo', 'SRV-001', 0.00, 'http://image-url.com/corte.jpg', 1, 1),
('Coloração', 'SRV-002', 5.00, 'http://image-url.com/coloracao.jpg', 2, 1),
('Escova', 'SRV-003', 10.00, 'http://image-url.com/escova.jpg', 3, 1),
('Progressiva', 'SRV-004', 15.00, 'http://image-url.com/progressiva.jpg', 4, 1),
('Luzes', 'SRV-005', 20.00, 'http://image-url.com/luzes.jpg', 5, 1),
('Mechas', 'SRV-006', 25.00, 'http://image-url.com/mechas.jpg', 6, 1),
('Hidratação', 'SRV-007', 30.00, 'http://image-url.com/hidratacao.jpg', 7, 1),
('Reconstrução', 'SRV-008', 35.00, 'http://image-url.com/reconstrucao.jpg', 8, 1),
('Nutrição', 'SRV-009', 40.00, 'http://image-url.com/nutricao.jpg', 9, 1),
('Definição', 'SRV-010', 45.00, 'http://image-url.com/definicao.jpg', 10, 1),
('Modelagem', 'SRV-011', 50.00, 'http://image-url.com/modelagem.jpg', 11, 1),
('Penteados', 'SRV-012', 55.00, 'http://image-url.com/penteados.jpg', 12, 1),
('Maquiagem', 'SRV-013', 60.00, 'http://image-url.com/maquiagem.jpg', 13, 1),
('Massagem relaxante', 'SRV-014', 65.00, 'http://image-url.com/massagem.jpg', 14, 1),
('Depilação', 'SRV-015', 70.00, 'http://image-url.com/depilacao.jpg', 15, 1),
('Barba', 'SRV-016', 75.00, 'http://image-url.com/barba.jpg', 16, 1),
('Unhas', 'SRV-017', 80.00, 'http://image-url.com/unhas.jpg', 17, 1),
('Design de sobrancelhas', 'SRV-018', 85.00, 'http://image-url.com/design.jpg', 18, 1),
('Dia da noiva', 'SRV-019', 90.00, 'http://image-url.com/dia-da-noiva.jpg', 19, 1),
('Dia do noivo', 'SRV-020', 95.00, 'http://image-url.com/dia-do-noivo.jpg', 20, 1);

-- crie uma massa de dados com 20 employee_types

INSERT INTO employee_type (name) VALUES
('Cabeleireiro'),
('Esteticista'),
('Manicure'),
('Maquiador'),
('Massagista'),
('Depilador'),
('Barbeiro'),
('Penteador'),
('Designer de sobrancelhas'),
('Dia da noiva'),
('Dia do noivo'),
('Dia da debutante');

-- crie uma massa de dados com 20 employees

INSERT INTO employee (name, email, password, img_url, fk_establishment, fk_employee_type, fk_phone, fk_local, fk_status) VALUES
('Carlos Dias', 'exemplo01@teste.com', '$2a$10$hleet8CAQHml3gRwovtmIeA52jxwrpLZ/MsOQyO0dAOIoBTTdd/W6','https://publish-p47754-e237306.adobeaemcloud.com/adobe/dynamicmedia/deliver/dm-aid--914bcfe0-f610-4610-a77e-6ea53c53f630/_330603286208.app.webp?preferwebp=true&width=312', 1, 1, 1, 1, 1),
('Maria Silva', 'exemplo02@teste.com', '$2a$10$hleet8CAQHml3gRwovtmIeA52jxwrpLZ/MsOQyO0dAOIoBTTdd/W6','https://publish-p47754-e237306.adobeaemcloud.com/adobe/dynamicmedia/deliver/dm-aid--914bcfe0-f610-4610-a77e-6ea53c53f630/_330603286208.app.webp?preferwebp=true&width=312', 2, 2, 2, 2, 1),
('João Santos', 'exemplo03@teste.com', '$2a$10$hleet8CAQHml3gRwovtmIeA52jxwrpLZ/MsOQyO0dAOIoBTTdd/W6','https://publish-p47754-e237306.adobeaemcloud.com/adobe/dynamicmedia/deliver/dm-aid--914bcfe0-f610-4610-a77e-6ea53c53f630/_330603286208.app.webp?preferwebp=true&width=312', 3, 3, 3, 3, 1),
('Ana Souza', 'exemplo04@teste.com', '$2a$10$hleet8CAQHml3gRwovtmIeA52jxwrpLZ/MsOQyO0dAOIoBTTdd/W6','https://publish-p47754-e237306.adobeaemcloud.com/adobe/dynamicmedia/deliver/dm-aid--914bcfe0-f610-4610-a77e-6ea53c53f630/_330603286208.app.webp?preferwebp=true&width=312', 4, 4, 4, 4, 1),
('Pedro Oliveira', 'exemplo05@teste.com', '$2a$10$hleet8CAQHml3gRwovtmIeA52jxwrpLZ/MsOQyO0dAOIoBTTdd/W6','https://publish-p47754-e237306.adobeaemcloud.com/adobe/dynamicmedia/deliver/dm-aid--914bcfe0-f610-4610-a77e-6ea53c53f630/_330603286208.app.webp?preferwebp=true&width=312', 5, 5, 5, 5, 1),
('Julia Pereira', 'exemplo06@teste.com', '$2a$10$hleet8CAQHml3gRwovtmIeA52jxwrpLZ/MsOQyO0dAOIoBTTdd/W6','https://publish-p47754-e237306.adobeaemcloud.com/adobe/dynamicmedia/deliver/dm-aid--914bcfe0-f610-4610-a77e-6ea53c53f630/_330603286208.app.webp?preferwebp=true&width=312', 6, 6, 6, 6, 1),
('Lucas Costa', 'exemplo07@teste.com', '$2a$10$hleet8CAQHml3gRwovtmIeA52jxwrpLZ/MsOQyO0dAOIoBTTdd/W6','https://publish-p47754-e237306.adobeaemcloud.com/adobe/dynamicmedia/deliver/dm-aid--914bcfe0-f610-4610-a77e-6ea53c53f630/_330603286208.app.webp?preferwebp=true&width=312', 7, 7, 7, 7, 1);

-- crie uma massa de dados com 20 employee_services

INSERT INTO employee_services (hours_spent, expertise, fk_employee, fk_service, fk_status) VALUES
(1, 1, 1, 1, 1),
(2, 2, 2, 2, 1),
(3, 3, 3, 3, 1),
(4, 4, 4, 4, 1),
(5, 5, 5, 5, 1),
(6, 6, 6, 6, 1),
(7, 7, 7, 7, 1),
(5, 5, 5, 5, 1),
(6, 6, 6, 6, 1),
(7, 7, 7, 7, 1);

-- crie uma massa de dados com 20 clients -- todo email é exemplo02@teste.com e a senha é 123456

INSERT INTO client (name, email, password, img_url, cpf, fk_local, fk_phone) VALUES
('Fernando Oliveira', 'exemplo08@teste.com', '$2a$10$hleet8CAQHml3gRwovtmIeA52jxwrpLZ/MsOQyO0dAOIoBTTdd/W6', 'https://negociosdebeleza.beautyfair.com.br/wp-content/uploads/2022/12/cabeleireira-811x526.png', '12345678901', 1, 1),
('Amanda Pereira', 'exemplo09@teste.com', '$2a$10$hleet8CAQHml3gRwovtmIeA52jxwrpLZ/MsOQyO0dAOIoBTTdd/W6', 'https://negociosdebeleza.beautyfair.com.br/wp-content/uploads/2022/12/cabeleireira-811x526.png', '12345678902', 2, 2),
('Ricardo Souza', 'exemplo10@teste.com', '$2a$10$hleet8CAQHml3gRwovtmIeA52jxwrpLZ/MsOQyO0dAOIoBTTdd/W6', 'https://negociosdebeleza.beautyfair.com.br/wp-content/uploads/2022/12/cabeleireira-811x526.png', '12345678903', 3, 3),
('Juliana Santos', 'exemplo11@teste.com', '$2a$10$hleet8CAQHml3gRwovtmIeA52jxwrpLZ/MsOQyO0dAOIoBTTdd/W6', 'https://negociosdebeleza.beautyfair.com.br/wp-content/uploads/2022/12/cabeleireira-811x526.png', '12345678904', 4, 4),
('Gabriela Silva', 'exemplo12@teste.com', '$2a$10$hleet8CAQHml3gRwovtmIeA52jxwrpLZ/MsOQyO0dAOIoBTTdd/W6', 'https://negociosdebeleza.beautyfair.com.br/wp-content/uploads/2022/12/cabeleireira-811x526.png', '12345678905', 5, 5),
('Bruno Costa', 'exemplo13@teste.com', '$2a$10$hleet8CAQHml3gRwovtmIeA52jxwrpLZ/MsOQyO0dAOIoBTTdd/W6', 'https://negociosdebeleza.beautyfair.com.br/wp-content/uploads/2022/12/cabeleireira-811x526.png', '12345678906', 6, 6),
('Patricia Dias', 'exemplo14@teste.com', '$2a$10$hleet8CAQHml3gRwovtmIeA52jxwrpLZ/MsOQyO0dAOIoBTTdd/W6', 'https://negociosdebeleza.beautyfair.com.br/wp-content/uploads/2022/12/cabeleireira-811x526.png', '12345678907', 7, 7);

-- crie uma massa de dados com 20 ratings

INSERT INTO rating (avaliation, fk_establishment, fk_employee, fk_service, fk_client, fk_product) VALUES
(1, 1, 1, 1, 1, 1),
(2, 2, 2, 2, 2, 2),
(3, 3, 3, 3, 3, 3),
(4, 4, 4, 4, 4, 4),
(5, 5, 5, 5, 5, 5),
(1, 1, 1, 1, 1, 1),
(2, 2, 2, 2, 2, 2),
(3, 3, 3, 3, 3, 3),
(4, 4, 4, 4, 4, 4),
(5, 5, 5, 5, 5, 5);

-- crie uma massa de dados com 20 schedules

INSERT INTO schedule (date_time, fk_service, fk_status, fk_client, fk_employee) VALUES
('2024-08-31 08:00:00', 1, 1, 1, 1),
('2024-08-31 09:00:00', 2, 1, 2, 2),
('2024-08-31 10:00:00', 3, 1, 3, 3),
('2024-08-31 11:00:00', 4, 1, 4, 4);

-- crie uma massa de dados com 20 orders

INSERT INTO orders (date_time, fk_status, fk_client) VALUES
('2024-08-31 08:00:00', 1, 1),
('2024-08-31 09:00:00', 1, 2),
('2024-08-31 10:00:00', 1, 3),
('2024-08-31 11:00:00', 1, 4),
('2024-08-31 12:00:00', 1, 4),
('2024-08-31 13:00:00', 1, 4),
('2024-08-31 14:00:00', 1, 4),
('2024-08-31 15:00:00', 1, 4),
('2024-08-31 20:00:00', 1, 4);
-- crie uma massa de dados com 20 markets

INSERT INTO market (quantity, fk_product, fk_order) VALUES
(1, 1, 1),
(2, 2, 2),
(3, 3, 3),
(4, 4, 4);

-- crie uma massa de dados com 20 payments

INSERT INTO payment (date_payment, fk_schedule, fk_market, fk_status) VALUES
('2024-08-31 08:00:00', 1, 1, 1),
('2024-08-31 09:00:00', 2, 2, 1),
('2024-08-31 10:00:00', 3, 3, 1),
('2024-08-31 11:00:00', 4, 4, 1);

-- crie uma massa de dados com 20 images

INSERT INTO image (name, path, file_extension, file_size, fk_client, fk_product, fk_service, fk_employee, fk_establishment) VALUES
('Imagem 1', 'http://image-url.com/imagem1', '.jpg', 1.5, 1, null, null, null, null),
('Imagem 2', 'http://image-url.com/imagem2', '.jpg', 1.5, null, 2, null, null, null),
('Imagem 3', 'http://image-url.com/imagem3', '.jpg', 1.5, 3, null, 3, null, null),
('Imagem 4', 'http://image-url.com/imagem4', '.jpg', 1.5, null, null, 4, null, null),
('Imagem 5', 'http://image-url.com/imagem5', '.jpg', 1.5, 4, null, null, null, null),
('Imagem 6', 'http://image-url.com/imagem6', '.jpg', 1.5, 4, null, null, null, null),
('Imagem 7', 'http://image-url.com/imagem7', '.jpg', 1.5, null, null, null, null, null),
('Imagem 8', 'http://image-url.com/imagem8', '.jpg', 1.5, 2, null, null,null, null),
('Imagem 9', 'http://image-url.com/imagem9', '.jpg', 1.5, null, 2, null, null, null),
('Imagem 10', 'http://image-url.com/imagem10', '.jpg', 1.5, null, null, 2, null, null);
