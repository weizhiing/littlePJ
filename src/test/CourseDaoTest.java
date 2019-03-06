package test;

import Bean.CourseBean;
import Bean.UrlBean;
import Dao.CourseDao;
import Dao.UrlDao;
import Dao.UserDao;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by your dad on 2018/7/28.
 */
public class CourseDaoTest {
    @Test
    public void modifyCourse() throws Exception {
        CourseDao courseDao =new CourseDao();
        System.out.println(courseDao.modifyCourse(6,"good",null));
        System.out.println(courseDao.modifyCourse(5,"good",null));
        System.out.println(courseDao.modifyCourse(10,"good","asd"));
    }

    @Test
    public void searchCourse() throws Exception {
        CourseDao courseDao =new CourseDao();
      /*  System.out.println(courseDao.searchCourse(1).getCourse());
        System.out.println(courseDao.searchCourse(1).getDescription());
        System.out.println(courseDao.searchCourse(1).getTeacher().getUsername());
        System.out.println(courseDao.searchCourse(1).getTeacher().getEmail());

        System.out.println(courseDao.searchCourse(1).getImage().getUrl());*/
        ArrayList<CourseBean> a =  courseDao.searchHotCourse();
        System.out.println(a.size());
        System.out.println(a.get(0).getCourse()+"  "+ a.get(0).getDescription());
        System.out.println(a.get(1).getCourse()+"  "+ a.get(1).getDescription());
        System.out.println(a.get(2).getCourse()+"  "+ a.get(2).getDescription());
    }

    @Test
    public void searchOpenCourse() throws Exception {
        CourseDao courseDao =new CourseDao();
        System.out.println(courseDao.searchOpenCourse("aa").size());
        CourseBean course = courseDao.searchOpenCourse("aa").get(1);
        System.out.println(course.getCourse()+ "   "  +course.getDescription());
         course = courseDao.searchOpenCourse("aa").get(0);
        System.out.println(course.getCourse()+ "   "  +course.getDescription());
    }

    @Test
    public void deleteCourse() throws Exception {
        CourseDao courseDao =new CourseDao();
        System.out.println(courseDao.deleteCourse(11));
        System.out.println(courseDao.deleteCourse(5));
    }

    @Test
    public void addCourse() throws Exception {
       CourseDao courseDao =new CourseDao();
        UserDao userDao = new UserDao();
        UrlDao urlDao =new UrlDao();
      /*  CourseBean course = new CourseBean("english","ss",urlDao.searchUrl(2),userDao.searchUser(2));
       int i = courseDao.idCourse(course.getCourse(),course.getTeacher().getUsername());
        System.out.print(i);*/


        System.out.println(courseDao.addCourse(new CourseBean("chinese","ss",new UrlBean("../../桌面/卓越软件/PJ/web/WEB-INF/upload\\a5145249-5f58-45b7-9b0e-9ffe683a4e98_013267145X.jpg"),userDao.searchUser(5))));
   //     System.out.println(courseDao.addCourse(new CourseBean("chinese","aa",urlDao.searchUrl(1),userDao.searchUser(3))));
      //  System.out.println(courseDao.addCourse(new CourseBean("english","ss",urlDao.searchUrl(2),userDao.searchUser(2))));
    }

    @Test
    public void idCourse() throws Exception {
        CourseDao courseDao =new CourseDao();
      //  System.out.println(courseDao.idCourse("math","nsi4"));
        ArrayList<Integer> a = courseDao.idCourseStr("a",3);
        System.out.println(a.size());
        System.out.println(a.get(0));
        System.out.println(a.get(1));


    }
    @Test
    public void search() throws Exception {
        CourseDao courseDao =new CourseDao();

      /*  ArrayList<CourseBean> a = courseDao.searchNewCourseAll("","math","");
        System.out.println(a.size());
        System.out.println(a.get(0).getCourse()+"  "+a.get(0).getDescription());
        System.out.println(a.get(1).getCourse()+"  "+a.get(1).getDescription());*/
        ArrayList<CourseBean> b = courseDao.searchHotCourseAll("","","");
        System.out.println(b.size());
        System.out.println(b.get(0).getCourse()+" "+b.get(0).getDescription());
        System.out.println(b.get(4).getCourse()+" "+b.get(4).getDescription());



    }
}