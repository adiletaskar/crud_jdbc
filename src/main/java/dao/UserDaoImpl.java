package dao;

import model.User;
import util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    public void addUser(User user) {
        String query = "INSERT INTO users (id,username, email,password) VALUES (?,?, ?, ?)";
        try (Connection connection = DatabaseUtil.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, user.getId());
            statement.setString(2, user.getUsername());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUser(int id) {
        String query = "SELECT id, username, email FROM users WHERE id = ?";
        try (Connection connection = DatabaseUtil.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int user_id = resultSet.getInt("id");
                    String name = resultSet.getString("username");
                    String email = resultSet.getString("email");

                    return new User(user_id, name, email);
                } else {
                    System.out.println("User not found.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public List<User> getAllUsers() {
        String query = "SELECT * from USERS";
        try(Connection connection = DatabaseUtil.getConnection();PreparedStatement statement = connection.prepareStatement(query)) {
            try(ResultSet resultSet = statement.executeQuery()){
                List<User> users = new ArrayList<>();

                while(resultSet.next()){
                    int user_id = resultSet.getInt("id");
                    String name = resultSet.getString("username");
                    String email = resultSet.getString("email");
                    users.add(new User(user_id, name, email));
                }
                return users;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateUser(User user) {
        String query = "UPDATE users SET username = ?, email = ?, password = ? WHERE id = ?";
        try(Connection connection = DatabaseUtil.getConnection();PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1,user.getUsername());
            statement.setString(2,user.getEmail());
            statement.setString(3,user.getPassword());
            statement.setInt(4,user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteUser(int id) {
        String query = "DELETE FROM users WHERE id = ?";
        try(Connection connection = DatabaseUtil.getConnection();PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1,id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
