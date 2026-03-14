package UserAction;

import dao.BookDao;
import dao.IODao;
import dao.UserDao;
import entity.Book;
import entity.Borrow;
import entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@WebServlet("/LendAction")
public class LendAction extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        LocalDateTime data = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String data_temp = data.format(formatter);
        String name = req.getParameter("bookname");
        String username = (String) req.getSession().getAttribute("username");
        Book b = new Book();
        b.setName(name);
        BookDao bdao = new BookDao();
        ArrayList<Book> List = new ArrayList<>();
        List = bdao.SearchBook(b);
        User u = new User();
        u.setName(username);
        if (List!= null && List.size()>0){
            Book temp = new Book();
            temp = List.get(0);
            if (temp.getFlag() == 1 && temp.getNum()>0){
                UserDao udao = new UserDao();
                String relname = udao.getrelname(u);
                int temp_sum = temp.getNum();
                temp_sum = temp_sum - 1;
                Borrow book = new Borrow();
                book.setUsername(username);
                book.setRelname(relname);
                book.setBookname(name);
                book.setDataime(data_temp);
                book.setFlag(0);
                IODao ioDao = new IODao();
                boolean f = ioDao.lendbook(book);
                if (f){
                    boolean ff = bdao.lessnum(temp.getName(), temp_sum);
                    HttpSession session = req.getSession();
                    session.setAttribute("lend",true);
                    resp.sendRedirect("/Library/lendbook.jsp");
                }else {
                    HttpSession session = req.getSession();
                    session.setAttribute("lend",false);
                    resp.sendRedirect("/Library/lendbook.jsp");
                }
            }else {
                HttpSession session = req.getSession();
                session.setAttribute("lend",false);
                resp.sendRedirect("/Library/lendbook.jsp");
            }
        }else {
            HttpSession session = req.getSession();
            session.setAttribute("lend",false);
            resp.sendRedirect("/Library/lendbook.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
