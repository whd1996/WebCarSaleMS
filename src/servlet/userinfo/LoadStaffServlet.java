package servlet.userinfo;

import dao.EmployeeDao;
import entity.StaffDto;
import util.Factory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/loadStaff.do")
public class LoadStaffServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            EmployeeDao dao = (EmployeeDao) Factory.getInstance("EmployeeDAO");
            StaffDto e = dao.findEmpById(id);
            request.setAttribute("e", e);
            request.getRequestDispatcher("page/admin/updateEmp.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
