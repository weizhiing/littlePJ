package DaoInterface.courseDao;

import Bean.CourseBean;

import java.util.ArrayList;

/**
 * Created by your dad on 2018/7/25.
 */
/*
     * 对数据库的操作
     * 老师开设的课程数据
     * 1.搜索:根据id搜索出具体课程；根据老师名称返回老师的开课列表
     * 2.删除：根据id删除具体课程
     * 3.添加：
     * 4.修改：修改描述，和图片
     */
public interface CourseDao {

    public boolean modifyCourse(int idCourse, String courseDes,String courseImage);

    public CourseBean searchCourse(int idCourse);

    public ArrayList<CourseBean> searchOpenCourse(String teacherName);

    public boolean deleteCourse(int idCourse);

    public boolean addCourse(CourseBean course);

    public int idCourse(String courseName,String teacherName);
}
