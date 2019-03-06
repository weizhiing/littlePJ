package Dao;

import Bean.StudentStatisticBean;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by your dad on 2018/7/29.
 */
public class StudentStatisticDao extends ConnectionDao {

    public ArrayList<StudentStatisticBean> searchStudentStatistic(String courseName,String teacherName) {
        Connection connection =connection();
        CourseDao courseDao =new CourseDao();
        String sql = "select * from StudentStatistic where courseId ="+courseDao.idCourse(courseName,teacherName);
        Statement st = null;
        ArrayList<StudentStatisticBean> arrayList = new ArrayList<StudentStatisticBean>();
        try {
            st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                UserDao userDao=new UserDao();
                StudentStatisticBean studentStatisticBean = searchStudentStatistic(idStudentStatistic(courseName,teacherName,userDao.searchUser(Integer.parseInt(rs.getString(3))).getUsername()));
                arrayList.add(studentStatisticBean);
            }
            connectionClose(connection,st);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return arrayList;
    }

    public void addHomeworkCount(String courseName,String teacherName){
        Connection connection =connection();
        CourseDao a = new CourseDao();
      //  int homeworkCount = searchStudentStatistic(a.idCourse(courseName,teacherName)).getHomework()+1 ;
       String sql  ="update StudentStatistic set homeworkCount=homeworkCount+1  where courseid =" +a.idCourse(courseName,teacherName);
        try {
           PreparedStatement pre = (PreparedStatement)connection.prepareStatement(sql);
           // pre.setInt(1,homeworkCount);
            pre.executeUpdate();
            connectionClose(connection,pre);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addHomeworkDo(String courseName,String teacherName,String studentName){
        Connection connection =connection();
        CourseDao a = new CourseDao();
        UserDao b =new UserDao();
        //  int homeworkCount = searchStudentStatistic(a.idCourse(courseName,teacherName)).getHomework()+1 ;
        String sql  ="update StudentStatistic set homeworkDo=homeworkDo+1  where courseid =" +a.idCourse(courseName,teacherName)+
                " and studentid="+b.idUser(studentName);
        try {
            PreparedStatement pre = (PreparedStatement)connection.prepareStatement(sql);
            // pre.setInt(1,homeworkCount);
            pre.executeUpdate();
            connectionClose(connection,pre);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public StudentStatisticBean searchStudentStatistic(int idStudentStatistic){
        Connection connection =connection();
        String sql = "select * from StudentStatistic where id ="+idStudentStatistic;
        Statement st = null;
        StudentStatisticBean StudentStatisticBean = new StudentStatisticBean();
        try {
            st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            connectionExe(rs,"CourseSelect");
            while (rs.next()) {
                CourseDao courseDao =new CourseDao();
                StudentStatisticBean.setCourse(courseDao.searchCourse(Integer.parseInt(rs.getString(2))));
                UserDao user= new UserDao();
                StudentStatisticBean.setStudentName(user.searchUser(Integer.parseInt(rs.getString(3))));
                StudentStatisticBean.setHomework(Integer.parseInt(rs.getString(4)));
                StudentStatisticBean.setHomeworkDo(Integer.parseInt(rs.getString(5)));
                StudentStatisticBean.setResult(Integer.parseInt(rs.getString(6)));

            }
            connectionClose(connection,st);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return StudentStatisticBean;
    }

    public boolean deleteStudentStatistic(int idStudentStatistic) {
        Connection connection =connection();
        if(searchStudentStatistic(idStudentStatistic).getCourse()==null){
            return false;
        }
        String sql = "delete from StudentStatistic where id =" +idStudentStatistic;
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql);
            connectionClose(connection,statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    public boolean addStudentStatistic(StudentStatisticBean StudentStatistic) {
        Connection connection =connection();
        if(idStudentStatistic(StudentStatistic.getCourse().getCourse(),StudentStatistic.getCourse().getTeacher().getUsername()
                ,StudentStatistic.getStudentName().getUsername())!=0){
            return false;
        }
        String sql = "insert into StudentStatistic(id ,courseId,studentId,homeworkCount,homeworkdo) values(?,?,?,?,?)";
        try {
            PreparedStatement pre = (PreparedStatement)connection.prepareStatement(sql);
            pre.setInt(1,0);
            CourseDao courseDao =new CourseDao();
            pre.setInt(2,courseDao.idCourse(StudentStatistic.getCourse().getCourse(),StudentStatistic.getCourse().getTeacher().getUsername()));
            UserDao userDao =new UserDao();
            pre.setInt(3,userDao.idUser(StudentStatistic.getStudentName().getUsername()));
            pre.setInt(4,0);
            pre.setInt(5,0);
            pre.executeUpdate();
            connectionClose(connection,pre);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    public int idStudentStatistic(String courseName, String teacherName, String studentName) {
        Connection connection = connection();
        UserDao userDao = new UserDao();
        CourseDao courseDao = new CourseDao();
        String sql = "select * from StudentStatistic where courseId ='" + courseDao.idCourse(courseName,teacherName)
                +"' and studentId ="  +userDao.idUser(studentName);

        return connectionCount(connection,sql,"StudentStatistic");    }
}
