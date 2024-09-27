package com.marcosvn3.jdbcTest;

import com.marcosvn3.jdbcTest.persistence.EmployeeDAO;
import org.flywaydb.core.Flyway;

// Esta é a classe principal do programa
public class Main {

    // Cria uma instância estática de EmployeeDAO para ser usada em toda a aplicação
    private final static EmployeeDAO employeeDAO = new EmployeeDAO();

    public static void main(String[] args) {
        // Configura o Flyway para gerenciar as migrações do banco de dados
        var flyway = Flyway
                .configure()
                // Define a conexão com o banco de dados MySQL
                .dataSource("jdbc:mysql://localhost:3306/test-jdbc", "root", "0000000")
                .load();
        // Executa as migrações pendentes no banco de dados
        flyway.migrate();
    }
}
