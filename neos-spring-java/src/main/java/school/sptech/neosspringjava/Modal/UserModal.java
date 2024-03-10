package school.sptech.neosspringjava.Modal;

import school.sptech.neosspringjava.DataBase.DatabaseConnectionManager;
import school.sptech.neosspringjava.services.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserModal {
    private DatabaseConnectionManager connectionManager;

    public UserModal(DatabaseConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    public void createUser(User user) {
        if (connectionManager.isConnected()) {
            Connection connection = connectionManager.getConnection();

            try {
                String sql = "INSERT INTO users (name, email, password) VALUES (?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setInt(1, user.getId());
                statement.setString(1, user.getName());
                statement.setString(2, user.getEmail());
                statement.setString(3, user.getPassword());
                statement.setInt(1, user.getPermission());
                statement.setString(1, user.getTelephone());
                statement.setString(1, user.getCpf());

                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("Usuário cadastrado com sucesso.");
                }

                statement.close();
            } catch (SQLException e) {
                System.out.println("Erro ao cadastrar usuário: " + e.getMessage());
            }
        } else {
            System.out.println("Não foi possível cadastrar o usuário. A conexão não está estabelecida.");
        }
    }

    public void updateUser(User user) {
        if (connectionManager.isConnected()) {
            Connection connection = connectionManager.getConnection();

            try {
                String sql = "UPDATE users SET name = ?, email = ?, password = ?, permission = ?, telephone = ?, cpf = ? WHERE id = ?";
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
                } else {
                    System.out.println("Nenhum usuário atualizado.");
                }

                statement.close();
            } catch (SQLException e) {
                System.out.println("Erro ao atualizar usuário: " + e.getMessage());
            }
        } else {
            System.out.println("Não foi possível atualizar o usuário. A conexão não está estabelecida.");
        }
    }

    public User getUser(int userId) {
        User user = null;

        if (connectionManager.isConnected()) {
            Connection connection = connectionManager.getConnection();

            try {
                String sql = "SELECT * FROM users WHERE id = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setInt(1, userId);

                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String email = resultSet.getString("email");
                    String password = resultSet.getString("password");
                    String permission = resultSet.getString("permission");
                    String telephone = resultSet.getString("telephone");
                    String cpf = resultSet.getString("cpf");

                    user = new User(id, name, email, password,permission,telephone,cpf);
                }

                resultSet.close();
                statement.close();
            } catch (SQLException e) {
                System.out.println("Erro ao buscar usuário: " + e.getMessage());
            }
        } else {
            System.out.println("Não foi possível buscar o usuário. A conexão não está estabelecida.");
        }

        return user;
    }

    public User login (String nomeLog, String passwordLog) {
        User user = null;

        if (connectionManager.isConnected()) {
            Connection connection = connectionManager.getConnection();

            try {
                String sql = "SELECT * FROM users WHERE nome = ? AND password =?" ;
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, nomeLog);
                statement.setString(1, passwordLog);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String email = resultSet.getString("email");
                    String password = resultSet.getString("password");
                    String permission = resultSet.getString("permission");
                    String telephone = resultSet.getString("telephone");
                    String cpf = resultSet.getString("cpf");

                    user = new User(id, name, email, password,permission,telephone,cpf);
                }

                resultSet.close();
                statement.close();
            } catch (SQLException e) {
                System.out.println("Erro ao buscar usuário: " + e.getMessage());
            }
        } else {
            System.out.println("Não foi possível buscar o usuário. A conexão não está estabelecida.");
        }

        return user;
    }
    
    public void deleteUser(int userId) {
        if (connectionManager.isConnected()) {
            Connection connection = connectionManager.getConnection();

            try {
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
            } catch (SQLException e) {
                System.out.println("Erro ao excluir usuário: " + e.getMessage());
            }
        } else {
            System.out.println("Não foi possível excluir o usuário. A conexão não está estabelecida.");
        }
    }
}

