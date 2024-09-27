package com.marcosvn3.jdbcTest;

import com.marcosvn3.jdbcTest.persistence.EmployeeDAO;
import com.marcosvn3.jdbcTest.persistence.entity.EmployeeEntity;
import org.flywaydb.core.Flyway;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

// Esta é a classe principal do programa
public class Main {

    // Cria uma instância estática de EmployeeDAO para ser usada em toda a aplicação
    private final static EmployeeDAO employeeDAO = new EmployeeDAO();

    public static void main(String[] args) {
        // Configura o Flyway para gerenciar as migrações do banco de dados
        var flyway = Flyway
                .configure()
                // Define a conexão com o banco de dados MySQL
                .dataSource("jdbc:mysql://localhost:3306/test-jdbc", "root", "144571")
                .load();
        // Executa as migrações pendentes no banco de dados
        flyway.migrate();


        /*var employee = new EmployeeEntity();
        employee.setNome("Anthony s");
        employee.setSalario(new BigDecimal(4000));
        employee.setAniversario(OffsetDateTime.now().minusYears(22).minusDays(25));
        System.out.println(employee);
        employeeDAO.insert(employee);
        System.out.println(employee);*/

        //employeeDAO.findAll().forEach(System.out::println);


        System.out.println(employeeDAO.findById(3));


    }
}
