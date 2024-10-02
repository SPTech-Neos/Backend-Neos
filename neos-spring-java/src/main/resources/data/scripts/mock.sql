
INSERT INTO Address VALUES(1,'Rua Coração de Maçã', 'São Paulo', '08474230', 'SP');


INSERT INTO Local VALUES (1, 211, 5, '52', 'C', 1);

INSERT INTO Phone VALUES (1, '55', '11', '933357637');

INSERT INTO Status VALUES (1, 'Ativo', 'Estabelecimeto');
INSERT INTO Status VALUES (2, 'Inativo', 'Estabelecimeto');
INSERT INTO Status VALUES (3, 'Aguardando Pagamento', 'Pagamento');
INSERT INTO Status VALUES (4, 'Em andamento', 'Pagamento');
INSERT INTO Status VALUES (5, 'cancelado', 'order');
INSERT INTO Status VALUES (6, 'concluido', 'order');
INSERT INTO Status VALUES (7, 'Em andamento', 'order');

INSERT INTO Establishment VALUES (1, 'aditumIdTeste', 'KevinSalon', null, 1, 1, 1, '08:00:00', '18:00:00', 'Salão de beleza Kevin', '12.345.678/0001-90');
INSERT INTO Establishment VALUES (2, 'aditumIdTeste2', 'LiraSalon', null, 1, 1, 2, '09:00:00', '17:00:00', 'Salão de beleza Lira', '98.765.432/0001-12');

INSERT INTO Product VALUES (1, 'Shampoo Cachos Suaves', 'Seda', 'img.example', 120.0, 1 , 1, 1);

INSERT INTO ServiceCategory VALUES(1, 'Cabelo');

INSERT INTO ServiceType VALUES(1, 'Corte de cabelo', 1);

INSERT INTO Service VALUES(1, 'Mullet', 'aditumId', 90.0, 'teste', 1);

INSERT INTO EmployeeType VALUES (1, 'Admin');

INSERT INTO Employee VALUES (1, 'Kevin', 'keviin@email.com', '$2a$10$aii7/bEjM0F1cyEbgG4aQu6kwe0mraOmeXzI2z1/MRDimtgZYM7.W', 'teste', 1, 1, 1, 1, 1);

INSERT INTO Client VALUES (1, 'Kevin', 'kevin@email.com', '$2a$10$aii7/bEjM0F1cyEbgG4aQu6kwe0mraOmeXzI2z1/MRDimtgZYM7.W', 'teste', '50709903812', 1, 1);

INSERT INTO Schedule VALUES (1,'2024-08-31 22:00:00',1,1,1,1 );

INSERT INTO Orders VALUES (1, '2024-08-31 22:00:00', 1, 1);

INSERT INTO Market VALUES (1, 1, 1, 1);

INSERT INTO Payment ( datePayment, fkMarket, fkStatus) VALUES ( '2024-08-31 22:00:00', 1, 1);
INSERT INTO Payment ( datePayment, fkSchedule, fkStatus) VALUES ( '2024-08-31 22:00:00', 1, 1);

