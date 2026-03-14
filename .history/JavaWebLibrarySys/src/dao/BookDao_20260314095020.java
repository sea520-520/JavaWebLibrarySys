package dao;

import JDBC.GetCorn;
import entity.Book;
import entity.Borrow;

import java.sql.*;
import java.util.ArrayList;

public class BookDao {
    public ArrayList<Book> getBook() {
        ArrayList<Book> List = new ArrayList<>();
        Connection conn = null;
        // 初始化st，向数据库发送查询或者更新语句，并返回查询结果
        Statement st = null;
        // 初始化rs，遍历结果表，下表1开始
        ResultSet rs = null;

        PreparedStatement pst = null;

        String select = "select * from book";

        try {
            conn = new GetCorn().getConn();
            st = conn.createStatement();
            rs = st.executeQuery(select);
            while (rs.next()) {
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

    public ArrayList<Book> SearchBook(Book b) {
        ArrayList<Book> List = new ArrayList<>();
        Connection conn = null;
        // 初始化st，向数据库发送查询或者更新语句，并返回查询结果
        Statement st = null;
        // 初始化rs，遍历结果表，下表1开始
        ResultSet rs = null;

        PreparedStatement pst = null;

        String select = "select * from book where name = ?";

        try {
            conn = new GetCorn().getConn();
            pst = (PreparedStatement) conn.prepareStatement(select);
            pst.setString(1, b.getName());
            rs = pst.executeQuery();
            while (rs.next()) {
                Book book = new Book();
                book.setIbsn(rs.getString("IBSN"));
                book.setName(rs.getString("name"));
                book.setNum(rs.getInt("num"));
                book.setPlace(rs.getString("place"));
                book.setFlag(rs.getInt("flag"));
                List.add(book);
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

    public ArrayList<Borrow> ifborrowbook(Borrow b) {
        ArrayList<Borrow> List = new ArrayList<>();
        Connection conn = null;
        // 初始化st，向数据库发送查询或者更新语句，并返回查询结果
        Statement st = null;
        // 初始化rs，遍历结果表，下表1开始
        ResultSet rs = null;

        PreparedStatement pst = null;

        String select = "select * from borrow where bookname = '" + b.getBookname() + "'";

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

    public boolean changebook(Book b) {
        Connection conn = null;
        // 初始化st，向数据库发送查询或者更新语句，并返回查询结果
        Statement st = null;
        // 初始化rs，遍历结果表，下表1开始
        ResultSet rs = null;

        PreparedStatement pst = null;

        String select = "update book set IBSN = '" + b.getIbsn() + "', name = '" + b.getName() + "', num = "
                + b.getNum() + ", place = '" + b.getPlace() + "', dataime = '" + b.getDataime() + "', flag = "
                + b.getFlag() + " where IBSN = '" + b.getIbsn() + "'";
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

    public boolean deletebook(Book b) {
        Connection conn = null;
        // 初始化st，向数据库发送查询或者更新语句，并返回查询结果
        Statement st = null;
        // 初始化rs，遍历结果表，下表1开始
        ResultSet rs = null;

        PreparedStatement pst = null;

        String select = "delete from book where name = '" + b.getName() + "'";

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

    public boolean insertbook(Book b) {
        Connection conn = null;
        // 初始化st，向数据库发送查询或者更新语句，并返回查询结果
        Statement st = null;
        // 初始化rs，遍历结果表，下表1开始
        ResultSet rs = null;

        PreparedStatement pst = null;

        String select = "insert into book() values('" + b.getIbsn() + "', '" + b.getName() + "', " + b.getNum() + ", '"
                + b.getPlace() + "', '" + b.getDataime() + "', " + b.getFlag() + ")";

        /*
         * "insert into book()
         * values('"+b.getIbsn()+"', '"+b.getName()+"', "+b.get()+", '"+b.get()+"',
         * '"+b.get()+"', "+b.get())
         */

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

    public boolean lessnum(String name, int num) {
        Connection conn = null;
        // 初始化st，向数据库发送查询或者更新语句，并返回查询结果
        Statement st = null;
        // 初始化rs，遍历结果表，下表1开始
        ResultSet rs = null;

        PreparedStatement pst = null;
        String select = "update book set num = " + num + " where name = '" + name + "'";
        try {
            conn = new GetCorn().getConn();
            st = conn.createStatement();
            int n = st.executeUpdate(select);
            if (n > 0) {
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

    public boolean addsnum(String name, int num) {
        Connection conn = null;
        // 初始化st，向数据库发送查询或者更新语句，并返回查询结果
        Statement st = null;
        // 初始化rs，遍历结果表，下表1开始
        ResultSet rs = null;

        PreparedStatement pst = null;
        String select = "update book set num = " + num + " where name = '" + name + "'";
        try {
            conn = new GetCorn().getConn();
            st = conn.createStatement();
            int n = st.executeUpdate(select);
            if (n > 0) {
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
        BookDao bdao = new BookDao();
        ArrayList<Book> List = new ArrayList<>();
        // Book book = new Book();
        // List = bdao.getBook();
        // if(List!=null && List.size() > 0) {
        // for (int i = 0; i < List.size(); i++) {
        // Book b = List.get(i);
        // System.out.println(b.getName());
        // }
        // }
        // Book book = new Book();
        //// book.setName("计算机导论");
        //// boolean f = bdao.deletebook(book);
        //// if (f) {
        //// System.out.println("success");
        //// }
        //// book.setIbsn("4");
        // book.setName("555");
        //// book.setNum(5);
        //// book.setPlace("总馆");
        //// book.setDataime("20230503");
        //// book.setFlag(1);
        // List = bdao.SearchBook(book);
        // if (List!=null &&List.size()>0){
        // System.out.println("true");
        // }
        boolean b = bdao.addsnum("计算机网络", 10);
        System.out.println(b);
    }
}
