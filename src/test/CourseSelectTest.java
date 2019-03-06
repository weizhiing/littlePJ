package test;

import Bean.CourseBean;
import Bean.CourseSelectBean;
import Bean.UserBean;
import Dao.CourseDao;
import Dao.CourseSelect;
import Dao.UserDao;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by your dad on 2018/7/28.
 */
public class CourseSelectTest {
    @Test
    public void searchCourseSelect() throws Exception {
        CourseSelect courseSelect = new CourseSelect();
        ArrayList<CourseBean> a = courseSelect.searchCourseSelect("aa");
        System.out.println(a.size());
        System.out.println(a.get(1).getCourse()+"  "+a.get(1).getDescription()+"  " + a.get(1).getTeacher().getUsername());
        System.out.println(a.get(0).getCourse()+"  "+a.get(0).getDescription()+"  " + a.get(0).getTeacher().getUsername());

    }

    @Test
    public void deleteCourseSelect() throws Exception {
        CourseSelect courseSelect = new CourseSelect();
        System.out.print(courseSelect.deleteCourseSelect(10));
    }

    @Test
    public void addCourseSelect() throws Exception {
        CourseSelect courseSelect = new CourseSelect();
        CourseDao courseDao =new CourseDao();
        UserDao userDao =new UserDao();
        CourseBean courseBean = courseDao.searchCourse(9);
        UserBean userBean =userDao.searchUser(1);
        UserBean userBean2 =userDao.searchUser(2);
        System.out.println(courseSelect.addCourseSelect(new CourseSelectBean(courseBean,userBean2)));
        System.out.println(courseSelect.addCourseSelect(new CourseSelectBean(courseBean,userBean2)));

    }

    @Test
    public void idCourseSelect() throws Exception {
        CourseSelect courseSelect = new CourseSelect();
        System.out.println(courseSelect.idCourseSelect("math","nsi4","aa"));
        System.out.println(courseSelect.idCourseSelect("math","nsi4","zx"));
        System.out.println(courseSelect.idCourseSelect("english","aa","nsi4"));
    }

}