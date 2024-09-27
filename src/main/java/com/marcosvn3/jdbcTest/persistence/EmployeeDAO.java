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

    // TODO:Método para inserir um novo funcionário no banco de dados
    public void insert(EmployeeEntity entity) {
        try(
                // Obtém uma conexão com o banco de dados
                var connection = ConnectorUtil.getConnection();
                // Cria um statement para executar comandos SQL
                var statement = connection.createStatement()
        ){
            // Prepara a string SQL para inserção
            var sql = "INSERT INTO employees (nome,salario,aniversario) values('"+
                    entity.getNome()+"',"+
                    entity.getSalario()+","+
                    "'"+formatOffSetDateTime(entity.getAniversario())+"')";

            // Executa o comando SQL de inserção
            statement.executeUpdate(sql);
            // Obtém o número de linhas afetadas
            statement.getUpdateCount();

            // Imprime o número de registros afetados
            System.out.printf("Foram afetados %s registros na base de dados", statement.getUpdateCount());

            // Se o statement for uma instância de StatementImpl, obtém o ID do último registro inserido
            if (statement instanceof StatementImpl impl) {
                entity.setId(impl.getLastInsertID());
            }
        }catch( Exception e){
            // Em caso de erro, imprime o stack trace
            e.printStackTrace();
        }
    }

    // TODO:Método para atualizar as informações de um funcionário existente
    public void update(EmployeeEntity entity) {
        try(
                // Obtém uma conexão com o banco de dados
                var connection = ConnectorUtil.getConnection();
                // Cria um statement para executar comandos SQL
                var statement = connection.createStatement()
        ){
            // Prepara a string SQL para atualização
            var sql = "UPDATE employees set " +
                    "nome= '"+entity.getNome()+"', " +
                    "salario="+entity.getSalario()+", " +
                    "aniversario='"+
                    entity.getAniversario()+"' " +
                    "where id="+entity.getId();
            // Executa o comando SQL de atualização
            statement.executeUpdate(sql);

            // Se o statement for uma instância de StatementImpl, obtém o ID do último registro atualizado
            if (statement instanceof StatementImpl impl) {
                entity.setId(impl.getLastInsertID());
            }
        }catch( Exception e){
            // Em caso de erro, imprime o stack trace
            e.printStackTrace();
        }
    }

    // TODO:Método para excluir um funcionário com base no seu ID
    public void delete(final long id) {
        try(
                // Obtém uma conexão com o banco de dados
                var connection = ConnectorUtil.getConnection();
                // Cria um statement para executar comandos SQL
                var statement = connection.createStatement()
        ){
            // Imprime os detalhes do funcionário a ser excluído
            System.out.println(findById(id));

            // Prepara e executa o comando SQL de exclusão
            var sql = "DELETE FROM employees WHERE id="+id;
            statement.executeUpdate(sql);

            // Imprime mensagem de sucesso
            System.out.println("Deletado com sucesso!");
        }catch( Exception e){
            // Em caso de erro, imprime o stack trace
            e.printStackTrace();
        }
    }

    // TODO:Método para buscar todos os funcionários
    public List<EmployeeEntity> findAll() {
        List<EmployeeEntity>entities = new ArrayList<EmployeeEntity>();
        try(
                // Obtém uma conexão com o banco de dados
                var connection = ConnectorUtil.getConnection();
                // Cria um statement para executar comandos SQL
                var statement = connection.createStatement()
        ){
            // Executa a query para selecionar todos os funcionários ordenados por nome
            statement.executeQuery("SELECT * FROM employees ORDER BY nome_employee");
            var resultSet = statement.getResultSet();

            // Itera sobre o resultado, criando objetos EmployeeEntity para cada registro
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
            // Em caso de erro, imprime o stack trace
            e.printStackTrace();
        }
        return entities;
    }

    // TODO:Metodo para buscar um funcionário específico pelo seu ID
    public EmployeeEntity findById(final long id) {
        var entity = new EmployeeEntity();

        try(
            // Obtém uma conexão com o banco de dados
            var connection = ConnectorUtil.getConnection();
            // Cria um statement para executar comandos SQL
            var statement = connection.createStatement();
        ){
            // Executa a query para selecionar o funcionário com o ID especificado
            statement.executeQuery("SELECT * FROM employees WHERE id=" + id);
            var resultSet = statement.getResultSet();

            // Se encontrar o funcionário, preenche o objeto EmployeeEntity com os dados
            if(resultSet.next()) {
                entity.setNome(resultSet.getString("nome"));
                entity.setSalario(resultSet.getBigDecimal("salario"));
                var aniversarioInstant = resultSet.getTimestamp("aniversario").toInstant();
                entity.setAniversario(OffsetDateTime.ofInstant(aniversarioInstant, ZoneOffset.UTC));
            }
        } catch (SQLException e) {
            // Em caso de erro SQL, imprime o stack trace
            e.printStackTrace();
        }
        return entity;
    }

    // TODO:Método auxiliar para formatar OffsetDateTime para o formato esperado pelo banco de dados
    private String formatOffSetDateTime(final OffsetDateTime dateTime) {
        var utcdateTime = dateTime.withOffsetSameInstant(ZoneOffset.UTC);
        return utcdateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

}
