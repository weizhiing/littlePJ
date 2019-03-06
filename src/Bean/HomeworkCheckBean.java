package Bean;

/**
 * Created by your dad on 2018/7/25.
 */
public class HomeworkCheckBean {
    private HomeworkDoBean   homeworkComplete;
    private int    score;
    public HomeworkCheckBean(){

    }

    public HomeworkCheckBean(HomeworkDoBean homeworkComplete, int score) {
        this.homeworkComplete = homeworkComplete;
        this.score = score;
    }

    public HomeworkDoBean getHomeworkComplete() {
        return homeworkComplete;
    }

    public void setHomeworkComplete(HomeworkDoBean homeworkComplete) {
        this.homeworkComplete = homeworkComplete;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
