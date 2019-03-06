package test;

import Bean.UserBean;
import Dao.UserDao;
import org.junit.Test;

/**
 * Created by your dad on 2018/7/27.
 */
public class UserDaoTest {
    @Test
    public void searchUserTest() throws Exception {
        UserDao userDao = new UserDao();
        System.out.println(userDao.searchUser(1).getUsername());
        System.out.println(userDao.searchUser(1).getPassword());
        System.out.println(userDao.searchUser(1).getEmail());
    }

    @Test
    public void deleteUserTest() throws Exception {
        UserDao userDao = new UserDao();
       System.out.print(userDao.deleteUser(101));
    }

    @Test
    public void addUserTest() throws Exception {
        UserDao userDao=new UserDao();
        System.out.println(userDao.addUser(new UserBean("nsi4","ss","email")));
       System.out.println(userDao.addUser(new UserBean("aa","ss","email")));
        System.out.println(userDao.addUser(new UserBean("aa","ss","email")));
    }

    @Test
    public void idUserTest() throws Exception {
        UserDao userDao = new UserDao();
      //  System.out.print(userDao.idUser("aa"));
      //  ArrayList<Integer> a = userDao.idUserStr("a");
     //   System.out.println(a.size());
      //  System.out.println(a.get(0));
      //  System.out.println(a.get(1));
        String a = "ssaad";
        if(a.contains("a")){
            System.out.print(0);
        }
        if(a.contains("as")){
            System.out.print(1);
        }
        if(a.contains("")){
            System.out.print(2);
        }
    }

}