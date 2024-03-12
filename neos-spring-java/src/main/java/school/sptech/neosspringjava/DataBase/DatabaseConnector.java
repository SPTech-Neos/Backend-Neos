package school.sptech.neosspringjava.DataBase;

import java.sql.Connection;

public interface DatabaseConnector {
    Connection connect();
    void disconnect();
}
