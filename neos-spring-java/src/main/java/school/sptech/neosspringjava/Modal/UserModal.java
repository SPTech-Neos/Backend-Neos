package school.sptech.neosspringjava.Modal;
import school.sptech.neosspringjava.DataBase.DatabaseConnectionManager;
import school.sptech.neosspringjava.services.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserModal {
    private DatabaseConnectionManager connectionManager = new DatabaseConnectionManager() ;

        public User createUser(User user) {
            Connection connection = connectionManager.getConnection();
            if (connection != null || connection != null) {
                try {
                    String sql = "INSERT INTO users (name, email, password, permission, telephone, cpf) VALUES (?, ?, ?, ?, ?, ?)";
                    /*if de vetor*/if (connectionManager.verificarTipo() == "VETOR"){
                    return  connectionManager.getConector().executar("INSERT",user);
                    }else {
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.setString(1, user.getName());
                    statement.setString(2, user.getEmail());
                    statement.setString(3, user.getPassword());
                    statement.setInt(4, user.getPermission());
                    statement.setString(5, user.getTelephone());
                    statement.setString(6, user.getCpf());

                    int rowsInserted = statement.executeUpdate();
                    if (rowsInserted > 0) {

                        System.out.println("Usuário cadastrado com sucesso.");
                        return user;
                    }
                        statement.close();
                    }
                } catch (SQLException e) {
                    System.out.println("Erro ao cadastrar usuário: " + e.getMessage());
                    return null;
                }
            } else {
                System.out.println("Não foi possível cadastrar o usuário. A conexão não está estabelecida.");
            return  null;
            }

            return user;
        }
    public User updateUser(User user) {
        if (connectionManager.isConnected()) {
            Connection connection = connectionManager.getConnection();

            try {
                String sql = "UPDATE users SET name = ?, email = ?, password = ?, permission = ?, telephone = ?, cpf = ? WHERE id = ?";
                /*if de vetor*/if (connectionManager.verificarTipo() == "VETOR"){
                    connectionManager.getConector().executar("UPDATE",user);
                }else {
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.setString(1, user.getName());
                    statement.setString(2, user.getEmail());
                    statement.setString(3, user.getPassword());
                    statement.setInt(1, user.getPermission());
                    statement.setString(1, user.getTelephone());
                    statement.setString(1, user.getCpf());
                    statement.setInt(1, user.getId());

                    int rowsUpdated = statement.executeUpdate();
                    if (rowsUpdated > 0) {
                        System.out.println("Usuário atualizado com sucesso.");
                        return user;
                    } else {
                        System.out.println("Nenhum usuário atualizado.");
                        return user;
                    }

                    //statement.close();
                }
            } catch (SQLException e) {
                System.out.println("Erro ao atualizar usuário: " + e.getMessage());
            }
        } else {
            System.out.println("Não foi possível atualizar o usuário. A conexão não está estabelecida.");
        }
        return user;
    }

    public User getUser(int userId) {
        User user = null;

        if (connectionManager.isConnected()) {
            Connection connection = connectionManager.getConnection();

            try {
                String sql = "SELECT * FROM users WHERE id = ?";
                /*if de vetor*/
                if (connectionManager.getConector().verificarTipo() == "VETOR") {
                    return connectionManager.getConector().executar("SELECT", user);
                } else {
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.setInt(1, userId);

                    ResultSet resultSet = statement.executeQuery();
                    if (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String name = resultSet.getString("name");
                        String email = resultSet.getString("email");
                        String password = resultSet.getString("password");
                        Integer permission = resultSet.getInt("permission");
                        String telephone = resultSet.getString("telephone");
                        String cpf = resultSet.getString("cpf");

                        user = new User(id, name, email, password, permission, telephone, cpf);
                    }

                    resultSet.close();
                    statement.close();
                }

                return user; // Retorna o objeto User encontrado
            } catch (SQLException e) {
                System.out.println("Erro ao buscar usuário: " + e.getMessage());
                return null;
            }
        } else {
            System.out.println("Não foi possível buscar o usuário. A conexão não está estabelecida.");
            return null;
        }
    }

    public User login(String nomeLog, String passwordLog) {
        User user = null;

        if (connectionManager.isConnected()) {
            Connection connection = connectionManager.getConnection();

            try {
                String sql = "SELECT * FROM users WHERE nome = ? AND password = ?";
                /*if de vetor*/
                if (connectionManager.verificarTipo() == "VETOR") {
                    connectionManager.getConector().executar("SELECT", nomeLog, passwordLog);
                } else {
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.setString(1, nomeLog);
                    statement.setString(2, passwordLog);

                    ResultSet resultSet = statement.executeQuery();
                    if (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String name = resultSet.getString("name");
                        String email = resultSet.getString("email");
                        String password = resultSet.getString("password");
                        Integer permission = resultSet.getInt("permission");
                        String telephone = resultSet.getString("telephone");
                        String cpf = resultSet.getString("cpf");

                        user = new User(id, name, email, password, permission, telephone, cpf);
                    }

                    resultSet.close();
                    statement.close();
                }

                return user; // Retorna o objeto User encontrado
            } catch (SQLException e) {
                System.out.println("Erro ao buscar usuário: " + e.getMessage());
            }
        } else {
            System.out.println("Não foi possível buscar o usuário. A conexão não está estabelecida.");
        }

        return null;
    }
    public void deleteUser(int userId) {
        if (connectionManager.isConnected()) {
            Connection connection = connectionManager.getConnection();

            try {
                /*if de vetor*/if (connectionManager.verificarTipo() == "VETOR"){
                    connectionManager.getConector().executar("DELETE", userId);
                }else {
                    String sql = "DELETE FROM users WHERE id = ?";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.setInt(1, userId);

                    int rowsDeleted = statement.executeUpdate();
                    if (rowsDeleted > 0) {
                        System.out.println("Usuário excluído com sucesso.");
                    } else {
                        System.out.println("Nenhum usuário excluído.");
                    }

                    statement.close();
                }
            } catch (SQLException e) {
                System.out.println("Erro ao excluir usuário: " + e.getMessage());
            }
        } else {
            System.out.println("Não foi possível excluir o usuário. A conexão não está estabelecida.");
        }
    }
}

