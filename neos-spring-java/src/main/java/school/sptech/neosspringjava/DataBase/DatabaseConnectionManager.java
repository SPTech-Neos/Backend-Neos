package school.sptech.neosspringjava.DataBase;

public class DatabaseConnectionManager {
    private DatabaseConnector connector;

    public void setConnector(DatabaseConnector connector) {
        this.connector = connector;
    }

    public void connect() {
        connector.connect();
    }

    public void disconnect() {
        connector.disconnect();
    }

   }
