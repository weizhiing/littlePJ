package test;

import Bean.HomeworkBean;
import Bean.HomeworkCheckBean;
import Bean.HomeworkDoBean;
import Bean.UserBean;
import Dao.HomeworkCheck;
import Dao.HomeworkDao;
import Dao.UserDao;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by your dad on 2018/7/29.
 */
public class HomeworkCheckTest {
    @Test
    public void searchHomeworkCheck() throws Exception {
        HomeworkCheck homeworkCheck =new HomeworkCheck();
        ArrayList<HomeworkCheckBean> a = homeworkCheck.searchHomeworkCheck("math","nsi4","aa");
        System.out.println(a.size());
        System.out.println(a.get(0).getScore());
        System.out.println(a.get(1).getScore());
    }

    @Test
    public void addHomeworkCheck() throws Exception {
        HomeworkCheck homeworkCheck =new HomeworkCheck();
        UserDao userDao=new UserDao();
        UserBean userBean =userDao.searchUser(2);
        HomeworkDao homeworkDao =new HomeworkDao();
        HomeworkBean homeworkBean =homeworkDao.searchHomeworkOne(3);
        HomeworkDoBean homeworkDoBean = new HomeworkDoBean(homeworkBean,userBean,"asdsa");
        System.out.println(homeworkCheck.addHomeworkCheck(new HomeworkCheckBean(homeworkDoBean,15)));
    }

    @Test
    public void idHomeworkCheck() throws Exception {
        HomeworkCheck homeworkCheck =new HomeworkCheck();
        System.out.println(homeworkCheck.idHomeworkCheck("math","nsi4","离散","aa"));
    }

}