package servlet;

import dao.EmployeeDao;
import dao.UserDao;
import entity.Staff;
import entity.User;
import util.Factory;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String uri = request.getRequestURI();

        String action = uri.substring(uri.lastIndexOf("/"), uri.lastIndexOf("."));
        if ("/login".equals(action)) {
            String number1 = request.getParameter("number");
            HttpSession session = request.getSession();
            String number2 = (String) session.getAttribute("number");
            if (!number1.equalsIgnoreCase(number2)) {
                request.setAttribute("checkcode_msg", "Verification code error");
                request.getRequestDispatcher("login.jsp").forward(request, response);
                return;
            }
            String username = request.getParameter("username");
            String password = request.getParameter("pwd");
            UserDao dao = (UserDao) Factory.getInstance("UserDao");
            try {
                User user = dao.findUserByUsername(username);
                if ("".equals(username.trim())) {
                    request.setAttribute("username", "The username cannot be empty!");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                } else if ("".equals(password.trim())) {
                    request.setAttribute("password", "The password cannot be empty");
                    request.getRequestDispatcher("/login.jsp").forward(request, response);
                } else if (user != null) {
                    User loginuser = dao.login(username, password);
                    if (loginuser != null) {
                        session.setAttribute("user", user);
                        Cookie c1 = new Cookie("userName", username);
                        Cookie c2 = new Cookie("passWord", password);
                        c1.setMaxAge(2592000);
                        c2.setMaxAge(2592000);
                        response.addCookie(c1);
                        response.addCookie(c2);
                        if (loginuser.getRole() == 2)
                            response.sendRedirect("page/admin/admin.jsp");
                        else if (loginuser.getRole() == 1)
                            response.sendRedirect("page/salesmen/salesmen.jsp");
                        else if (loginuser.getRole() == 0)
                            response.sendRedirect("page/customers/customers.jsp");
                    }else {
                        request.setAttribute("result", "The username or password is incorrect!");
                        request.getRequestDispatcher("/login.jsp").forward(request, response);
                    }
                }else {
                    request.setAttribute("result", "The user is not exists!");
                    request.getRequestDispatcher("/login.jsp").forward(request, response);
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new ServletException(e);
            }
        } else if ("/regist".equals(action)) {
            String username = request.getParameter("username");
            String password = request.getParameter("pwd");

            if ("".equals(username.trim())) {
                request.setAttribute("username", "The username cannot be empty!");
                request.getRequestDispatcher("/regist.jsp").forward(request, response);
            } else if ("".equals(password.trim())) {
                request.setAttribute("password", "The password cannot be empty!");
                request.getRequestDispatcher("/regist.jsp").forward(request, response);
            } else {
                UserDao dao = (UserDao) Factory.getInstance("UserDao");
                User user = dao.findUserByUsername(username);
                if (user != null) {
                    request.setAttribute("result", "User already exists!");
                    request.getRequestDispatcher("regist.jsp").forward(request, response);
                } else {
                    user = new User();
                    user.setUsername(username);
                    user.setPassword(password);
                    //Registration defaults to customer  user-role==2
                    user.setRole(0);
                    dao.addUser(user);
                    User userByUsername = dao.findUserByUsername(username);
                    EmployeeDao edao = (EmployeeDao) Factory.getInstance("EmployeeDAO");
                    Staff e = new Staff();
                    e.setName(username);
                    e.setUid(userByUsername.getId());
                    e.setAge(20);
                    try {
                        edao.insertEmp(e);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }

                }
                request.getRequestDispatcher("registSuccess.jsp").forward(request, response);
            }
        }
    }
}

