package JDBC;

import java.sql.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ConnectionPool {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/jdbc?serverTimezone=GMT%2B8";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    private static final int INITIAL_SIZE = 5;
    private static final int MAX_SIZE = 20;
    private static final int TIMEOUT = 30000;

    private static ConcurrentLinkedQueue<Connection> pool = new ConcurrentLinkedQueue<>();
    private static volatile int currentSize = 0;
    private static final Object lock = new Object();

    static {
        try {
            Class.forName(DRIVER);
            for (int i = 0; i < INITIAL_SIZE; i++) {
                Connection conn = createConnection();
                if (conn != null) {
                    pool.offer(conn);
                    currentSize++;
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("数据库驱动加载失败", e);
        }
    }

    private static Connection createConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Connection getConnection() throws SQLException {
        Connection conn = pool.poll();
        if (conn != null) {
            if (isValid(conn)) {
                return conn;
            } else {
                currentSize--;
                return getConnection();
            }
        }

        synchronized (lock) {
            if (currentSize < MAX_SIZE) {
                conn = createConnection();
                if (conn != null) {
                    currentSize++;
                    return conn;
                }
            }
        }

        long startTime = System.currentTimeMillis();
        while (conn == null) {
            if (System.currentTimeMillis() - startTime > TIMEOUT) {
                throw new SQLException("获取数据库连接超时");
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            conn = pool.poll();
        }

        if (!isValid(conn)) {
            currentSize--;
            return getConnection();
        }

        return conn;
    }

    private static boolean isValid(Connection conn) {
        try {
            return conn != null && !conn.isClosed() && conn.isValid(5);
        } catch (SQLException e) {
            return false;
        }
    }

    public static void releaseConnection(Connection conn) {
        if (conn != null) {
            try {
                if (!conn.isClosed()) {
                    if (pool.size() < INITIAL_SIZE) {
                        pool.offer(conn);
                    } else {
                        conn.close();
                        synchronized (lock) {
                            currentSize--;
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(Connection conn, Statement st, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (st != null) {
                st.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        releaseConnection(conn);
    }

    public static void close(Connection conn, PreparedStatement pst, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        releaseConnection(conn);
    }

    public static void shutdown() {
        for (Connection conn : pool) {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        pool.clear();
        currentSize = 0;
    }
}
