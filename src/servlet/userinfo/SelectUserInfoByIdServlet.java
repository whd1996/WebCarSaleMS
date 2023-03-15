package servlet.userinfo;

import dao.EmployeeDao;
import entity.StaffDto;
import util.Factory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/personalInfo.do")
public class SelectUserInfoByIdServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int uid = Integer.parseInt(request.getParameter("uid"));
        try {
            EmployeeDao dao = (EmployeeDao) Factory.getInstance("EmployeeDAO");
            StaffDto e = dao.findEmpByUId(uid);
            request.setAttribute("e", e);
            request.getRequestDispatcher("page/salesmen/showAndUpdateInfo.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException(e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
