package school.sptech.neosspringjava.DataBase;

import school.sptech.neosspringjava.DataBase.DatabaseConnector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLServerConnector implements DatabaseConnector {
    private static final String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=db_name";
    private static final String USER = "username";
    private static final String PASSWORD = "password";

    private Connection connection;

    public void connect() {
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            System.out.println("Conexão com o SQL Server estabelecida.");
        } catch (SQLException e) {
            System.out.println("Erro ao conectar-se ao SQL Server: " + e.getMessage());
        }
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
}