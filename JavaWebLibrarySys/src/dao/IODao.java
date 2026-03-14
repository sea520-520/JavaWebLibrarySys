package dao;

import JDBC.GetCorn;
import entity.Borrow;

import java.sql.*;
import java.util.ArrayList;

public class IODao {
    public boolean lendbook(Borrow b){
        Connection conn = null;
        PreparedStatement pst = null;
        String select = "insert into borrow values(?, ?, ?, ?, ?)";
        try {
            conn = GetCorn.getInstance().getConn();
            pst = conn.prepareStatement(select);
            pst.setString(1, b.getUsername());
            pst.setString(2, b.getRelname());
            pst.setString(3, b.getBookname());
            pst.setString(4, b.getDataime());
            pst.setInt(5, b.getFlag());
            int num = pst.executeUpdate();
            return num > 0;
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("数据库操作失败！");
        }finally {
            GetCorn.close(conn, pst, null);
        }
        return false;
    }

    public boolean backbook(Borrow b){
        Connection conn = null;
        PreparedStatement pst = null;
        String select = "update borrow set flag = 1 where username = ? and bookname = ?";
        try {
            conn = GetCorn.getInstance().getConn();
            pst = conn.prepareStatement(select);
            pst.setString(1, b.getUsername());
            pst.setString(2, b.getBookname());
            int num = pst.executeUpdate();
            return num > 0;
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("数据库操作失败！");
        }finally {
            GetCorn.close(conn, pst, null);
        }
        return false;
    }

    public ArrayList<Borrow> getborrowall(){
        ArrayList<Borrow> List = new ArrayList<>();
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        String select = "select * from borrow";
        try {
            conn = GetCorn.getInstance().getConn();
            st = conn.createStatement();
            rs = st.executeQuery(select);
            while (rs.next()){
                Borrow borrow = new Borrow();
                borrow.setUsername(rs.getString("username"));
                borrow.setRelname(rs.getString("relname"));
                borrow.setBookname(rs.getString("bookname"));
                borrow.setDataime(rs.getString("dataime"));
                borrow.setFlag(rs.getInt("flag"));
                List.add(borrow);
            }
            return List;
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("数据库操作失败！");
        }finally {
            GetCorn.close(conn, st, rs);
        }
        return List;
    }
}
