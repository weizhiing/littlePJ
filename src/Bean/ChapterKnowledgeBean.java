package Bean;

/**
 * Created by your dad on 2018/7/25.
 */
public class ChapterKnowledgeBean {
    private ChapterBean chapter;
    private String knowledgeName;

    public ChapterKnowledgeBean() {

    }

    public ChapterKnowledgeBean(ChapterBean chapter, String knowledgeName) {
        this.chapter = chapter;
        this.knowledgeName = knowledgeName;
    }

    public ChapterBean getChapter() {
        return chapter;
    }

    public void setChapter(ChapterBean chapter) {
        this.chapter = chapter;
    }

    public String getKnowledgeName() {
        return knowledgeName;
    }

    public void setKnowledgeName(String knowledgeName) {
        this.knowledgeName = knowledgeName;
    }


}
