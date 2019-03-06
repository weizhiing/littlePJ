package Dao;

import Bean.SourceBean;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by your dad on 2018/7/29.
 */
public class SourceDao extends ConnectionDao implements DaoInterface.sourceDao.SourceDao {
    @Override
    public ArrayList<SourceBean> searchSource(String courseName, String teacherName) {
        Connection connection =connection();
        CourseDao courseDao = new CourseDao();
        String sql = "select * from Source where courseId ="+courseDao.idCourse(courseName,teacherName);
        Statement st = null;
        ArrayList<SourceBean> arrayList = new ArrayList<SourceBean>();
        try {
            st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                SourceBean sourceBean = searchSource(Integer.parseInt(rs.getString(1)));
                arrayList.add(sourceBean);
            }
            connectionClose(connection,st);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return arrayList;
    }

    public SourceBean searchSource(int id){
        Connection connection = connection();
        String sql = "select * from Source where id =" + id;
        Statement st = null;
        SourceBean sourceBean = new SourceBean();
        try {
            st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            connectionExe(rs, "Source");
            while (rs.next()) {
                CourseDao courseDao = new CourseDao();
                sourceBean.setCourse(courseDao.searchCourse(Integer.parseInt(rs.getString(2))));

                sourceBean.setSourceName(rs.getString(3));
                sourceBean.setSourceDescription(rs.getString(4));

                sourceBean.setUrl(rs.getString(5));

            }
            connectionClose(connection, st);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sourceBean;
    }

    @Override
    public boolean deleteSource(int idSource) {
        Connection connection =connection();
        if(searchSource(idSource).getCourse()==null){
            return false;
        }
        String sql = "delete from Source where id =" +idSource;
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
    public boolean addSource(SourceBean Source) {
        Connection connection =connection();
        if(idSource(Source.getCourse().getCourse(),Source.getCourse().getTeacher().getUsername()
                ,Source.getSourceName())!=0){
            return false;
        }
        String sql = "insert into source(id ,courseId,sourcename,SourceDescription,fileName) values(?,?,?,?,?)";
        try {
            PreparedStatement pre = (PreparedStatement)connection.prepareStatement(sql);
            pre.setInt(1,0);
            CourseDao courseDao =new CourseDao();
            pre.setInt(2,courseDao.idCourse(Source.getCourse().getCourse(),Source.getCourse().getTeacher().getUsername()));

           UrlDao urlDao=new UrlDao();
            pre.setString(3,Source.getSourceName());
            pre.setString(4,Source.getSourceDescription());
            pre.setString(5,Source.getUrl());
            pre.executeUpdate();
            connectionClose(connection,pre);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public int idSource(String courseName, String teacherName, String sourceName) {
        Connection connection = connection();
        UserDao userDao = new UserDao();
        CourseDao courseDao = new CourseDao();
        String sql = "select * from source where courseId ='" + courseDao.idCourse(courseName, teacherName)
                + "' and sourceName ='" + sourceName + "'";

        return connectionCount(connection, sql, "source");
    }
}
