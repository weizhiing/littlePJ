package Bean;

/**
 * Created by your dad on 2018/7/26.
 */
public class StudentStatisticBean {
   private  CourseBean course;
 private  UserBean studentName;
    private int homework=0;
    private int homeworkDo=0;
    private int result;

    public StudentStatisticBean(CourseBean course, UserBean studentName,int result) {
        this.course = course;
        this.studentName = studentName;
      this.result =result;

    }

    public StudentStatisticBean() {
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public CourseBean getCourse() {
        return course;
    }

    public void setCourse(CourseBean course) {
        this.course = course;
    }

    public UserBean getStudentName() {
        return studentName;
    }

    public void setStudentName(UserBean studentName) {
        this.studentName = studentName;
    }

    public int getHomework() {
        return homework;
    }

    public void setHomework(int homework) {
        this.homework = homework;
    }

    public int getHomeworkDo() {
        return homeworkDo;
    }

    public void setHomeworkDo(int homeworkDo) {
        this.homeworkDo = homeworkDo;
    }
}
