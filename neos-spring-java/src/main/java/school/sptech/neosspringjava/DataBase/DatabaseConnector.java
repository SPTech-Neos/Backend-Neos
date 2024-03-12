package school.sptech.neosspringjava.database;


import java.sql.Connection;

public interface DatabaseConnector {
    Connection connect();
    void disconnect();
}
