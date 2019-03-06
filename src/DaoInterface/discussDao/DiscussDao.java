package DaoInterface.discussDao;

import Bean.DiscussBean;

import java.util.ArrayList;

/**
 * Created by your dad on 2018/7/27.
 */
/*数据库的操作：
 *1.删除
 * 2.添加
 * 3.搜索：返回该课程的讨论
 */
public interface DiscussDao {

    public ArrayList<DiscussBean> searchDiscuss(String courseName,String teacherName);

    public boolean deleteDiscuss(int idDiscuss);

    public boolean addDiscuss(DiscussBean discuss);

    public int idDiscuss(String courseName,String teacherName,String studentName,String title);


}
