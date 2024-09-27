CREATE TABLE employees(
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome_employee VARCHAR(128) NOT NULL,
    salario_employee DECIMAL(10,2) NOT NULL,
    aniversario_employee TIMESTAMP NOT NULL,
    PRIMARY KEY(id)
);