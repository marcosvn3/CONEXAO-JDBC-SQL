package com.marcosvn3.jdbcTest.persistence;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static lombok.AccessLevel.PRIVATE;

// Esta classe ConnectorUtil é responsável por fornecer uma conexão com o banco de dados MySQL.
@NoArgsConstructor(access = PRIVATE)
public class ConnectorUtil {
    // TODO:Este método estático retorna uma conexão com o banco de dados.
    // O metodo pode lançar uma SQLException em caso de falha na conexão.
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/test-jdbc", "root", "000000");
    }
}
