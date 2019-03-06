package Servlet.Course.DiscussRelation;

import Bean.CourseBean;
import Bean.UserBean;
import Dao.DiscussReply;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by your dad on 2018/7/26.
 */
@WebServlet(name = "DiscussReplyDeleteServlet",value = "/DiscussReplyDeleteServlet")
public class DiscussReplyDeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");

        String studentName = request.getParameter("studentName");
        String content = request.getParameter("content");
        HttpSession session =request.getSession();
        UserBean userBean = (UserBean) session.getAttribute("user");
        CourseBean courseBean =(CourseBean)session.getAttribute("course");

        DiscussReply discussReply =new DiscussReply();
        int count = discussReply.idDiscussReply(courseBean.getCourse(),courseBean.getTeacher().getUsername(),
                studentName,title,userBean.getUsername(),content);
        System.out.println(count+ courseBean.getCourse()+" "+courseBean.getTeacher().getUsername()+"  "+studentName+title+" "+userBean.getUsername()+" "+content);
        discussReply.deleteDiscussReply(count);
        response.sendRedirect("/DiscussPageServlet");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 doPost(request,response);
    }
}
