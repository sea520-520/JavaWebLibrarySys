package JDBC;

import java.sql.*;

public class GetCorn {

    @Deprecated
    public Connection getConn() throws ClassNotFoundException, SQLException {
        return ConnectionPool.getConnection();
    }

    public static Connection getConnection() throws SQLException {
        return ConnectionPool.getConnection();
    }

    public static void close(Connection conn, Statement st, ResultSet rs) {
        ConnectionPool.close(conn, st, rs);
    }

    public static void close(Connection conn, PreparedStatement pst, ResultSet rs) {
        ConnectionPool.close(conn, pst, rs);
    }

    public static void releaseConnection(Connection conn) {
        ConnectionPool.releaseConnection(conn);
    }
}
