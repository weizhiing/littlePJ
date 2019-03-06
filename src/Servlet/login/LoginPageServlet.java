package Servlet.login;

import Bean.UserBean;
import Dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by your dad on 2018/7/25.
 */
@WebServlet(name = "LoginPageServlet",value = "/LoginPageServlet")
public class LoginPageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user =request.getParameter("username");
        String password = request.getParameter("password");
        String newPassword = encodePwd(password);
        String error = "";
        UserDao userDao = new UserDao();
        UserBean userBean = userDao.searchUser(userDao.idUser(user));
        if (userDao.idUser(user) == 0) {
            error = "username is incorrect";
        } else if (!userBean.getPassword().equals(newPassword)) {
            error = "password is incorrect";
        }
        if (!error.equals("")) {
            request.setAttribute("error", error);
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }else {
            HttpSession session = request.getSession();
            session.setAttribute("user",userBean);
            response.sendRedirect("/HomePageServlet");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          doPost(request,response);
    }
    public  String encodePwd(String pwd){
        String encodeString="^&*^&687GIKUGUGBjhkjbhjk";
        String  reString="";
        if(pwd==null){
            pwd="";
        }
        for(int i=0;i<pwd.length();i++){
            reString=reString+(char)(pwd.charAt(i)^encodeString.charAt(i));

        }
        return reString;

    }
}
