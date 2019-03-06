package Bean;

/**
 * Created by your dad on 2018/7/25.
 */
/*
 *记录老师开的课程
 */
public class CourseBean {
    private String  course;
    private String  description;
    private UrlBean    image;
    private UserBean   teacher;
     public CourseBean(){

    }

    public CourseBean(String course, String description, UrlBean image, UserBean teacher) {
        this.course = course;
        this.description = description;
        this.image = image;
        this.teacher = teacher;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UrlBean getImage() {
        return image;
    }

    public void setImage(UrlBean image) {
        this.image = image;
    }

    public UserBean getTeacher() {
        return teacher;
    }

    public void setTeacher(UserBean teacher) {
        this.teacher = teacher;
    }
}
