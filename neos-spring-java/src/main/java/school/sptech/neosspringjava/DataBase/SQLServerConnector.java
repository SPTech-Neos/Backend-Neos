package school.sptech.neosspringjava.database;


import school.sptech.neosspringjava.database.DatabaseConnector;
import school.sptech.neosspringjava.modal.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLServerConnector implements DatabaseConnector {
    private static final String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=db_name";
    private static final String USER = "username";
    private static final String PASSWORD = "password";

    private Connection connection;

    public Connection connect() {
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            System.out.println("Conexão com o SQL Server estabelecida.");
        } catch (SQLException e) {
            System.out.println("Erro ao conectar-se ao SQL Server: " + e.getMessage());
        }
        return null;
    }

    public void disconnect() {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("Conexão com o SQL Server encerrada.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao desconectar-se do SQL Server: " + e.getMessage());
        }
    }
    @Override
    public String verificarTipo() {
        return "SQLSERVER";
    }

    @Override
    public User executar(String insert, User user) {
        return null;

    }

    @Override
    public User executar(String operacao, int parametro) {
        return null;
    }

    @Override
    public User executar(String operacao, String nomeLog, String passwordLog) {
        return null;
    }
}