//package jm.task.core.jdbc;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//public class Util {
//    private static final String URL = "jdbc:mysql://localhost:3306/preproject?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
//    private static final String USERNAME = "root";
//    private static final String PASSWORD = "tahmina09090+++";
//
//    public static Connection getConnection() {
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//
//            Connection c = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//            System.out.println("✅ Connected: " + !c.isClosed());
//        } catch (ClassNotFoundException e) {
//            System.err.println("❌ JDBC driver not found: " + e.getMessage());
//            throw new RuntimeException(e);
//        } catch (SQLException e) {
//            System.err.println("❌ SQL connection error");
//            System.err.println("  URL: " + URL);
//            System.err.println("  SQLState: " + e.getSQLState());
//            System.err.println("  ErrorCode: " + e.getErrorCode());
//            System.err.println("  Message: " + e.getMessage());
//            throw new RuntimeException(e);
//        }
//    }
//}


