package DaoInterface.chapterDao;

import Bean.ChapterKnowledgeBean;

import java.util.ArrayList;

/**
 * Created by your dad on 2018/7/27.
 */
public interface ChapterKnowledgeDao {


    public ArrayList<ChapterKnowledgeBean> searchChapterKnowledge(String courseName, String teacherName,String chapterName);

    public boolean deleteChapterKnowledge(int idChapterKnowledge);

    public boolean addChapterKnowledge(ChapterKnowledgeBean chapterKnowledge);

    public int idChapterKnowledge(String courseName,String teacherName,String chapterName,String knowledgeName  );
}
