package Dao;

import Bean.HomeworkDoBean;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by your dad on 2018/7/29.
 */
public class HomeworkDoDao extends ConnectionDao implements DaoInterface.homeworkDao.HomeworkDoDao {
    @Override
    public ArrayList<HomeworkDoBean> searchHomeworkDo(String courseName, String teacherName, String studentName) {
        Connection connection =connection();
        CourseDao courseDao = new CourseDao();
        HomeworkDao homeworkDao =new HomeworkDao();
        UserDao userDao =new UserDao();
        String sql = "select * from homeworkDo where student ="+userDao.idUser(studentName);
        Statement st = null;
        ArrayList<HomeworkDoBean> arrayList = new ArrayList<HomeworkDoBean>();
        try {
            st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                HomeworkDoBean homeworkDoBean = searchHomeworkDo(Integer.parseInt(rs.getString(1)));
                if(homeworkDoBean.getHomework().getCourse().getCourse().equals(courseName)&&
                        homeworkDoBean.getHomework().getCourse().getTeacher().getUsername().equals(teacherName)){
                arrayList.add(homeworkDoBean);}
            }
            connectionClose(connection,st);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return arrayList;
    }

    public ArrayList<HomeworkDoBean> searchHomeworkDoCourse (String courseName, String teacherName, String homeworkName){
        Connection connection =connection();
        CourseDao courseDao = new CourseDao();
        HomeworkDao homeworkDao =new HomeworkDao();
        UserDao userDao =new UserDao();
        String   sql = "select * from homeworkcheck ";
        Statement st = null;
        ArrayList<Integer> arrayListCheck = new ArrayList<Integer>();
        try {
            st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                arrayListCheck.add(Integer.parseInt(rs.getString(2)));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
         sql = "select * from homeworkDo where homework="+homeworkDao.idHomework(courseName,teacherName,homeworkName);
         st = null;
        ArrayList<HomeworkDoBean> arrayList = new ArrayList<HomeworkDoBean>();
        try {
            st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                int i  = Integer.parseInt(rs.getString(1));
                Boolean a=true;
                for(int j:arrayListCheck){
                    if(i==j){
                        a=false;
                    }
                }
                if(a) {
                    HomeworkDoBean homeworkDoBean = searchHomeworkDo(i);
                    arrayList.add(homeworkDoBean);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        connectionClose(connection,st);
        return arrayList;


    }


    public  HomeworkDoBean searchHomeworkDo(int isHomeworkDo){
        Connection connection = connection();
        String sql = "select * from homeworkDo where id =" + isHomeworkDo;
        Statement st = null;
        HomeworkDoBean homeworkDo = new HomeworkDoBean();
        try {
            st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            connectionExe(rs, "homeworkDo");
            while (rs.next()) {
                HomeworkDao homeworkDao =new HomeworkDao();
                homeworkDo.setHomework(homeworkDao.searchHomeworkOne(Integer.parseInt(rs.getString(2))));
                UserDao userDao =new UserDao();
                homeworkDo.setStudent(userDao.searchUser(Integer.parseInt(rs.getString(3))));
                homeworkDo.setContent(rs.getString(4));

            }
            connectionClose(connection, st);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return homeworkDo;

    }

    @Override
    public boolean addHomeworkDo(HomeworkDoBean homeworkDo) {
        Connection connection =connection();
        if(idHomeworkDo(homeworkDo.getHomework().getCourse().getCourse(),homeworkDo.getHomework().getCourse().getTeacher().getUsername()
                ,homeworkDo.getHomework().getHomeworkName(),homeworkDo.getStudent().getUsername())!=0){
            return false;
        }
        String sql = "insert into homeworkDo(id ,homework,student,content) values(?,?,?,?)";
        try {
            PreparedStatement pre = (PreparedStatement)connection.prepareStatement(sql);
            pre.setInt(1,0);
           HomeworkDao homeworkDao =new HomeworkDao();
            pre.setInt(2,homeworkDao.idHomework(homeworkDo.getHomework().getCourse().getCourse(),homeworkDo.getHomework().getCourse().getTeacher().getUsername(),homeworkDo.getHomework().getHomeworkName()));
            UserDao userDao=new UserDao();
            pre.setInt(3,userDao.idUser(homeworkDo.getStudent().getUsername()));
            pre.setString(4,homeworkDo.getContent());
            pre.executeUpdate();
            connectionClose(connection,pre);
        } catch (SQLException e) {
            e.printStackTrace();
        }
     //   StudentStatisticDao studentStatisticDao =new StudentStatisticDao();
      //  studentStatisticDao.addHomeworkDo(homeworkDo.getHomework().getCourse().getCourse(),homeworkDo.getHomework().getCourse().getTeacher().getUsername(),homeworkDo.getStudent().getUsername());

        return true;
    }

    @Override
    public int idHomeworkDo(String courseName, String teacherName, String homeworkName, String studentName) {
        Connection connection = connection();
        UserDao userDao = new UserDao();
        HomeworkDao homeworkDao =new HomeworkDao();
        String sql = "select * from homeworkDo where homework =" + homeworkDao.idHomework(courseName,teacherName,homeworkName)
                + " and student =" + userDao.idUser(studentName)+ "";

        return connectionCount(connection, sql, "homeworkDo");
    }
}
