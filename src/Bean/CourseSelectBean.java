package Bean;

/**
 * Created by your dad on 2018/7/25.
 */
public class CourseSelectBean {
    private CourseBean   course;
    private UserBean     student;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    private int result ;
    public CourseSelectBean(){

    }

    public CourseSelectBean(CourseBean course, UserBean student) {
        this.course = course;
        this.student = student;
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
}
