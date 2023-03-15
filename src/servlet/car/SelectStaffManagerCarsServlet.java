package servlet.car;

import dao.CarDao;
import dao.EmployeeDao;
import dao.UserDao;
import entity.Car;
import entity.StaffDto;
import entity.User;
import util.Factory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/salesmenCarMs.do")
public class SelectStaffManagerCarsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if(user!=null){
            CarDao dao = (CarDao) Factory.getInstance("CarDao");
            int count = 0;
            try {
                count = dao.findCount();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            EmployeeDao eDao = (EmployeeDao) Factory.getInstance("EmployeeDAO");
            StaffDto dbStaff = eDao.findEmpByUId(user.getId());
            if(dbStaff!=null){
              ArrayList<Car> list= dao.findStaffAllCars(dbStaff.getId());
              if(!list.isEmpty())
              request.setAttribute("cars",list);
              request.setAttribute("count",count);
            }
            request.getRequestDispatcher("page/salesmen/showAndUpdateCar.jsp").forward(request, response);

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
