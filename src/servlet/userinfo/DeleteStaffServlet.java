package servlet.userinfo;

import dao.EmployeeDao;
import dao.UserDao;
import entity.StaffDto;
import util.Factory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/deleteStaff.do")
public class DeleteStaffServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            UserDao userDao = (UserDao) Factory.getInstance("UserDao");
            EmployeeDao dao = (EmployeeDao) Factory.getInstance("EmployeeDAO");
            StaffDto emp = dao.findEmpById(id);
            userDao.deleteUserByID(emp.getUid());
            response.sendRedirect("staffList.do");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
