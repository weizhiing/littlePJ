package Servlet.register;

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
@WebServlet(name = "RegisterPageServlet",value = "/RegisterPageServlet")
public class RegisterPageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName =request.getParameter("username");
        String password =  request.getParameter("password");
        String email  = request.getParameter("email");
        String newPassword = encodePwd(password);
        String error="";
        UserDao userDao =new UserDao();
        if(userDao.idUser(userName)!=0){
            error = "username is already used";
            request.setAttribute("error",error);
            String name ="";
            request.getRequestDispatcher("/register.jsp").forward(request,response);
        }else {
            //数据库增加
            UserBean user =new UserBean(userName,newPassword,email);
            userDao.addUser(user);
            HttpSession session = request.getSession();
            session.setAttribute("user",user);
            response.sendRedirect("/HomePageServlet");
        }
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
