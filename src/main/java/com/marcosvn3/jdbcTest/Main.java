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


//        var employee = new EmployeeEntity();
//        employee.setNome("cccccc");
//        employee.setSalario(new BigDecimal(3000));
//        employee.setAniversario(OffsetDateTime.now().minusYears(50).plusMonths(3).minusDays(4));
//        System.out.println(employee);
//        employeeDAO.insert(employee);
//        System.out.println(employee);

        //employeeDAO.findAll().forEach(System.out::println);


        //System.out.println(employeeDAO.findById(3));


//        var employee = new EmployeeEntity();
//
//        employee.setId(3);
//        employee.setNome("William D");
//        employee.setSalario(new BigDecimal(5500));
//        employee.setAniversario(OffsetDateTime.now().minusYears(26).minusMonths(7).minusDays(9));
//        employeeDAO.update(employee);


        employeeDAO.delete(9);


    }
}
