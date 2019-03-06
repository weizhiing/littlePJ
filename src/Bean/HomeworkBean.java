package Bean;

/**
 * Created by your dad on 2018/7/25.
 */
public class HomeworkBean {
    private  String   homeworkName;
    private String   homeworkDescription;
    private CourseBean  course;
    public HomeworkBean(){

    }

    public HomeworkBean(String homeworkName, String homeworkDescription, CourseBean course) {
        this.homeworkName = homeworkName;
        this.homeworkDescription = homeworkDescription;
        this.course = course;
    }

    public String getHomeworkName() {
        return homeworkName;
    }

    public void setHomeworkName(String homeworkName) {
        this.homeworkName = homeworkName;
    }

    public String getHomeworkDescription() {
        return homeworkDescription;
    }

    public void setHomeworkDescription(String homeworkDescription) {
        this.homeworkDescription = homeworkDescription;
    }

    public CourseBean getCourse() {
        return course;
    }

    public void setCourse(CourseBean course) {
        this.course = course;
    }
}
