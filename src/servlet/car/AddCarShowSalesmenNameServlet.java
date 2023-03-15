package servlet.car;

import dao.CarDao;
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

@WebServlet("/addCar.do")
public class AddCarShowSalesmenNameServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Object obj = session.getAttribute("user");
        if (obj == null) {
            response.sendRedirect("/login.jsp");
            return;
        }
        CarDao dao = (CarDao) Factory.getInstance("CarDao");
        ArrayList<String> salesmenNameList = dao.findAllCarsSalesmenName();
        System.out.println(salesmenNameList);
        request.setAttribute("salesmenNameList", salesmenNameList);
        RequestDispatcher rd = request.getRequestDispatcher("page/admin/addCar.jsp");
        rd.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
