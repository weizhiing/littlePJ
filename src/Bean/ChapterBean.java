package Bean;

/**
 * Created by your dad on 2018/7/25.
 */
public class ChapterBean {
    private CourseBean course;
    private String chapterName;

    public   ChapterBean() {
    }

    public ChapterBean(CourseBean course, String chapterName) {
        this.course = course;
        this.chapterName = chapterName;
    }

    public void setCourse(CourseBean course) {
        this.course = course;
    }

    public String getUnitName() {
        return chapterName;
    }

    public void setUnitName(String unitName) {
        this.chapterName = unitName;
    }



    public CourseBean getCourse() {
        return course;
    }
}
