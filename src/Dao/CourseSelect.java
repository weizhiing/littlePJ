package Dao;

import Bean.CourseBean;
import Bean.CourseSelectBean;
import Bean.UserBean;
import DaoInterface.courseDao.CourseSelectDao;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by your dad on 2018/7/28.
 */
public class CourseSelect extends ConnectionDao implements CourseSelectDao{
    @Override
    public ArrayList<CourseBean> searchCourseSelect(String studentName) {
        Connection connection =connection();
        UserDao userDao =new UserDao();
        String sql = "select * from courseSelect where studentId ="+userDao.idUser(studentName);
        Statement st = null;
        ArrayList<CourseBean> arrayList = new ArrayList<CourseBean>();
        try {
            st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                CourseDao courseDao = new CourseDao();
                CourseBean course = courseDao.searchCourse(Integer.parseInt(rs.getString(2)));
                arrayList.add(course);
            }
            connectionClose(connection,st);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return arrayList;
    }

    public ArrayList<UserBean> searchList(String courseName, String teacherName){
        Connection connection =connection();
        UserDao userDao =new UserDao();
        CourseDao courseDao = new CourseDao();
        String sql = "select * from courseSelect where courseId ="+courseDao.idCourse(courseName,teacherName);
        Statement st = null;
        ArrayList<UserBean> arrayList = new ArrayList<UserBean>();
        try {
            st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                UserBean userBean = userDao.searchUser(Integer.parseInt(rs.getString(3)));
                arrayList.add(userBean);
            }
            connectionClose(connection,st);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return arrayList;
    }

    public CourseSelectBean searchCourseSelect(int idCourseSelect){
        Connection connection =connection();
        String sql = "select * from courseSelect where id ="+idCourseSelect;
        Statement st = null;
        CourseSelectBean courseSelect = new CourseSelectBean();
        try {
            st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            connectionExe(rs,"CourseSelect");
            while (rs.next()) {
                CourseDao courseDao =new CourseDao();
                courseSelect.setCourse(courseDao.searchCourse(Integer.parseInt(rs.getString(2))));
                UserDao user= new UserDao();
                courseSelect.setStudent(user.searchUser(Integer.parseInt(rs.getString(3))));
                courseSelect.setResult(Integer.parseInt(rs.getString(4)));
            }
            connectionClose(connection,st);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return courseSelect;
    }

    @Override
    public boolean deleteCourseSelect(int idCourseSelect) {
        Connection connection =connection();
    if(searchCourseSelect(idCourseSelect).getCourse()==null){
        return false;
    }
        String
         sql = "delete from courseSelect where id =" +idCourseSelect;
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

    @Override
    public boolean addCourseSelect(CourseSelectBean courseSelect) {
        Connection connection =connection();
        if(idCourseSelect(courseSelect.getCourse().getCourse(),courseSelect.getCourse().getTeacher().getUsername()
        ,courseSelect.getStudent().getUsername())!=0){
            return false;
        }
        String sql = "insert into courseSelect(id ,courseId,studentId) values(?,?,?)";
        try {
            PreparedStatement pre = (PreparedStatement)connection.prepareStatement(sql);
            pre.setInt(1,0);
            CourseDao courseDao =new CourseDao();
            pre.setInt(2,courseDao.idCourse(courseSelect.getCourse().getCourse(),courseSelect.getCourse().getTeacher().getUsername()));
            UserDao userDao =new UserDao();
            pre.setInt(3,userDao.idUser(courseSelect.getStudent().getUsername()));
            pre.executeUpdate();
            connectionClose(connection,pre);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    public boolean  scoreDo(int id,int score){
        Connection connection =connection();
        String sql = "update courseSelect set result = ?  where id ="+id;
        try {
            PreparedStatement pre = (PreparedStatement) connection.prepareStatement(sql);
            pre.setInt(1, score);
            pre.executeUpdate();
            connectionClose(connection, pre);
        } catch (SQLException e) {
            e.printStackTrace();
        }
      return true;
    }


    @Override
    public int idCourseSelect(String courseName, String teacherName, String studentName) {
        Connection connection = connection();
        UserDao userDao = new UserDao();
        CourseDao courseDao = new CourseDao();
        String sql = "select * from courseSelect where courseId ='" + courseDao.idCourse(courseName,teacherName)
                +"' and studentId ="  +userDao.idUser(studentName);

        return connectionCount(connection,sql,"courseSelect");    }
}
