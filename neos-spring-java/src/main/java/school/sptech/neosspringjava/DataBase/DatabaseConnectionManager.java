package school.sptech.neosspringjava.DataBase;

import java.sql.Connection;

public class DatabaseConnectionManager {
    private DatabaseConnector connector;

    public void setConnector(DatabaseConnector connector) {
        this.connector = connector;
    }

    public Connection connect() {
       return connector.connect();
    }

    public void disconnect() {
        connector.disconnect();
    }

    public boolean isConnected() {
        return true;
    }

    public Connection getConnection() {
       return connect();
    }
}
