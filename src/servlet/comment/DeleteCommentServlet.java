package servlet.comment;

import dao.CarDao;
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
import java.io.IOException;

@WebServlet("/deleteComment.do")
public class DeleteCommentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user= (User) request.getSession().getAttribute("user");
        if (user == null) {
            response.sendRedirect("/login.jsp");
            return;
        }
        int id = Integer.parseInt(request.getParameter("id"));
        String commentName = request.getParameter("name");
        try {
            EmployeeDao eDao = (EmployeeDao) Factory.getInstance("EmployeeDAO");
            StaffDto emp = eDao.findEmpByUId(user.getId());
            CommentDao commentDao = (CommentDao) Factory.getInstance("CommentDao");
            if(user.getRole()==2){
                commentDao.deleteCommentById(id);
            }else{
                if(!emp.getName().equals(commentName)){
                    response.getWriter().print("You don't have permission to delete it");
                    return;
                }else{
                    commentDao.deleteCommentById(id);
                }
            }
            response.sendRedirect("commentList.do");

        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
