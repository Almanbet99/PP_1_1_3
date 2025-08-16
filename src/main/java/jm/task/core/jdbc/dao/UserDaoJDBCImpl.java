package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {}

    private void rollbackQuietly(Connection connection) {
        if (connection != null) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                System.err.println("Ошибка при rollback: " + e.getMessage());
            }
        }
    }

    @Override
    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users (" +
                "id BIGINT NOT NULL AUTO_INCREMENT, " +
                "name VARCHAR(50), " +
                "lastName VARCHAR(50), " +
                "age TINYINT, " +
                "PRIMARY KEY (id))";

        Connection connection = null;
        try {
            connection = Util.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            connection.commit();
            System.out.println("Таблица создана (если не существовала)");
        } catch (SQLException e) {
            rollbackQuietly(connection);
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) connection.close();
            } catch (SQLException ignored) {}
        }
    }

    @Override
    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS users";

        Connection connection = null;
        try {
            connection = Util.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            connection.commit();
            System.out.println("Таблица удалена (если существовала)");
        } catch (SQLException e) {
            rollbackQuietly(connection);
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) connection.close();
            } catch (SQLException ignored) {}
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";

        Connection connection = null;
        try {
            connection = Util.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, lastName);
            ps.setByte(3, age);
            ps.executeUpdate();
            connection.commit();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            rollbackQuietly(connection);
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) connection.close();
            } catch (SQLException ignored) {}
        }
    }

    @Override
    public void removeUserById(long id) {
        String sql = "DELETE FROM users WHERE id = ?";

        Connection connection = null;
        try {
            connection = Util.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, id);
            ps.executeUpdate();
            connection.commit();
            System.out.println("User с id = " + id + " удалён из базы");
        } catch (SQLException e) {
            rollbackQuietly(connection);
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) connection.close();
            } catch (SQLException ignored) {}
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM users";

        Connection connection = null;
        try {
            connection = Util.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setLastName(rs.getString("lastName"));
                user.setAge(rs.getByte("age"));
                userList.add(user);
            }

            connection.commit(); // Необязательно, но допустимо
        } catch (SQLException e) {
            rollbackQuietly(connection);
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) connection.close();
            } catch (SQLException ignored) {}
        }

        return userList;
    }

    @Override
    public void cleanUsersTable() {
        String sql = "TRUNCATE TABLE users";

        Connection connection = null;
        try {
            connection = Util.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            connection.commit();
            System.out.println("Таблица очищена");
        } catch (SQLException e) {
            rollbackQuietly(connection);
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) connection.close();
            } catch (SQLException ignored) {}
        }
    }
}
