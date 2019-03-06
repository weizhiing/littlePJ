package DaoInterface.homeworkDao;

import Bean.HomeworkCheckBean;

import java.util.ArrayList;

/**
 * Created by your dad on 2018/7/27.
 */
/*对数据库的操作：
 * 1.搜索：返回老师打分的作业
 * 2.添加：
 *
 */
public interface HomeworkCheckDao {

    public ArrayList<HomeworkCheckBean> searchHomeworkCheck(String courseName, String teacherName, String studentName);

    public boolean addHomeworkCheck(HomeworkCheckBean homeworkCheck);

    public int idHomeworkCheck(String courseName,String teacherName,String homeworkName,
                            String studentName);
}
