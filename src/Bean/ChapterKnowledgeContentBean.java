package Bean;

/**
 * Created by your dad on 2018/7/25.
 */
public class ChapterKnowledgeContentBean {
    private ChapterKnowledgeBean  knowledge;
    private UrlBean          url;
    public ChapterKnowledgeContentBean(){

    }

    public ChapterKnowledgeContentBean(ChapterKnowledgeBean knowledge, UrlBean url) {
        this.knowledge = knowledge;
        this.url = url;
    }

    public ChapterKnowledgeBean getKnowledge() {
        return knowledge;
    }

    public void setKnowledge(ChapterKnowledgeBean knowledge) {
        this.knowledge = knowledge;
    }

    public UrlBean getUrl() {
        return url;
    }

    public void setUrl(UrlBean url) {
        this.url = url;
    }
}
