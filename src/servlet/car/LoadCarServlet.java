package servlet.car;

import dao.CarDao;
import entity.CarDto;
import entity.User;
import util.Factory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/loadCar.do")
public class LoadCarServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            CarDao dao = (CarDao) Factory.getInstance("CarDao");
            CarDto c = dao.findCarById(id);
            ArrayList<String> salesmenNameList = dao.findAllCarsSalesmenName();
            request.setAttribute("e", c);
            request.setAttribute("salesmenNameList", salesmenNameList);
            if(user.getRole()==2)
                request.getRequestDispatcher("page/admin/updateCar.jsp").forward(request, response);
            else if (user.getRole()==1) {
                request.getRequestDispatcher("page/salesmen/updateStaffManageCar.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
