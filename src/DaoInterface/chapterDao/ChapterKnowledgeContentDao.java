package DaoInterface.chapterDao;

import Bean.ChapterKnowledgeContentBean;

import java.util.ArrayList;

/**
 * Created by your dad on 2018/7/27.
 */
public interface ChapterKnowledgeContentDao {


    public ArrayList<ChapterKnowledgeContentBean> searchChapterKnowledgeContent(String courseName, String teacherName, String chapterName,String knowledgeName);

    public boolean deleteChapterKnowledgeContent(int idChapterKnowledgeContent);

    public boolean addChapterKnowledgeContent(ChapterKnowledgeContentBean chapterKnowledgeContent);

    public int idChapterKnowledgeContent(String courseName,String teacherName,String chapterName,String knowledgeName,String url  );
}
