package com.marcosvn3.jdbcTest.persistence.entity;

import lombok.Data;

// Importações de classes necessárias para os atributos
import java.math.BigDecimal;
import java.time.OffsetDateTime;

// Anotação @Data do Lombok para gerar automaticamente getters, setters, toString, etc.
@Data
// Declaração da classe EmployeeEntity
public class EmployeeEntity {

    // Atributo para armazenar o ID do funcionário
    private long id;

    // Atributo para armazenar o nome do funcionário
    private String nome;

    // Atributo para armazenar a data de aniversário do funcionário
    private OffsetDateTime aniversario;

    // Atributo para armazenar o salário do funcionário
    private BigDecimal salario;

}
