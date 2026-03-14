package dao;

import JDBC.GetCorn;
import entity.Book;
import entity.Borrow;
import entity.User;

import java.sql.*;
import java.util.ArrayList;

public class UserDao {
    public String login(User u) {

        Connection conn = null;
        // 初始化st，向数据库发送查询或者更新语句，并返回查询结果
        Statement st = null;
        // 初始化rs，遍历结果表，下表1开始
        ResultSet rs = null;

        PreparedStatement pst = null;

        // 查询语句
        String select = "select * from user where username = ?";

        try {
            if (u.getName().equals("admin") && u.getPassword().equals("123456")) {
                return "admin";
            }
            conn = new GetCorn().getConn();
            pst = (PreparedStatement) conn.prepareStatement(select);
            pst.setString(1, u.getName());
            rs = pst.executeQuery();
            if (rs.next()) {
                if (u.getPassword().equals(rs.getString("password"))) {
                    return "user";
                } else {
                    return "error";
                }
            } else {
                return "error";
            }
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
        return null;
    }

    public ArrayList<User> getuserall() {
        ArrayList<User> List = new ArrayList<>();
        Connection conn = null;
        // 初始化st，向数据库发送查询或者更新语句，并返回查询结果
        Statement st = null;
        // 初始化rs，遍历结果表，下表1开始
        ResultSet rs = null;

        PreparedStatement pst = null;

        // 查询语句
        String select = "select * from user";

        try {
            conn = new GetCorn().getConn();
            st = conn.createStatement();
            rs = st.executeQuery(select);
            while (rs.next()) {
                User u = new User();
                u.setName(rs.getString("username"));
                u.setRelname(rs.getString("relname"));
                List.add(u);
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

    public boolean del_user(String name) {
        Connection conn = null;
        // 初始化st，向数据库发送查询或者更新语句，并返回查询结果
        Statement st = null;
        // 初始化rs，遍历结果表，下表1开始
        ResultSet rs = null;

        PreparedStatement pst = null;

        String select = "delete from user where username = '" + name + "'";
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

    public ArrayList<Borrow> getbook(User u) {
        ArrayList<Borrow> List = new ArrayList<>();
        Connection conn = null;
        // 初始化st，向数据库发送查询或者更新语句，并返回查询结果
        Statement st = null;
        // 初始化rs，遍历结果表，下表1开始
        ResultSet rs = null;

        PreparedStatement pst = null;

        // 查询语句
        String select = "select * from borrow where username = ?";

        try {
            conn = new GetCorn().getConn();
            pst = (PreparedStatement) conn.prepareStatement(select);
            pst.setString(1, u.getName());
            rs = pst.executeQuery();
            while (rs.next()) {
                Borrow b = new Borrow();
                b.setUsername(rs.getString("username"));
                b.setBookname(rs.getString("bookname"));
                b.setDataime(rs.getString("dataime"));
                b.setFlag(rs.getInt("flag"));
                List.add(b);
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

    public boolean ifbook(Borrow b) {
        ArrayList<Borrow> List = new ArrayList<>();
        Connection conn = null;
        // 初始化st，向数据库发送查询或者更新语句，并返回查询结果
        Statement st = null;
        // 初始化rs，遍历结果表，下表1开始
        ResultSet rs = null;

        PreparedStatement pst = null;

        // 查询语句
        String select = "select * from borrow where username = ? and bookname = ?";

        try {
            conn = new GetCorn().getConn();
            pst = (PreparedStatement) conn.prepareStatement(select);
            pst.setString(1, b.getUsername());
            pst.setString(2, b.getBookname());
            rs = pst.executeQuery();
            while (rs.next()) {
                int flag = rs.getInt("flag");
                if (flag == 0) {
                    return true;
                }
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

    public boolean adduser(User u) {
        Connection conn = null;
        // 初始化st，向数据库发送查询或者更新语句，并返回查询结果
        Statement st = null;
        // 初始化rs，遍历结果表，下表1开始
        ResultSet rs = null;

        PreparedStatement pst = null;

        String select = "insert into user() values('" + u.getName() + "', '" + u.getPassword() + "', '" + u.getRelname()
                + "')";

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

    public String getrelname(User u) {
        Connection conn = null;
        // 初始化st，向数据库发送查询或者更新语句，并返回查询结果
        Statement st = null;
        // 初始化rs，遍历结果表，下表1开始
        ResultSet rs = null;

        PreparedStatement pst = null;

        String select = "select relname from user where username = '" + u.getName() + "'";

        try {
            conn = new GetCorn().getConn();
            st = conn.createStatement();
            rs = st.executeQuery(select);
            while (rs.next()) {
                String relname = rs.getString("relname");
                return relname;
            }
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
        return null;
    }

    public boolean changepwd(User user, String pwd) {
        Connection conn = null;
        // 初始化st，向数据库发送查询或者更新语句，并返回查询结果
        Statement st = null;
        // 初始化rs，遍历结果表，下表1开始
        ResultSet rs = null;

        PreparedStatement pst = null;
        String select = "update user set password = '" + pwd + "' where username = '" + user.getName() + "'";
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

    public static void main(String[] args) {
        UserDao udao = new UserDao();
        User user = new User();
        user.setName("xiaowang");
        user.setPassword("123");
        boolean b = udao.adduser(user);
        System.out.println(b);
    }
}
