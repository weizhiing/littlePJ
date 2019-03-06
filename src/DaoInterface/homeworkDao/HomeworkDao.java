package DaoInterface.homeworkDao;

import Bean.HomeworkBean;

import java.util.ArrayList;

/**
 * Created by your dad on 2018/7/27.
 */

/* 对数据库的操作
        * 1.搜索:根据课程名，老师名返回 该课程的作业名称列表
        * 2.删除
        * 3.添加
        * 4.修改
        */
public interface HomeworkDao {

    public ArrayList<HomeworkBean> searchHomework(String courseName, String teacherName);

    public boolean modifyHomework(int idCourse,String ho,String homeworkDes);

    public boolean deleteHomework(int idHomework);

    public boolean addHomework(HomeworkBean homework);

    public int idHomework(String courseName,String teacherName,String homeworkName);
}
