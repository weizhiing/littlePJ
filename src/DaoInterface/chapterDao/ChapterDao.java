package DaoInterface.chapterDao;

import Bean.ChapterBean;

import java.util.ArrayList;

/**
 * Created by your dad on 2018/7/27.
 */

/*数据库的处理
 *
 */
public interface ChapterDao {

    public ArrayList<ChapterBean> searchChapter(String courseName,String teacherName);

    public boolean deleteChapter(int idChapter);

    public boolean addChapter(ChapterBean chapter);

    public int idChapter(String courseName,String teacherName,String chapterName);
}
