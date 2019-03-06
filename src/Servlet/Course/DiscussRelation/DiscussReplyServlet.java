package Servlet.Course.DiscussRelation;

import Bean.CourseBean;
import Bean.DiscussReplyBean;
import Bean.UserBean;
import Dao.DiscussDao;
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
@WebServlet(name = "DiscussReplyServlet",value = "/DiscussReplyServlet")
public class DiscussReplyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String title = request.getParameter("title");
        String studentName = request.getParameter("studentName");
        String content = request.getParameter("replyContent");
        String error  = "";
        HttpSession session =request.getSession();
        UserBean userBean = (UserBean) session.getAttribute("user");
        CourseBean courseBean =(CourseBean)session.getAttribute("course");
        DiscussDao discussDao =new DiscussDao();
        DiscussReply discussReply =new DiscussReply();

        int count = discussReply.idDiscussReply(courseBean.getCourse(),courseBean.getTeacher().getUsername(),
                studentName,title,userBean.getUsername(),content);
        if(count!=0){
            error = "content repeat";
            request.setAttribute("error",error);
            request.getRequestDispatcher("/DiscussPageServlet").forward(request,response);
        }
        discussReply.addDiscussReply(new DiscussReplyBean(discussDao.searchDiscuss(discussDao.idDiscuss(courseBean.getCourse(),
                courseBean.getTeacher().getUsername(),studentName,title)),userBean,content));
          response.sendRedirect("/DiscussPageServlet");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          doPost(request,response);
    }
}
