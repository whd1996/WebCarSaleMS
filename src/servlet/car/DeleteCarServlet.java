package servlet.car;

import dao.CarDao;
import dao.EmployeeDao;
import dao.UserDao;
import entity.StaffDto;
import entity.User;
import util.Factory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/deleteCar.do")
public class DeleteCarServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("/login.jsp");
            return;
        }
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            CarDao carDao = (CarDao) Factory.getInstance("CarDao");
            carDao.DeleteCarById(id);
            if(user.getRole()==2){
                response.sendRedirect("carList.do");
            } else if (user.getRole()==1) {
                response.sendRedirect("salesmenCarMs.do");
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
