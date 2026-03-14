package dao;

import JDBC.GetCorn;
import entity.Book;
import entity.Borrow;

import java.sql.*;
import java.util.ArrayList;

public class BookDao {
    public ArrayList<Book> getBook(){
        ArrayList<Book> List = new ArrayList<>();
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        String select = "select * from book";
        try {
            conn = GetCorn.getInstance().getConn();
            st = conn.createStatement();
            rs = st.executeQuery(select);
            while (rs.next()){
                Book book = new Book();
                book.setIbsn(rs.getString("IBSN"));
                book.setName(rs.getString("name"));
                book.setNum(rs.getInt("num"));
                book.setPlace(rs.getString("place"));
                book.setDataime(rs.getString("dataime"));
                book.setFlag(rs.getInt("flag"));
                List.add(book);
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

    public ArrayList<Book> SearchBook(Book b){
        ArrayList<Book> List = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String select = "select * from book where name = ?";
        try {
            conn = GetCorn.getInstance().getConn();
            pst = conn.prepareStatement(select);
            pst.setString(1, b.getName());
            rs = pst.executeQuery();
            while (rs.next()){
                Book book = new Book();
                book.setIbsn(rs.getString("IBSN"));
                book.setName(rs.getString("name"));
                book.setNum(rs.getInt("num"));
                book.setPlace(rs.getString("place"));
                book.setFlag(rs.getInt("flag"));
                List.add(book);
            }
            return List;
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("数据库操作失败！");
        }finally {
            GetCorn.close(conn, pst, rs);
        }
        return List;
    }

    public ArrayList<Borrow> ifborrowbook(Borrow b){
        ArrayList<Borrow> List = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String select = "select * from borrow where bookname = ?";
        try {
            conn = GetCorn.getInstance().getConn();
            pst = conn.prepareStatement(select);
            pst.setString(1, b.getBookname());
            rs = pst.executeQuery();
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
            GetCorn.close(conn, pst, rs);
        }
        return List;
    }

    public boolean changebook(Book b){
        Connection conn = null;
        PreparedStatement pst = null;
        String select = "update book set name = ?, num = ?, place = ?, dataime = ?, flag = ? where IBSN = ?";
        try {
            conn = GetCorn.getInstance().getConn();
            pst = conn.prepareStatement(select);
            pst.setString(1, b.getName());
            pst.setInt(2, b.getNum());
            pst.setString(3, b.getPlace());
            pst.setString(4, b.getDataime());
            pst.setInt(5, b.getFlag());
            pst.setString(6, b.getIbsn());
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

    public boolean deletebook(Book b){
        Connection conn = null;
        PreparedStatement pst = null;
        String select = "delete from book where name = ?";
        try {
            conn = GetCorn.getInstance().getConn();
            pst = conn.prepareStatement(select);
            pst.setString(1, b.getName());
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

    public boolean insertbook(Book b){
        Connection conn = null;
        PreparedStatement pst = null;
        String select = "insert into book values(?, ?, ?, ?, ?, ?)";
        try {
            conn = GetCorn.getInstance().getConn();
            pst = conn.prepareStatement(select);
            pst.setString(1, b.getIbsn());
            pst.setString(2, b.getName());
            pst.setInt(3, b.getNum());
            pst.setString(4, b.getPlace());
            pst.setString(5, b.getDataime());
            pst.setInt(6, b.getFlag());
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

    public boolean lessnum(String name, int num){
        Connection conn = null;
        PreparedStatement pst = null;
        String select = "update book set num = ? where name = ?";
        try {
            conn = GetCorn.getInstance().getConn();
            pst = conn.prepareStatement(select);
            pst.setInt(1, num);
            pst.setString(2, name);
            int n = pst.executeUpdate();
            return n > 0;
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("数据库操作失败！");
        }finally {
            GetCorn.close(conn, pst, null);
        }
        return false;
    }

    public boolean addsnum(String name, int num){
        Connection conn = null;
        PreparedStatement pst = null;
        String select = "update book set num = ? where name = ?";
        try {
            conn = GetCorn.getInstance().getConn();
            pst = conn.prepareStatement(select);
            pst.setInt(1, num);
            pst.setString(2, name);
            int n = pst.executeUpdate();
            return n > 0;
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("数据库操作失败！");
        }finally {
            GetCorn.close(conn, pst, null);
        }
        return false;
    }

    public static void main(String[] args) {
        BookDao bdao = new BookDao();
        ArrayList<Book> List = new ArrayList<>();
        boolean b = bdao.addsnum("计算机网络",10);
        System.out.println(b);
    }
}
