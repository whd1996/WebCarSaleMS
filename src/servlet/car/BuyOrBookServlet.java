package servlet.car;

import dao.CarDao;
import dao.EmployeeDao;
import dao.OrderDao;
import entity.*;
import util.Factory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.DataInput;
import java.io.IOException;
import java.util.Date;

@WebServlet("/buyCar.do")
public class BuyOrBookServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("/login.jsp");
            return;
        }
        Integer carId = Integer.valueOf(request.getParameter("id"));
        String status = request.getParameter("status");
        CarDao carDao = (CarDao) Factory.getInstance("CarDao");
        Car car = carDao.findCarById(carId);
        OrderDao orderDao = (OrderDao) Factory.getInstance("OrderDao");
        EmployeeDao eDao = (EmployeeDao) Factory.getInstance("EmployeeDAO");
        StaffDto emp = eDao.findEmpByUId(user.getId());
        Order order = new Order();
        order.setcId(car.getId());
        order.setsId(emp.getId());
        order.setSaleDate(new Date().toString());
        orderDao.addOrder(order);
        //Update car table
        car.setStatus(status);
        carDao.updateCar(car);
        if(status.equals("paid"))
            request.getRequestDispatcher("/buySuccess.jsp").forward(request, response);
        else if (status.equals("booked")) {
            request.getRequestDispatcher("/bookSuccess.jsp").forward(request, response);

        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
