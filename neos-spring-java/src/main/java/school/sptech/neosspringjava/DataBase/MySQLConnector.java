package school.sptech.neosspringjava.database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import school.sptech.neosspringjava.modal.User;

public class MySQLConnector implements DatabaseConnector {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/db_name";
    private static final String USER = "username";
    private static final String PASSWORD = "password";

    private Connection connection;

    public Connection connect() {
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            System.out.println("Conexão com o MySQL estabelecida.");
            return connection;
        } catch (SQLException e) {
            System.out.println("Erro ao conectar-se ao MySQL: " + e.getMessage());
        }
        return null;
    }

    public void disconnect() {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("Conexão com o MySQL encerrada.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao desconectar-se do MySQL: " + e.getMessage());
        }
    }

    @Override
    public String verificarTipo() {
        return "MYSQL";
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
