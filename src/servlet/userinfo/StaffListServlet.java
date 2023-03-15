package servlet.userinfo;

import dao.EmployeeDao;
import entity.StaffDto;
import util.Factory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;
@WebServlet("/staffList.do")
public class StaffListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Object obj = session.getAttribute("user");
        if (obj == null) {
            response.sendRedirect("/login.jsp");
            return;
        }
        EmployeeDao dao = (EmployeeDao) Factory.getInstance("EmployeeDAO");
        try {
            int currPage = 1;
            if (request.getParameter("page") != null) {
                currPage = Integer.parseInt(request.getParameter("page"));
            }
            List<StaffDto> employees = dao.findALL(currPage);
            int count = dao.findCount();
            int pages;
            if (count % 5 == 0) {
                pages = count / 5;
            } else {
                pages = count / 5 + 1;
            }

            StringBuffer sb = new StringBuffer();
            for (int i = 1; i <= pages; ++i) {
                sb.append("<li style=\"display:inline-block\"><a href='staffList.do?page=" + i + "'>" + i + "  </a></li>");
            }

            request.setAttribute("count", count);
            request.setAttribute("bar", sb);
            //step1
            request.setAttribute("employees", employees);
            //step2
            RequestDispatcher rd = request.getRequestDispatcher("page/admin/userInfoList.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
