package Bean;

/**
 * Created by your dad on 2018/7/25.
 */
public class DiscussBean {
    private CourseBean course;
    private UserBean student;
    private String title;
    private String content;
    private Boolean beAbleToDelete;

    public Boolean getBeAbleToDelete() {
        return beAbleToDelete;
    }

    public void setBeAbleToDelete(Boolean beAbleToDelete) {
        this.beAbleToDelete = beAbleToDelete;
    }


    public DiscussBean() {
    }

    public DiscussBean(CourseBean course, UserBean student, String title, String content) {
        this.course = course;
        this.student = student;
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public CourseBean getCourse() {
        return course;
    }

    public void setCourse(CourseBean course) {
        this.course = course;
    }

    public UserBean getStudent() {
        return student;
    }

    public void setStudent(UserBean student) {
        this.student = student;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
