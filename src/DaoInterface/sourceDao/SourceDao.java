package DaoInterface.sourceDao;

import Bean.SourceBean;

import java.util.ArrayList;

/**
 * Created by your dad on 2018/7/27.
 */
/*
     * 对数据库的操作
     * 1.搜索:根据课程名，老师名返回 该课程的资源名称列表
     * 2.删除
     * 3.添加
     */
public interface SourceDao {



    public ArrayList<SourceBean> searchSource(String courseName, String teacherName);

    public boolean deleteSource(int idSource);

    public boolean addSource(SourceBean Source);

    public int idSource(String courseName,String teacherName,String sourceName);

}
