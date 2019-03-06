package Dao;

import Bean.CourseBean;
import Bean.UserBean;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by your dad on 2018/7/28.
 */
public class CourseDao extends ConnectionDao implements DaoInterface.courseDao.CourseDao {
    @Override
    public boolean modifyCourse(int idCourse, String courseDes, String courseImage) {
        Connection connection = connection();
        CourseBean course = searchCourse(idCourse);
        if (course.getCourse() == null) {
            return false;
        }

        UrlDao urlDao = new UrlDao();
        int url = urlDao.idUrl(course.getImage().getUrl());
        String sql = "update course set description = ?,imageid = ? where id = " + idCourse;

        try {
            PreparedStatement pre = (PreparedStatement) connection.prepareStatement(sql);

            pre.setString(1, courseDes);
            int courseUrl = urlDao.idUrl(courseImage);
            if (courseUrl == 0) {
                courseUrl = url;
            }
            pre.setInt(2, courseUrl);
            pre.executeUpdate();
            connectionClose(connection, pre);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    public boolean modifyCourse(int idCourse, String courseDes) {
        Connection connection = connection();
        CourseBean course = searchCourse(idCourse);
        if (course.getCourse() == null) {
            return false;
        }

        UrlDao urlDao = new UrlDao();
        int url = urlDao.idUrl(course.getImage().getUrl());
        String sql = "update course set description = ? where id = " + idCourse;

        try {
            PreparedStatement pre = (PreparedStatement) connection.prepareStatement(sql);
            pre.setString(1, courseDes);
            pre.executeUpdate();
            connectionClose(connection, pre);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    public ArrayList<CourseBean> searchCourseList(String teacherName) {
        Connection connection =connection();
        UserDao userDao =new UserDao();
        String sql = "select * from course where teacherId ="+userDao.idUser(teacherName);
        Statement st = null;
        ArrayList<CourseBean> arrayList = new ArrayList<CourseBean>();
        try {
            st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                CourseBean course = searchCourse(Integer.parseInt(rs.getString(1)));
                arrayList.add(course);
            }
            connectionClose(connection,st);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return arrayList;
    }
    @Override
    public CourseBean searchCourse(int idCourse) {
        Connection connection = connection();
        String sql = "select * from course where id =" + idCourse;
        Statement st = null;
        CourseBean course = new CourseBean();
        try {
            st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            connectionExe(rs, "Course");
            while (rs.next()) {
                course.setCourse(rs.getString(2));
                course.setDescription(rs.getString(3));
                UserDao user = new UserDao();
                course.setTeacher(user.searchUser(Integer.parseInt(rs.getString(4))));
                UrlDao urlDao = new UrlDao();
                course.setImage(urlDao.searchUrl(Integer.parseInt(rs.getString(5))));
            }
            connectionClose(connection, st);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return course;
    }

    @Override
    public ArrayList<CourseBean> searchOpenCourse(String teacherName) {
        Connection connection = connection();
        UserDao userDao = new UserDao();
        String sql = "select * from course where teacherid =" + userDao.idUser(teacherName);
        Statement st = null;
        ArrayList<CourseBean> arrayList = new ArrayList<CourseBean>();
        try {
            st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                CourseBean course = new CourseBean();
                course.setCourse(rs.getString(2));
                course.setDescription(rs.getString(3));
                UserDao user = new UserDao();
                course.setTeacher(user.searchUser(Integer.parseInt(rs.getString(4))));
                UrlDao urlDao = new UrlDao();
                course.setImage(urlDao.searchUrl(Integer.parseInt(rs.getString(5))));
                arrayList.add(course);
            }
            connectionClose(connection, st);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return arrayList;
    }

    public ArrayList<CourseBean> searchNewCourse() {
        Connection connection = connection();
        String sql = "select * from course ";
        Statement st = null;
        ArrayList<CourseBean> arrayList = new ArrayList<CourseBean>();
        try {
            st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                CourseBean course = new CourseBean();
                course.setCourse(rs.getString(2));
                course.setDescription(rs.getString(3));
                UserDao user = new UserDao();
                course.setTeacher(user.searchUser(Integer.parseInt(rs.getString(4))));
                UrlDao urlDao = new UrlDao();
                course.setImage(urlDao.searchUrl(Integer.parseInt(rs.getString(5))));
                if (arrayList.size() == 6) {
                    arrayList.remove(0);
                    arrayList.add(course);
                } else {
                    arrayList.add(course);
                }
            }


            connectionClose(connection, st);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return arrayList;
    }

    public ArrayList<CourseBean> searchHotCourse() {
        Connection connection = connection();
        String sql = "select * from courseSelect ";
        Statement st = null;
        ArrayList<CourseBean> arrayList = new ArrayList<CourseBean>();
        try {
            st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            HashMap<Integer, Integer> hotCourse = new HashMap<Integer, Integer>();
            while (rs.next()) {
                int count = Integer.parseInt(rs.getString(2));
                if (hotCourse.containsKey(count)) {
                    int value = hotCourse.get(count) + 1;
                    hotCourse.replace(count, value);
                } else {
                    hotCourse.put(count, 1);
                }
            }
            int i = 0;
            for (Integer a : hotCourse.keySet()) {
                i++;
            }
            while (i != 0) {
                i = 0;
                int courseId = 0;
                int count = 0;
                for (Integer a : hotCourse.keySet()) {
                    courseId = (hotCourse.get(a) >= count) ? a : courseId;
                    count = (hotCourse.get(a) >= count) ? hotCourse.get(a) : count;
                }
                CourseDao courseDao = new CourseDao();
                if (arrayList.size() < 6) {
                    arrayList.add(courseDao.searchCourse(courseId));
                }
                hotCourse.remove(courseId);
                for (Integer a : hotCourse.keySet()) {
                    i++;
                }
            }

            connectionClose(connection, st);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return arrayList;
    }

    private ArrayList<CourseBean> searchHotCourseCenter() {
        Connection connection = connection();
        String sql = "select * from courseSelect ";
        Statement st = null;
        ArrayList<CourseBean> arrayList = new ArrayList<CourseBean>();
        try {
            st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            HashMap<Integer, Integer> hotCourse = new HashMap<Integer, Integer>();
            while (rs.next()) {
                int count = Integer.parseInt(rs.getString(2));
                if (hotCourse.containsKey(count)) {
                    int value = hotCourse.get(count) + 1;
                    hotCourse.replace(count, value);
                } else {
                    hotCourse.put(count, 1);
                }
            }
            int i = 0;
            for (Integer a : hotCourse.keySet()) {
                i++;
            }
            while (i != 0) {
                i = 0;
                int courseId = 0;
                int count = 0;
                for (Integer a : hotCourse.keySet()) {
                    courseId = (hotCourse.get(a) >= count) ? a : courseId;
                    count = (hotCourse.get(a) >= count) ? hotCourse.get(a) : count;
                }
                CourseDao courseDao = new CourseDao();

                arrayList.add(courseDao.searchCourse(courseId));

                hotCourse.remove(courseId);
                for (Integer a : hotCourse.keySet()) {
                    i++;
                }
            }

            connectionClose(connection, st);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return arrayList;
    }

    public ArrayList<CourseBean> searchHotCourseAll(String teacherName, String courseName, String CourseDes) {
        ArrayList<CourseBean> newCourseChoice = searchNewCourseAll(teacherName, courseName, CourseDes);
        ArrayList<CourseBean> hotCourseAll = searchHotCourseCenter();
        ArrayList<CourseBean> hotCourseChoice = new ArrayList<CourseBean>();
        for (int i = 0; i < hotCourseAll.size(); i++) {
            Boolean center = false;
            int id = idCourse(hotCourseAll.get(i).getCourse(), hotCourseAll.get(i).getTeacher().getUsername());
            for (int j = 0; j < newCourseChoice.size(); j++) {
                int id2 = idCourse(newCourseChoice.get(j).getCourse(), newCourseChoice.get(j).getTeacher().getUsername());
                if (id == id2) {
                    center = true;
                }
            }
            if (center) {
                hotCourseChoice.add(hotCourseAll.get(i));
            }
        }
        return hotCourseChoice;
    }

    public ArrayList<CourseBean> searchNewCourseAll(String teacherName, String courseName, String CourseDes) {
        UserDao userDao = new UserDao();
        CourseDao courseDao = new CourseDao();

        ArrayList<Integer> teacher = userDao.idUserStr(teacherName);
        ArrayList<Integer> course = courseDao.idCourseStr(courseName, 2);
        ArrayList<Integer> description = courseDao.idCourseStr(CourseDes, 3);
        ArrayList<Integer> courseId = new ArrayList<Integer>();
        ArrayList<CourseBean> arrayList = new ArrayList<CourseBean>();

        for (int i = 0; i < course.size(); i++) {
            boolean center = false;
            for (int j = 0; j < description.size(); j++) {
                if (course.get(i) == description.get(j)) {
                    center = true;
                }
            }
            if (center)
                courseId.add(course.get(i));
        }

        for (int i = 0; i < courseId.size(); i++) {
            Boolean center = false;
            CourseBean courseBean = courseDao.searchCourse(courseId.get(i));

            for (int j = 0; j < teacher.size(); j++) {
                UserBean userBean = userDao.searchUser(teacher.get(j));
                if (courseBean.getTeacher().getUsername().equals(userBean.getUsername())) {
                    center = true;
                }
            }
            if (center) {
                arrayList.add(courseDao.searchCourse(courseId.get(i)));
            }
        }
        return arrayList;
    }

    @Override
    public boolean deleteCourse(int idCourse) {
        Connection connection = connection();
        if (searchCourse(idCourse).getCourse() == null) {
            return false;
        }
        String sql = "delete from course where id =" + idCourse;
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql);
            connectionClose(connection, statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public boolean addCourse(CourseBean course) {
        Connection connection = connection();
        if (idCourse(course.getCourse(), course.getTeacher().getUsername()) != 0) {
            return false;
        }

        String sql = "insert into course(id ,course,description,teacherid,imageid) values(?,?,?,?,?)";
        try {
            PreparedStatement pre = (PreparedStatement) connection.prepareStatement(sql);
            pre.setInt(1, 0);
            pre.setString(2, course.getCourse());
            pre.setString(3, course.getDescription());
            UserDao userDao = new UserDao();
            pre.setInt(4, userDao.idUser(course.getTeacher().getUsername()));
            UrlDao urlDao = new UrlDao();
            pre.setInt(5, urlDao.idUrl(course.getImage().getUrl()));
            pre.executeUpdate();
            connectionClose(connection, pre);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public int idCourse(String courseName, String teacherName) {
        Connection connection = connection();
        UserDao userDao = new UserDao();
        String sql = "select * from course where course ='" + courseName + "' and teacherId =" + userDao.idUser(teacherName);
        return connectionCount(connection, sql, "course");
    }

    /*
    * i=2 courseName
    * i=3 desName
    * */
    public ArrayList<Integer> idCourseStr(String Name, int i) {
        Connection connection = connection();
        String sql = "select * from course";
        Statement statement = null;
        ResultSet rs = null;
        ArrayList<Integer> count = new ArrayList<Integer>();
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(sql);
            while (rs.next()) {
                if (rs.getString(i).contains(Name)) {
                    count.add(Integer.parseInt(rs.getString(1)));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        connectionClose(connection, statement);
        return count;
    }
}
