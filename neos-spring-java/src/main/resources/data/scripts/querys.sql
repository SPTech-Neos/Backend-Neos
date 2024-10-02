


SELECT * FROM Product;
SELECT * FROM Orders;
SELECT * FROM Market;

SELECT m.quantity, p.datePayment, Product.price FROM Payment as p
  JOIN Market as m ON p.fkMarket= market_id
  JOIN Product ON m.fkProduct = product_id
  JOIN Establishment ON fkEstablishment = establishment_id;

SELECT * FROM Establishment;
SELECT * FROM Rating;
SELECT * FROM Employee;
SELECT * FROM Product;
SELECT * FROM Phone;

SELECT establishment_id, Establishment.name, TRUNCATE(AVG(avaliation), 1) as media FROM Rating
  JOIN Establishment ON fkEstablishment = establishment_id WHERE fkEstablishment IS true GROUP BY fkEstablishment ORDER BY media DESC		;
SELECT AVG(avaliation) as media FROM Rating WHERE fkEstablishment IS NOT NULL ORDER BY media;

-- total ganhos
SELECT
    SUM(IFNULL(s.price, 0) + IFNULL(m.quantity * prod.price, 0)) AS total_balance
FROM 
    Establishment e
LEFT JOIN Employee emp ON emp.fkEstablishment = e.establishment_id
LEFT JOIN Schedule sch ON sch.fkEmployee = emp.employee_id
LEFT JOIN Service s ON sch.fkService = s.service_id
LEFT JOIN Payment p ON p.fkSchedule = sch.schedule_id 
    AND p.datePayment BETWEEN '2024-08-31 00:00:00' AND '2024-08-31 23:59:00'
LEFT JOIN Market m ON m.fkOrder = p.fkMarket
LEFT JOIN Product prod ON m.fkProduct = prod.product_id
WHERE 
    e.establishment_id = 1;

-- quantidade e status

SELECT 
    COUNT(st.status_id) AS count_status,
    st.name AS status_servico
FROM 
    Schedule sc
JOIN 
    Status st ON sc.fkStatus = st.status_id
WHERE 
    sc.fkService IN (
        SELECT service_id 
        FROM Service 
        WHERE fkServiceType IN (
            SELECT service_type_id 
            FROM ServiceType
        )
    )
    AND sc.dateTime BETWEEN '2024-08-31 00:00:00' AND '2024-08-31 23:59:00'
    AND sc.fkService IN (
        SELECT service_id 
        FROM Service
        WHERE fkServiceType IN (
            SELECT service_type_id 
            FROM ServiceType
        )
    )
GROUP BY 
    st.name;

-- pegar todos os Payments de um estabelecimento
SELECT * FROM Payment join Schedule on schedule_id = fkSchedule join Employee on employee_id = fkEmployee join Establishment on establishment_id = fkEstablishment WHERE establishment_id = 1;
