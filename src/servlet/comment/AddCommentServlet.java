package servlet.comment;

import dao.CommentDao;
import dao.EmployeeDao;
import entity.Comment;
import entity.StaffDto;
import entity.User;
import util.Factory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AddCommentServlet", value = "/AddCommentServlet")
public class AddCommentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String oId = request.getParameter("oId");
        String name = request.getParameter("name");
        String commentDate = request.getParameter("commentDate");
        String content = request.getParameter("content");
        EmployeeDao eDao = (EmployeeDao) Factory.getInstance("EmployeeDAO");
        StaffDto emp = eDao.findEmpByName(name);
        int sId=emp.getId();
        Comment comment = new Comment();
        comment.setoId(Integer.valueOf(oId));
        comment.setsId(sId);
        comment.setContent(content);
        comment.setCommentDate(commentDate);
        CommentDao cDao = (CommentDao) Factory.getInstance("CommentDao");
        cDao.addComment(comment);
        response.sendRedirect("commentList.do");
    }
}
