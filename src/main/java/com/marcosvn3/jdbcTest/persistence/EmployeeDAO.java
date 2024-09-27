package com.marcosvn3.jdbcTest.persistence;

import com.marcosvn3.jdbcTest.persistence.entity.EmployeeEntity;

import java.util.List;

// Esta classe EmployeeDAO é responsável por realizar operações de acesso a dados relacionadas aos funcionários.
public class EmployeeDAO {

    // Método para inserir um novo funcionário no banco de dados.
    // Atualmente, a implementação está vazia.
    public void insert(EmployeeEntity employee) {}

    // Método para atualizar as informações de um funcionário existente.
    // Atualmente, a implementação está vazia.
    public void update(EmployeeEntity employee) {}

    // Método para excluir um funcionário com base no seu ID.
    // Atualmente, a implementação está vazia.
    public void delete(final long id) {}

    // Método para buscar todos os funcionários.
    // Atualmente, retorna null, mas deveria retornar uma lista de EmployeeEntity.
    public List<EmployeeEntity> findAll(EmployeeEntity employee) {
        return null;
    }

    // Método para buscar um funcionário específico pelo seu ID.
    // Atualmente, retorna null, mas deveria retornar um EmployeeEntity.
    public EmployeeEntity findById(final long id) {
        return null;
    }

}
