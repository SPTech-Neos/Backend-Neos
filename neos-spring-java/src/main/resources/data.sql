-- Inserir endereços
INSERT INTO Address (id,street, city, state) VALUES (1,'123 Main St', 'Anytown', 'State');

-- Inserir locais
INSERT INTO Local (number, floor, bloc, complement, address_id) VALUES (101, 1, 'A', 'Near the entrance', (SELECT id FROM Address WHERE street='123 Main St'));

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
INSERT INTO Scheduling (client_client_id, service_id, product_id, employee_id, date_time) 
VALUES (
    (SELECT client_id FROM Client WHERE name='John Doe'),
    (SELECT id FROM Service WHERE specification='Complete physical examination'),
    (SELECT id FROM Product WHERE name='Blood Test'),
    (SELECT id FROM Employee WHERE name='Dr. Smith'),
    '2024-05-24T08:00:00'
);


