package Bean;

/**
 * Created by your dad on 2018/7/25.
 */
public class SourceBean {
    private   CourseBean   course;
    private String        sourceName;
    private String        sourceDescription;
    private String        url;
    public SourceBean(){

    }

    public SourceBean(CourseBean course, String sourceName, String sourceDescription,String url) {
        this.course = course;
        this.sourceName = sourceName;
        this.sourceDescription = sourceDescription;
        this.url =url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public CourseBean getCourse() {
        return course;
    }

    public void setCourse(CourseBean course) {
        this.course = course;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getSourceDescription() {
        return sourceDescription;
    }

    public void setSourceDescription(String sourceDescription) {
        this.sourceDescription = sourceDescription;
    }
}
