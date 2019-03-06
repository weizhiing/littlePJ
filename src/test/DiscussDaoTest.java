package test;

import Bean.CourseBean;
import Bean.DiscussBean;
import Bean.UserBean;
import Dao.CourseDao;
import Dao.DiscussDao;
import Dao.UserDao;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by your dad on 2018/7/29.
 */
public class DiscussDaoTest {
    @Test
    public void searchDiscuss() throws Exception {
        DiscussDao  discussDao =new DiscussDao();
        //System.out.print(discussDao.searchDiscuss(1).getContent());
        ArrayList<DiscussBean> a = discussDao.searchDiscuss("math","nsi4");
        System.out.println(a.size());
        System.out.println(a.get(0).getTitle()+"  "+a.get(0).getContent());
    }

    @Test
    public void deleteDiscuss() throws Exception {
        DiscussDao  discussDao =new DiscussDao();
        System.out.println(discussDao.deleteDiscuss(1));

    }

    @Test
    public void addDiscuss() throws Exception {
        DiscussDao  discussDao =new DiscussDao();

        CourseDao courseDao =new CourseDao();
        UserDao userDao =new UserDao();
        CourseBean courseBean = courseDao.searchCourse(9);
        CourseBean courseBean2= courseDao.searchCourse(6);
        UserBean userBean =userDao.searchUser(1);
        UserBean userBean2 =userDao.searchUser(2);
        System.out.println(discussDao.addDiscuss(new DiscussBean(courseBean,userBean,"你好","wohao")));
        System.out.println(discussDao.addDiscuss(new DiscussBean(courseBean2,userBean2,"你好","wohao")));


    }

    @Test
    public void idDiscuss() throws Exception {
        DiscussDao  discussDao =new DiscussDao();
        System.out.println(discussDao.idDiscuss("英语","Zhengyuxin1","Zhengyuxin1"," 这道题不会啊"));
    }

}