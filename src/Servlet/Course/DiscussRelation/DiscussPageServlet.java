package Servlet.Course.DiscussRelation;

import Bean.CourseBean;
import Bean.DiscussBean;
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
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by your dad on 2018/7/25.
 */
@WebServlet(name = "DiscussPageServlet",value = "/DiscussPageServlet")
public class DiscussPageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");
        if(role.equals("neither")){
            response.sendRedirect("/login.jsp");
        }
        UserBean userBean = (UserBean)session.getAttribute("user");
        HashMap<DiscussBean,ArrayList<DiscussReplyBean>> discussBoard = new HashMap<DiscussBean,ArrayList<DiscussReplyBean>>();
        CourseBean courseBean = (CourseBean)session.getAttribute("course");
        DiscussDao discussDao =new DiscussDao();
        DiscussReply discussReply =new DiscussReply();
        ArrayList<DiscussBean> discuss = discussDao.searchDiscuss(courseBean.getCourse(),courseBean.getTeacher().getUsername());
        for(DiscussBean discussBean: discuss ){
            if(discussBean.getStudent().getUsername().equals(userBean.getUsername())){
                discussBean.setBeAbleToDelete(true);
            }
            ArrayList<DiscussReplyBean> discussReplyAll = discussReply.searchDiscussReply(courseBean.getCourse(),
                    courseBean.getTeacher().getUsername(),
                    discussBean.getStudent().getUsername(),
                    discussBean.getTitle());
            for(DiscussReplyBean discussReplyBean :discussReplyAll){
                if(discussReplyBean.getStudent().getUsername().equals(userBean.getUsername())){
                    discussReplyBean.setBeAbleToDelete(true);
                }
            }
            discussBoard.put(discussBean,discussReplyAll);
        }
        request.setAttribute("discussBoard",discussBoard );
        request.getRequestDispatcher("/groupTopic.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          doPost(request,response);
    }
}
