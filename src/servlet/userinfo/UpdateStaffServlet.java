package servlet.userinfo;

import dao.EmployeeDao;
import dao.UserDao;
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

@WebServlet("/modifyStaff.do")
public class UpdateStaffServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User sessionUser = (User) request.getSession().getAttribute("user");
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String gender = request.getParameter("gender");
        int age = Integer.parseInt(request.getParameter("age"));
        String contactNumber = request.getParameter("contactNumber");
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        String icNumber = request.getParameter("icNumber");
        if (sessionUser != null) {

            try {
                UserDao userDao = (UserDao) Factory.getInstance("UserDao");
                EmployeeDao dao = (EmployeeDao) Factory.getInstance("EmployeeDAO");
                StaffDto empById = dao.findEmpById(id);
                User user = userDao.findUserByUserId(String.valueOf(empById.getUid()));
                String userRole = request.getParameter("userRole");
                if (userRole != null)
                    user.setRole(Integer.valueOf(userRole));
                userDao.updateUser(user);
                if (sessionUser.getRole() == 1 || sessionUser.getRole() == 0) {
                    user.setRole(sessionUser.getRole());
                }
                //update user
                //update staff
                Staff e = new Staff();
                e.setId(id);
                e.setName(name);
                e.setGender(gender);
                e.setAge(age);
                e.setEmail(email);
                e.setAddress(address);
                e.setIcNumber(icNumber);
                e.setContactNumber(contactNumber);
                dao.updateEmp(e);
                if (sessionUser.getRole() == 2)
                    response.sendRedirect("staffList.do");
                else if (sessionUser.getRole() == 1 || sessionUser.getRole() == 0) {
                    response.sendRedirect("personalInfo.do?uid="+sessionUser.getId());
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new ServletException(e);
            }
        }
    }
}
