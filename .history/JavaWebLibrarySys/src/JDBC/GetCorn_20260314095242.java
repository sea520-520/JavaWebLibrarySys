package JDBC;

import java.io.PrintWriter;
import java.sql.*;

public class GetCorn {
    Connection conn = null;

    public Connection getConn() throws ClassNotFoundException {
        String drive = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/jdbc?serverTimezone=GMT%2B8";
        String user = "root";
        String password = "123456";
        Class.forName(drive);
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("数据库连接失败！");
        }
        return conn;
    }

    public static void close(Connection conn, Statement st, ResultSet rs) {
        try {
            if (conn != null)
                conn.close();
            if (st != null)
                st.close();
            if (rs != null)
                rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
