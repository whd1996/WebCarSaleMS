package servlet.comment;

import dao.CommentDao;
import dao.EmployeeDao;
import dao.OrderDao;
import entity.*;
import util.Factory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/commentList.do")
public class AllCommentListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("/login.jsp");
            return;
        }
        CommentDao cDao = (CommentDao) Factory.getInstance("CommentDao");
        ArrayList<CommentDto> list=cDao.selectAllComments();
        request.setAttribute("list",list);
        if(user.getRole()==0)
            request.setAttribute("path","customers/customers.jsp");
        else if (user.getRole()==1)
            request.setAttribute("path","salesmen/salesmen.jsp");
        else if (user.getRole()==2)
            request.setAttribute("path","admin/admin.jsp");

        request.getRequestDispatcher("page/customers/commentList.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
