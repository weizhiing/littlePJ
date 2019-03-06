package Bean;

/**
 * Created by your dad on 2018/7/25.
 */
public class DiscussReplyBean {
    private  DiscussBean discuss;
    private  UserBean   Student;
    private String      content;
    private Boolean beAbleToDelete;
    public Boolean getBeAbleToDelete() {
        return beAbleToDelete;
    }

    public void setBeAbleToDelete(Boolean beAbleToDelete) {
        this.beAbleToDelete = beAbleToDelete;
    }


    public DiscussReplyBean(){

    }

    public DiscussReplyBean(DiscussBean discuss, UserBean student, String content) {
        this.discuss = discuss;
        Student = student;
        this.content = content;
    }

    public DiscussBean getDiscuss() {
        return discuss;
    }

    public void setDiscuss(DiscussBean discuss) {
        this.discuss = discuss;
    }

    public UserBean getStudent() {
        return Student;
    }

    public void setStudent(UserBean student) {
        Student = student;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
