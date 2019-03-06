package Dao;

import Bean.DiscussBean;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by your dad on 2018/7/29.
 */
public class DiscussDao  extends ConnectionDao implements DaoInterface.discussDao.DiscussDao{
    @Override
    public ArrayList<DiscussBean> searchDiscuss(String courseName, String teacherName) {
        Connection connection =connection();
        CourseDao courseDao = new CourseDao();
        String sql = "select * from discuss where courseId ="+courseDao.idCourse(courseName,teacherName);
        Statement st = null;
        ArrayList<DiscussBean> arrayList = new ArrayList<DiscussBean>();
        try {
            st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                DiscussBean discuss = searchDiscuss(Integer.parseInt(rs.getString(1)));
                arrayList.add(discuss);
            }
            connectionClose(connection,st);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return arrayList;
    }

    public DiscussBean searchDiscuss(int idDiscuss){
        Connection connection =connection();
        String sql = "select * from Discuss where id ="+idDiscuss;
        Statement st = null;
        DiscussBean discuss = new DiscussBean();
        try {
            st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            connectionExe(rs,"Discuss");
            while (rs.next()) {
                CourseDao courseDao =new CourseDao();
                discuss.setCourse(courseDao.searchCourse(Integer.parseInt(rs.getString(2))));
                UserDao user= new UserDao();
                discuss.setStudent(user.searchUser(Integer.parseInt(rs.getString(3))));
                discuss.setTitle(rs.getString(4));
                discuss.setContent(rs.getString(5));

            }
            connectionClose(connection,st);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return discuss;
    }

    @Override
    public boolean deleteDiscuss(int idDiscuss) {
        Connection connection =connection();
        if(searchDiscuss(idDiscuss).getCourse()==null){
            return false;
        }
        String sql = "delete from discuss where id =" +idDiscuss;
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql);
            connectionClose(connection,statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
         connection =connection();

         sql = "delete from discussReply where discussid =" +idDiscuss;
         statement = null;
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
    public boolean addDiscuss(DiscussBean discuss) {
        Connection connection =connection();
        if(idDiscuss(discuss.getCourse().getCourse(),discuss.getCourse().getTeacher().getUsername()
                ,discuss.getStudent().getUsername(),discuss.getTitle())!=0){
            return false;
        }
        String sql = "insert into discuss(id ,courseId,studentId,title,content) values(?,?,?,?,?)";
        try {
            PreparedStatement pre = (PreparedStatement)connection.prepareStatement(sql);
            pre.setInt(1,0);
            CourseDao courseDao =new CourseDao();
            pre.setInt(2,courseDao.idCourse(discuss.getCourse().getCourse(),discuss.getCourse().getTeacher().getUsername()));
            UserDao userDao =new UserDao();
            pre.setInt(3,userDao.idUser(discuss.getStudent().getUsername()));
            pre.setString(4,discuss.getTitle());
            pre.setString(5,discuss.getContent());
            pre.executeUpdate();
            connectionClose(connection,pre);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public int idDiscuss(String courseName,String teacherName, String studentName, String title) {
        Connection connection = connection();
        UserDao userDao = new UserDao();
        CourseDao courseDao = new CourseDao();
        String sql = "select * from Discuss where courseId ='" + courseDao.idCourse(courseName,teacherName)
                +"' and studentId ='"  +userDao.idUser(studentName)+"' and title ='"+title+"'";

        return connectionCount(connection,sql,"courseSelect");
    }
}
