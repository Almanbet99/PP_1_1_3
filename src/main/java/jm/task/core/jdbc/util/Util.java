package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/preproject?useSSL=false&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "tahmina09090+++";

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            connection.setAutoCommit(false);
            System.out.println("✅ Подключение к базе успешно");
            return connection;
        } catch (SQLException e) {
            System.err.println("❌ Ошибка подключения к базе: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("❌ JDBC Driver не найден: " + e.getMessage());
        }
        return null;
    }
}

