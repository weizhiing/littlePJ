package DaoInterface.homeworkDao;

import Bean.HomeworkDoBean;

import java.util.ArrayList;

/**
 * Created by your dad on 2018/7/27.
 */

/* 对数据库的操作
        * 1.搜索:根据课程名，老师名返回 该课程的资源名称列表
        * 2.添加
        */
public interface HomeworkDoDao {

    public ArrayList<HomeworkDoBean> searchHomeworkDo(String courseName, String teacherName,String studentName);

    public boolean addHomeworkDo(HomeworkDoBean homeworkDo);

    public int idHomeworkDo(String courseName,String teacherName,String homeworkName,
                            String studentName);

}
