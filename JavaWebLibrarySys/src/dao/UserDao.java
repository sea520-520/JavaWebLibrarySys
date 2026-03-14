package dao;

import JDBC.GetCorn;
import entity.Book;
import entity.Borrow;
import entity.User;

import java.sql.*;
import java.util.ArrayList;

public class UserDao {
    public String login(User u){

        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        String select = "select * from user where username = ?";

        try {
            if (u.getName().equals("admin") && u.getPassword().equals("123456")){
                return "admin";
            }
            conn = GetCorn.getConnection();
            pst = conn.prepareStatement(select);
            pst.setString(1,u.getName());
            rs = pst.executeQuery();
            if (rs.next()){
                if (u.getPassword().equals(rs.getString("password"))){
                    return "user";
                }else{
                    return "error";
                }
            }else {
                return "error";
            }
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("数据库操作失败！");
        } finally {
            GetCorn.close(conn, pst, rs);
        }
        return null;
    }

    public ArrayList<User> getuserall(){
        ArrayList<User> List = new ArrayList<>();
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        String select = "select * from user";

        try {
            conn = GetCorn.getConnection();
            st = conn.createStatement();
            rs = st.executeQuery(select);
            while (rs.next()){
                User u = new User();
                u.setName(rs.getString("username"));
                u.setRelname(rs.getString("relname"));
                List.add(u);
            }
            return List;
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("数据库操作失败！");
        } finally {
            GetCorn.close(conn, st, rs);
        }
        return List;
    }

    public boolean del_user(String name){
        Connection conn = null;
        PreparedStatement pst = null;

        String select = "delete from user where username = ?";
        try {
            conn = GetCorn.getConnection();
            pst = conn.prepareStatement(select);
            pst.setString(1, name);
            int num = pst.executeUpdate();
            if (num > 0){
                return true;
            }
            return false;
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("数据库操作失败！");
        } finally {
            GetCorn.close(conn, pst, null);
        }
        return false;
    }

    public ArrayList<Borrow> getbook(User u){
        ArrayList<Borrow> List = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        String select = "select * from borrow where username = ?";

        try {
            conn = GetCorn.getConnection();
            pst = conn.prepareStatement(select);
            pst.setString(1,u.getName());
            rs = pst.executeQuery();
            while (rs.next()){
                Borrow b = new Borrow();
                b.setUsername(rs.getString("username"));
                b.setBookname(rs.getString("bookname"));
                b.setDataime(rs.getString("dataime"));
                b.setFlag(rs.getInt("flag"));
                List.add(b);
            }
            return List;
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("数据库操作失败！");
        } finally {
            GetCorn.close(conn, pst, rs);
        }
        return List;
    }

    public boolean ifbook(Borrow b){
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        String select = "select * from borrow where username = ? and bookname = ?";

        try {
            conn = GetCorn.getConnection();
            pst = conn.prepareStatement(select);
            pst.setString(1, b.getUsername());
            pst.setString(2, b.getBookname());
            rs = pst.executeQuery();
            while (rs.next()){
                int flag = rs.getInt("flag");
                if (flag == 0){
                    return true;
                }
            }
            return false;
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("数据库操作失败！");
        } finally {
            GetCorn.close(conn, pst, rs);
        }
        return false;
    }

    public boolean adduser(User u){
        Connection conn = null;
        PreparedStatement pst = null;

        String select = "insert into user(username, password, relname) values(?, ?, ?)";

        try {
            conn = GetCorn.getConnection();
            pst = conn.prepareStatement(select);
            pst.setString(1, u.getName());
            pst.setString(2, u.getPassword());
            pst.setString(3, u.getRelname());
            int num = pst.executeUpdate();
            if (num > 0){
                return true;
            }
            return false;
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("数据库操作失败！");
        } finally {
            GetCorn.close(conn, pst, null);
        }
        return false;
    }

    public String getrelname(User u){
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        String select = "select relname from user where username = ?";

        try {
            conn = GetCorn.getConnection();
            pst = conn.prepareStatement(select);
            pst.setString(1, u.getName());
            rs = pst.executeQuery();
            while (rs.next()){
                String relname = rs.getString("relname");
                return relname;
            }
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("数据库操作失败！");
        } finally {
            GetCorn.close(conn, pst, rs);
        }
        return null;
    }

    public boolean changepwd(User user, String pwd){
        Connection conn = null;
        PreparedStatement pst = null;
        String select = "update user set password = ? where username = ?";
        try {
            conn = GetCorn.getConnection();
            pst = conn.prepareStatement(select);
            pst.setString(1, pwd);
            pst.setString(2, user.getName());
            int num = pst.executeUpdate();
            if (num > 0){
                return true;
            }
            return false;
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("数据库操作失败！");
        } finally {
            GetCorn.close(conn, pst, null);
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
