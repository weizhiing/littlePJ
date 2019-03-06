package DaoInterface.discussDao;

import Bean.DiscussReplyBean;

import java.util.ArrayList;

/**
 * Created by your dad on 2018/7/27.
 */
/*数据库的操作：
 *1.删除
 * 2.添加
 * 3.搜索：返回该课程的讨论
 */
public interface DiscussReply {

    public ArrayList<DiscussReplyBean> searchDiscussReply(String courseName,String teacherName
                                                      ,String studentName,String title);

    public boolean deleteDiscussReply(int idDiscussReply);

    public boolean addDiscussReply(DiscussReplyBean discussReply);

    public int idDiscussReply(String courseName,String teacherName,String studentName1,String title,String studentName2,String content);


}
