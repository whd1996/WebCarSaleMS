package servlet.car;

import dao.CarDao;
import dao.EmployeeDao;
import entity.CarDto;
import entity.StaffDto;
import util.Factory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/carList.do")
public class CarListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Object obj = session.getAttribute("user");
        if (obj == null) {
            response.sendRedirect("/login.jsp");
            return;
        }
        CarDao dao = (CarDao) Factory.getInstance("CarDao");
        try {
            int currPage = 1;
            if (request.getParameter("page") != null) {
                currPage = Integer.parseInt(request.getParameter("page"));
            }
            ArrayList<CarDto> cars = dao.findAllCars(currPage);
            int count = dao.findCount();
            int pages;
            if (count % 5 == 0) {
                pages = count / 5;
            } else {
                pages = count / 5 + 1;
            }
            StringBuffer sb = new StringBuffer();
            for (int i = 1; i <= pages; ++i) {
                sb.append("<li style=\"display:inline-block\"><a href='carList.do?page=" + i + "'>" + i + "  </a></li>");
            }
            request.setAttribute("count", count);
            request.setAttribute("bar", sb);
            //step1
            request.setAttribute("cars", cars);
            //step2
            RequestDispatcher rd = request.getRequestDispatcher("page/admin/carList.jsp");
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
