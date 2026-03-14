package UserAction;

import dao.BookDao;
import entity.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/SearchAction")
public class SerachAction extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        String name = req.getParameter("bookname");
        Book book = new Book();
        book.setName(name);
        BookDao bdao = new BookDao();
        ArrayList<Book> List = new ArrayList<>();
        List = bdao.SearchBook(book);
        if (List != null && List.size() > 0) {
            HttpSession session = req.getSession();
            session.setAttribute("search", true);
            session.setAttribute("List", List);
            resp.sendRedirect("/Library/searchbook.jsp");
        } else {
            HttpSession session = req.getSession();
            session.setAttribute("search", false);
            resp.sendRedirect("/Library/user_search.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
