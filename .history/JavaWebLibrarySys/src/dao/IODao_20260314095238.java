package dao;

import JDBC.GetCorn;
import entity.Book;
import entity.Borrow;

import java.sql.*;
import java.util.ArrayList;

public class IODao {
    public boolean lendbook(Borrow b) {
        Connection conn = null;
        // 初始化st，向数据库发送查询或者更新语句，并返回查询结果
        Statement st = null;
        // 初始化rs，遍历结果表，下表1开始
        ResultSet rs = null;

        PreparedStatement pst = null;

        String select = "insert into borrow() values('" + b.getUsername() + "', '" + b.getRelname() + "', '"
                + b.getBookname() + "', '" + b.getDataime() + "', " + b.getFlag() + ")";
        try {
            conn = new GetCorn().getConn();
            st = conn.createStatement();
            int num = st.executeUpdate(select);
            if (num > 0) {
                return true;
            }
            return false;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("数据库驱动没有安装！");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("数据库连接失败！");
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    public boolean backbook(Borrow b) {
        Connection conn = null;
        // 初始化st，向数据库发送查询或者更新语句，并返回查询结果
        Statement st = null;
        // 初始化rs，遍历结果表，下表1开始
        ResultSet rs = null;

        PreparedStatement pst = null;

        String select = "update borrow set flag = 1 where username = '" + b.getUsername() + "' and bookname = '"
                + b.getBookname() + "'";
        try {
            conn = new GetCorn().getConn();
            st = conn.createStatement();
            int num = st.executeUpdate(select);
            if (num > 0) {
                return true;
            }
            return false;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("数据库驱动没有安装！");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("数据库连接失败！");
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    public ArrayList<Borrow> getborrowall() {
        ArrayList<Borrow> List = new ArrayList<>();

        Connection conn = null;
        // 初始化st，向数据库发送查询或者更新语句，并返回查询结果
        Statement st = null;
        // 初始化rs，遍历结果表，下表1开始
        ResultSet rs = null;

        PreparedStatement pst = null;

        String select = "select * from borrow";

        try {
            conn = new GetCorn().getConn();
            st = conn.createStatement();
            rs = st.executeQuery(select);
            while (rs.next()) {
                Borrow borrow = new Borrow();
                borrow.setUsername(rs.getString("username"));
                borrow.setRelname(rs.getString("relname"));
                borrow.setBookname(rs.getString("bookname"));
                borrow.setDataime(rs.getString("dataime"));
                borrow.setFlag(rs.getInt("flag"));
                List.add(borrow);
            }
            return List;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("数据库驱动没有安装！");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("数据库连接失败！");
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return List;
    }
}
