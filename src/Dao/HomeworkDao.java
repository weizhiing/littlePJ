package Dao;

import Bean.CourseBean;
import Bean.HomeworkBean;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by your dad on 2018/7/29.
 */
public class HomeworkDao extends ConnectionDao implements DaoInterface.homeworkDao.HomeworkDao {
    @Override
    public ArrayList<HomeworkBean> searchHomework(String courseName, String teacherName) {
        Connection connection = connection();
        CourseDao courseDao = new CourseDao();
        String sql = "select * from homework where courseId =" + courseDao.idCourse(courseName, teacherName);
        Statement st = null;
        ArrayList<HomeworkBean> arrayList = new ArrayList<HomeworkBean>();
        try {
            st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                HomeworkBean homeworkBean = searchHomeworkOne(Integer.parseInt(rs.getString(1)));
                arrayList.add(homeworkBean);
            }
            connectionClose(connection, st);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return arrayList;
    }

    public HomeworkBean searchHomeworkOne(int idHomework) {
        Connection connection = connection();
        String sql = "select * from homework where id =" + idHomework;
        Statement st = null;
        HomeworkBean homework = new HomeworkBean();
        try {
            st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            connectionExe(rs, "homework");
            while (rs.next()) {
                CourseDao courseDao = new CourseDao();
                homework.setCourse(courseDao.searchCourse(Integer.parseInt(rs.getString(4))));

                homework.setHomeworkName(rs.getString(2));
                homework.setHomeworkDescription(rs.getString(3));

            }
            connectionClose(connection, st);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return homework;
    }

    @Override
    public boolean modifyHomework(int idCourse, String homeworkName, String homeworkDes) {
        Connection connection = connection();
        CourseDao a = new CourseDao();
        CourseBean course = a.searchCourse(idCourse);
        if (course.getCourse() == null) {
            return false;
        }


        String sql = "update homework set homeworkDescription = ? where courseid =" + idCourse +
                " and homeworkName= '" + homeworkName + "'";

        try {
            PreparedStatement pre = (PreparedStatement) connection.prepareStatement(sql);
            pre.setString(1, homeworkDes);
            pre.executeUpdate();
            connectionClose(connection, pre);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean deleteHomework(int idHomework) {
        Connection connection = connection();

        if (searchHomeworkOne(idHomework).getCourse() == null) {
            return false;
        }
        String sql = "delete * FROM homeworkDo where homework =" + idHomework;
        ArrayList<Integer> a = new ArrayList<Integer>();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                a.add(Integer.parseInt(rs.getString(1)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

         sql = "delete from homework where id =" + idHomework;

        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        sql = "delete from homeworkDo where homework =" + idHomework;
        statement = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        //     HomeworkDao homeworkDao= new HomeworkDao();
        //    HomeworkBean  a= homeworkDao.searchHomeworkOne(idHomework);
        //HomeworkDoDao homeworkDoDao = new HomeworkDoDao();

 for(int i=0;i<a.size();i++){
            sql = "delete from homeworkCheck where homeworkdoid =" +a.get(i);
            statement = null;
            try {
                statement = connection.createStatement();
                statement.executeUpdate(sql);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        connectionClose(connection, statement);
        return true;
    }

    @Override
    public boolean addHomework(HomeworkBean homework) {
        Connection connection = connection();
        if (idHomework(homework.getCourse().getCourse(), homework.getCourse().getTeacher().getUsername()
                , homework.getHomeworkName()) != 0) {
            return false;
        }
        String sql = "insert into homework(id ,homeworkName,homeworkDescription,courseId) values(?,?,?,?)";
        try {
            PreparedStatement pre = (PreparedStatement) connection.prepareStatement(sql);
            pre.setInt(1, 0);
            CourseDao courseDao = new CourseDao();
            pre.setInt(4, courseDao.idCourse(homework.getCourse().getCourse(), homework.getCourse().getTeacher().getUsername()));


            pre.setString(2, homework.getHomeworkName());
            pre.setString(3, homework.getHomeworkDescription());
            pre.executeUpdate();
            connectionClose(connection, pre);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //  StudentStatisticDao studentStatisticDao =new StudentStatisticDao();
        //  studentStatisticDao.addHomeworkCount(homework.getCourse().getCourse(),homework.getCourse().getTeacher().getUsername());
        return true;
    }

    @Override
    public int idHomework(String courseName, String teacherName, String homeworkName) {
        Connection connection = connection();
        UserDao userDao = new UserDao();
        CourseDao courseDao = new CourseDao();


        String sql = "select * from homework where courseId ='" + courseDao.idCourse(courseName, teacherName)
                + "' and homeworkName ='" + homeworkName + "'";

        return connectionCount(connection, sql, "homework");
    }
}
