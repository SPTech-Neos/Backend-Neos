-- Inserir endereços
INSERT INTO Address (street, city, state) VALUES ('123 Main St', 'Anytown', 'State');

-- Inserir locais
INSERT INTO Local (cep, number, floor, bloc, complement, address_id) VALUES ('CEP A', 101, 1, 'A', 'Near the entrance', (SELECT id FROM Address WHERE street='123 Main St'));

-- Inserir categorias de serviço
INSERT INTO Service_Category (name) VALUES ('Healthcare');

-- Inserir tipos de serviço
INSERT INTO Service_Type (name, service_category_id) VALUES ('General Checkup', (SELECT id FROM Service_Category WHERE name='Healthcare'));

-- Inserir serviços
INSERT INTO Service (specification, service_type_id) VALUES ('Complete physical examination', (SELECT id FROM Service_Type WHERE name='General Checkup'));

-- Inserir filtros
INSERT INTO Filter (price, service_id) VALUES (99.99, (SELECT id FROM Service WHERE specification='Complete physical examination'));

-- Inserir clientes
INSERT INTO Client (name, email, password, local_id) VALUES ('John Doe', 'john.doe@example.com', 'password123', (SELECT id FROM Local WHERE number=101));

-- Insert data into Employee_Type
INSERT INTO Employee_Type (name) VALUES ('Doctor');

-- Insert data into Establishment
INSERT INTO Establishment (name, assessment, end_shift, local_id, qtd_assessment, start_shift, cnpj, description)
VALUES ('Health Clinic', 4.5, '18:00:00', (SELECT id FROM Local WHERE number=101), 20, '08:00:00', '12345678901234', 'A general health clinic');

-- Insert data into Employee
INSERT INTO Employee (name, email, password, employee_type_id, establishment_id)
VALUES ('Dr. Smith', 'dr.smith@example.com', 'password456',
        (SELECT id FROM Employee_Type WHERE name='manicure'),
        (SELECT id FROM Establishment WHERE name='Health Clinic'));

-- Inserir produtos (exemplo)
INSERT INTO Product (name, price) VALUES ('Blood Test', 49.99);

-- Inserir agendamentos
INSERT INTO Scheduling (client_client_id, service_id, employee_id, date_time)
VALUES (
           (SELECT client_id FROM Client WHERE name='John Doe'),
           (SELECT id FROM Service WHERE specification='Complete physical examination'),
           (SELECT id FROM Employee WHERE name='Dr. Smith'),
           '2024-05-24T08:00:00'
       );



INSERT INTO address (street, city, state) VALUES
('Rua A', 'Cidade A', 'AA'),
('Rua B', 'Cidade B', 'BB'),
('Rua C', 'Cidade C', 'CC'),
('Rua D', 'Cidade D', 'DD');

INSERT INTO local (cep, number, floor, bloc, complement, address_id) VALUES
                                                                                 ('CEP A', 1, 1, 'A', 'Casa', 1),
                                                                                 ('CEP B', 2, 2, 'B', 'Apartamento', 2),
                                                                                 ('CEP C', 3, 3, 'C', 'Casa', 3),
                                                                                 ('CEP D', 4, 4, 'D', 'Apartamento', 4);

INSERT INTO client (name, email, password) VALUES
('Cliente A', 'cliente.a@example.com', '$2a$10$1h3qTcdIfxbfPyMKrDUAie5SzNfKyTk/HgvrtUSI.3ZyElQGy75We'),
('Cliente B', 'cliente.b@example.com', 'senha456'),
('Cliente C', 'cliente.c@example.com', 'senha789'),
('Cliente D', 'cliente.d@example.com', 'senhaabc'),
('Cliente Funcionario', 'cliente.funcionario@example.com', '$2a$10$kb6w6bL6NG.p8ZjjMIQWs.496gUWEC9cXA1StRkgtRcJz1HOWQubS');


INSERT INTO establishment (name,end_shift, start_shift, local_id, qtd_assessment, assessment, cnpj) VALUES
('Estabelecimento A', '18:00:00', '08:00:00', 1, 20, 4.8, '12345678901239'),
('Estabelecimento B', '18:00:00', '08:00:00', 2, 20, 4.5, '12385678901239');

INSERT Into Employee_type (name) VALUES
('Gerente'),
('Funcionario');

INSERT INTO employee (name,email, password, employee_type_id, establishment_id) VALUES
('Funcionario A', 'gg@gmail.com', '123', 2, 2),
('Gerente A', 'dd@gmail.com', '123', 1, 3),
('Cliente Funcionario', 'cliente.funcionario@example.com', '123senha', 2, 3);

