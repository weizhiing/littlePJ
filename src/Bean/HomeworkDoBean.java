package Bean;

/**
 * Created by your dad on 2018/7/25.
 */
public class HomeworkDoBean {
     private HomeworkBean   homework;
    private UserBean        student;
    private String           content;
    public HomeworkDoBean(){

    }

    public HomeworkDoBean(HomeworkBean homework, UserBean student, String content) {
        this.homework = homework;
        this.student = student;
        this.content = content;
    }

    public HomeworkBean getHomework() {
        return homework;
    }

    public void setHomework(HomeworkBean homework) {
        this.homework = homework;
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
