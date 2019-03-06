package test;

import Bean.CourseBean;
import Bean.HomeworkBean;
import Dao.CourseDao;
import Dao.HomeworkDao;
import Dao.UserDao;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by your dad on 2018/7/29.
 */
public class HomeworkDaoTest {
    @Test
    public void searchHomework() throws Exception {
        HomeworkDao homework= new HomeworkDao();
        ArrayList<HomeworkBean> a = homework.searchHomework("math","nsi4");
        System.out.println(a.size());
        System.out.println(a.get(0).getHomeworkName());
        System.out.println(a.get(1).getHomeworkName());
    }

    @Test
    public void modifyHomework() throws Exception {
        HomeworkDao homework= new HomeworkDao();
        System.out.println(homework.modifyHomework(1,"niubi","牛逼"));
    }

    @Test
    public void deleteHomework() throws Exception {
        HomeworkDao homework= new HomeworkDao();
        System.out.println(homework.deleteHomework(2));
        System.out.println(homework.deleteHomework(2));
    }

    @Test
    public void addHomework() throws Exception {
        HomeworkDao homework= new HomeworkDao();

        CourseDao courseDao =new CourseDao();
        UserDao userDao =new UserDao();

        CourseBean courseBean = courseDao.searchCourse(9);


        System.out.println(homework.addHomework(new HomeworkBean("攻读","ss",courseBean)));
        System.out.println(homework.addHomework(new HomeworkBean("厉害","ssads",courseBean)));
        System.out.println(homework.addHomework(new HomeworkBean("攻读","ss",courseBean)));
    }

    @Test
    public void idHomework() throws Exception {
        HomeworkDao homeworkDao =new HomeworkDao();
        System.out.println(homeworkDao.idHomework("math","nsi4","离散"));
    }

}