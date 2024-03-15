package school.sptech.neosspringjava.DataBase;
import school.sptech.neosspringjava.database.SQLServerConnector;

import java.sql.Connection;

public class DatabaseConnectionManager {
    private DatabaseConnector connector;
    private String Ambiente = "DEV-VET";
    public void setConnector() {
        if (Ambiente == "DEV") {
            MySQLConnector mySQLConnector = new MySQLConnector();
            this.connector = mySQLConnector;
        } else if (Ambiente == "PROD") {
            SQLServerConnector sqlServerConnector = new SQLServerConnector();
            this.connector = sqlServerConnector;
        }else if (Ambiente == "DEV-VET"){

        }
        connect();
    }
    public void setConnector(String ambiente) {
        disconnect();
        Ambiente = ambiente;
        if (Ambiente == "DEV") {
            MySQLConnector mySQLConnector = new MySQLConnector();
            this.connector = mySQLConnector;
        } else if (Ambiente == "PROD") {
            SQLServerConnector sqlServerConnector = new SQLServerConnector();
            this.connector = sqlServerConnector;
        }else if (Ambiente == "DEV-VET"){

        }
        connect();
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
    public DatabaseConnector getConector(){return connector;};

}
