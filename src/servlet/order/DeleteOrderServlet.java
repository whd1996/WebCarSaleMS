package servlet.order;

import dao.CarDao;
import dao.OrderDao;
import entity.CarDto;
import entity.Order;
import entity.User;
import util.Factory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteOrder.do")
public class DeleteOrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        User user = (User) request.getSession().getAttribute("user");
        try {
            OrderDao orderDao = (OrderDao) Factory.getInstance("OrderDao");
            Order order =orderDao.findOrderById(id);
            CarDao carDao=(CarDao) Factory.getInstance("CarDao");
            CarDto car = carDao.findCarById(order.getcId());
            car.setStatus("available");
            carDao.updateCar(car);
            orderDao.DeleteOrderById(id);
            if(user.getRole()==0){
                response.sendRedirect("personalOrderList.do");
            }else if(user.getRole()==2){
                response.sendRedirect("OrderList.do");
            }else {
                response.sendRedirect("salesRecord.do");
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
