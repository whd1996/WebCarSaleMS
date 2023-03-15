package servlet.comment;

import dao.EmployeeDao;
import entity.StaffDto;
import entity.User;
import util.Factory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Date;

@WebServlet("/addComment.do")
public class AddCommentDo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        EmployeeDao eDao = (EmployeeDao) Factory.getInstance("EmployeeDAO");
        StaffDto emp = eDao.findEmpByUId(user.getId());
        String oId = request.getParameter("id");
        request.setAttribute("name",emp.getName());
        request.setAttribute("oId",oId);
        request.setAttribute("commentDate",new Date().toString());
        request.getRequestDispatcher("page/customers/addComment.jsp").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
