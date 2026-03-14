package Action;

import dao.BookDao;
import entity.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet("/ChangeAction")
public class ChangeAction extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        LocalDateTime data_time = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String data_temp = data_time.format(formatter);
        Book book = new Book();
        String ibsn = req.getParameter("ibsn");
        String name = req.getParameter("bookname");
        int num = Integer.parseInt(req.getParameter("num"));
        String place = req.getParameter("place");
        String flag_temp = req.getParameter("flag");
        int flag = 0;
        if (flag_temp.equals("可借")) {
            flag = 1;
        }
        book.setIbsn(ibsn);
        book.setName(name);
        book.setNum(num);
        book.setPlace(place);
        book.setDataime(data_temp);
        book.setFlag(flag);
        BookDao bdao = new BookDao();
        boolean f = bdao.changebook(book);
        if (f) {
            HttpSession session = req.getSession();
            session.setAttribute("change", true);
            resp.sendRedirect("/Library/changebook.jsp");
        } else {
            HttpSession session = req.getSession();
            session.setAttribute("change", false);
            resp.sendRedirect("/Library/changebook.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
