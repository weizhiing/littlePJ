package DaoInterface.courseDao;

import Bean.CourseBean;
import Bean.CourseSelectBean;

import java.util.ArrayList;

/**
 * Created by your dad on 2018/7/27.
 */
/*
     * 对数据库的操作
     * 选课记录数据
     * 1.搜索:根据选课的学生名返回课程列表；//根据选课id返回搜索的选课记录
     * 2.删除
     * 3.添加
     */
public interface CourseSelectDao {



   // public CourseSelectBean searchCourseSelect(int idCourseSelect);

    public ArrayList<CourseBean>  searchCourseSelect(String studentName);

    public boolean deleteCourseSelect(int idCourseSelect);

    public boolean addCourseSelect(CourseSelectBean courseSelect);

    public int idCourseSelect(String courseName,String teacherName,String studentName);

}
