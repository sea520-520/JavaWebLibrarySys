package JDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GetCorn {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/jdbc?serverTimezone=GMT%2B8";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";
    
    private static final int MAX_POOL_SIZE = 10;
    private static List<Connection> connectionPool = new ArrayList<>();
    private static List<Connection> usedConnections = new ArrayList<>();
    
    private static GetCorn instance;
    
    static {
        try {
            Class.forName(DRIVER);
            for (int i = 0; i < MAX_POOL_SIZE / 2; i++) {
                connectionPool.add(createConnection());
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    
    private static Connection createConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    
    public static synchronized GetCorn getInstance() {
        if (instance == null) {
            instance = new GetCorn();
        }
        return instance;
    }
    
    public synchronized Connection getConn() throws SQLException {
        if (connectionPool.isEmpty()) {
            if (usedConnections.size() < MAX_POOL_SIZE) {
                connectionPool.add(createConnection());
            } else {
                throw new SQLException("数据库连接池已满");
            }
        }
        Connection conn = connectionPool.remove(connectionPool.size() - 1);
        usedConnections.add(conn);
        return conn;
    }
    
    public synchronized void releaseConn(Connection conn) {
        if (conn != null) {
            usedConnections.remove(conn);
            connectionPool.add(conn);
        }
    }
    
    public static void close(Connection conn, Statement st, ResultSet rs) {
        try {
            if (rs != null)
                rs.close();
            if (st != null)
                st.close();
            if (conn != null)
                GetCorn.getInstance().releaseConn(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
