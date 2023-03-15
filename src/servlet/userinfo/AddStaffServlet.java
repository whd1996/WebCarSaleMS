package servlet.userinfo;

import dao.EmployeeDao;
import dao.UserDao;
import entity.Staff;
import entity.User;
import util.Factory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AddStaffServlet", value = "/AddStaffServlet")
public class AddStaffServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String gender = request.getParameter("gender");
        int age = Integer.parseInt(request.getParameter("age"));
        String contactNumber = request.getParameter("contactNumber");
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        String icNumber = request.getParameter("icNumber");
        String userRole = request.getParameter("userRole");
        try {
            UserDao userDao = (UserDao) Factory.getInstance("UserDao");
            User user = new User();
            user.setUsername(name);
            user.setRole(Integer.valueOf(userRole));
            user.setPassword("123456");
            userDao.addUser(user);
            User userByUsername = userDao.findUserByUsername(name);
            EmployeeDao dao = (EmployeeDao) Factory.getInstance("EmployeeDAO");
            Staff e = new Staff();
            e.setName(name);
            e.setGender(gender);
            e.setAge(age);
            e.setEmail(email);
            e.setAddress(address);
            e.setIcNumber(icNumber);
            e.setContactNumber(contactNumber);
            e.setUid(userByUsername.getId());
            dao.insertEmp(e);
            response.sendRedirect("staffList.do");
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException(e);
        }

    }
}
