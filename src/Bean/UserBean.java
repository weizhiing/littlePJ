package Bean;

/**
 * Created by your dad on 2018/7/25.
 */

/**
 * 用于储存用户信息
 */
public class UserBean {
    private  String username;
    private String password;
    private String email;
   public UserBean(){
    }

    public UserBean(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
