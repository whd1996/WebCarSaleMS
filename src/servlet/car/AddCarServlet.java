package servlet.car;

import dao.CarDao;
import dao.EmployeeDao;
import dao.UserDao;
import entity.Car;
import entity.Staff;
import entity.StaffDto;
import entity.User;
import util.Factory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/AddCarServlet")
public class AddCarServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String carName = request.getParameter("carName");
        Integer ageLimit = Integer.valueOf(request.getParameter("ageLimit"));
        String type = request.getParameter("type");
        String quality = request.getParameter("quality");
        Double price = Double.valueOf(request.getParameter("price"));
        String status = request.getParameter("status");
        String salesmenName = request.getParameter("salesmenName");
        try {
            CarDao carDao = (CarDao) Factory.getInstance("CarDao");
            EmployeeDao eDao = (EmployeeDao) Factory.getInstance("EmployeeDAO");
            StaffDto emp=eDao.findEmpByName(salesmenName);
            Car car = new Car();
            car.setCarName(carName);
            car.setType(type);
            car.setQuality(quality);
            car.setPrice(price);
            car.setStatus(status);
            car.setAgeLimit(ageLimit);
            if(emp!=null){
                car.setsId(emp.getId());
            }
            carDao.addCar(car);
            response.sendRedirect("carList.do");
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException(e);
        }

    }
}
