// package school.sptech.neosspringjava.db;
// import java.sql.Connection;
// import java.sql.DriverManager;
// import java.sql.SQLException;

// public class DatabaseConector {

//     public Connection getCurrentConnection() throws SQLException {
//         String activeProfile = System.getProperty("db.profile");

//         if ("dev".equals(activeProfile)) {
//             return getDevConnection();
//         } else if ("prod".equals(activeProfile)) {
//             return getProdConnection();
//         }

//         throw new IllegalArgumentException("Invalid active profile: " + activeProfile);
//     }

//     private Connection getDevConnection() throws SQLException {
//         // Configuração da conexão do banco de dados para ambiente de desenvolvimento (MySQL)
//         String url = "jdbc:mysql://localhost:3306/database";
//         String username = "username";
//         String password = "password";
//         return DriverManager.getConnection(url, username, password);
//     }

//     private Connection getProdConnection() throws SQLException {
//         // Configuração da conexão do banco de dados para ambiente de produção (SQL Server)
//         String url = "jdbc:sqlserver://localhost:1433;databaseName=database";
//         String username = "username";
//         String password = "password";
//         return DriverManager.getConnection(url, username, password);
//     }

// }
