package servlet.userinfo;

import dao.UserDao;
import entity.User;
import util.Factory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/toResetPwd.do")
public class ReSetPwdServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Object obj = session.getAttribute("user");
        if (obj == null) {
            response.sendRedirect("/login.jsp");
            return;
        }
        String username = request.getParameter("username");
        String password = request.getParameter("pwd");
        UserDao dao = (UserDao) Factory.getInstance("UserDao");
        try {
            User user = dao.findUserByUsername(username);
            if ("".equals(username.trim())) {
                request.setAttribute("username", "The username cannot be empty!");
                request.getRequestDispatcher("/resetPwd.jsp").forward(request, response);
            } else if (user != null) {
                user.setPassword(password);
                dao.updateUser(user);
                request.getRequestDispatcher("/pwdResetSuccess.jsp").forward(request, response);
            }else {
                request.setAttribute("result", "The user is not exists!");
                request.getRequestDispatcher("/resetPwd.jsp").forward(request, response);
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
