package com.marcosvn3.jdbcTest.persistence;

import com.marcosvn3.jdbcTest.persistence.entity.EmployeeEntity;
import com.mysql.cj.jdbc.StatementImpl;

import java.sql.SQLException;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

// Esta classe EmployeeDAO é responsável por realizar operações de acesso a dados relacionadas aos funcionários.
public class EmployeeDAO {

    // TODO:Metodo para inserir um novo funcionário no banco de dados.
    public void insert(EmployeeEntity entity) {
        try(
                var connection = ConnectorUtil.getConnection();
                var statement = connection.createStatement()
        ){
            var sql = "INSERT INTO employees (nome,salario,aniversario) values('"+
                    entity.getNome()+"',"+
                    entity.getSalario()+","+
                    "'"+formatOffSetDateTime(entity.getAniversario())+"')";

            statement.executeUpdate(sql);
            statement.getUpdateCount();

            System.out.printf("Foram afetados %s registros na base de dados", statement.getUpdateCount());

            if (statement instanceof StatementImpl impl) {
                entity.setId(impl.getLastInsertID());
            }
        }catch( Exception e){
            e.printStackTrace();
        }
    }

    // TODO:Metodo para atualizar as informações de um funcionário existente.
    public void update(EmployeeEntity entity) {
        try(
                var connection = ConnectorUtil.getConnection();
                var statement = connection.createStatement()
        ){

            var sql = "UPDATE employees set " +
                    "nome= '"+entity.getNome()+"', " +
                    "salario="+entity.getSalario()+", " +
                    "aniversario='"+
                    entity.getAniversario()+"' " +
                    "where id="+entity.getId();
            statement.executeUpdate(sql);

            if (statement instanceof StatementImpl impl) {
                entity.setId(impl.getLastInsertID());
            }
        }catch( Exception e){
            e.printStackTrace();
        }



    }

    // TODO:Metodo para excluir um funcionário com base no seu ID.
    public void delete(final long id) {
        try(
                var connection = ConnectorUtil.getConnection();
                var statement = connection.createStatement()
        ){

            System.out.println(findById(id));

            var sql = "DELETE FROM employees WHERE id="+id;
            statement.executeUpdate(sql);

            System.out.println("Deletado com sucesso!");
        }catch( Exception e){
            e.printStackTrace();
        }
    }

    // TODO:metodo para buscar todos os funcionários.
    public List<EmployeeEntity> findAll() {
        List<EmployeeEntity>entities = new ArrayList<EmployeeEntity>();
        try(
                var connection = ConnectorUtil.getConnection();
                var statement = connection.createStatement()
        ){

            statement.executeQuery("SELECT * FROM employees ORDER BY nome_employee");
            var resultSet = statement.getResultSet();

            while(resultSet.next()) {
                var entity = new EmployeeEntity();
                entity.setId(resultSet.getLong("id"));
                entity.setNome(resultSet.getString("nome"));
                entity.setSalario(resultSet.getBigDecimal("salario"));
                var aniversarioInstant = resultSet.getTimestamp("aniversario").toInstant();
                entity.setAniversario(OffsetDateTime.ofInstant(aniversarioInstant, ZoneOffset.UTC));

                entities.add(entity);
            }
        }catch( Exception e){
            e.printStackTrace();
        }
        return entities;
    }

    // TODO:Método para buscar um funcionário específico pelo seu ID.
    public EmployeeEntity findById(final long id) {
        var entity = new EmployeeEntity();

        try(var connection = ConnectorUtil.getConnection();
            var statement = connection.createStatement();)
        {
            statement.executeQuery("SELECT * FROM employees WHERE id=" + id);
            var resultSet = statement.getResultSet();

            if(resultSet.next()) {
                entity.setNome(resultSet.getString("nome"));
                entity.setSalario(resultSet.getBigDecimal("salario"));
                var aniversarioInstant = resultSet.getTimestamp("aniversario").toInstant();
                entity.setAniversario(OffsetDateTime.ofInstant(aniversarioInstant, ZoneOffset.UTC));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entity;
    }

    private String formatOffSetDateTime(final OffsetDateTime dateTime) {
        var utcdateTime = dateTime.withOffsetSameInstant(ZoneOffset.UTC);
        return utcdateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

}
