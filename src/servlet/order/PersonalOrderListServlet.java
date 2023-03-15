package servlet.order;

import dao.EmployeeDao;
import dao.OrderDao;
import entity.OrderDto;
import entity.StaffDto;
import entity.User;
import util.Factory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/personalOrderList.do")
public class PersonalOrderListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("/login.jsp");
        }
        OrderDao orderDao = (OrderDao) Factory.getInstance("OrderDao");
        EmployeeDao eDao = (EmployeeDao) Factory.getInstance("EmployeeDAO");
        StaffDto emp = eDao.findEmpByUId(user.getId());
        ArrayList<OrderDto> list= orderDao.selectCustomerAllOrders(emp.getId());
        for (OrderDto o : list){
            String salesmenName =eDao.selectStaffNameByCarId(o.getcId());
            o.setSalesmenName(salesmenName);
        }
        request.setAttribute("path","customers/customers.jsp");
        request.setAttribute("list",list);
        request.getRequestDispatcher("page/customers/orderList.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
