package DaoInterface;

import Bean.UserBean;

/**
 * Created by your dad on 2018/7/25.
 */
public interface UserDao {
    /*
     * 对数据库的操作
     * 用户数据
     * 1.搜索
     * 2.删除
     * 3.添加
     */

  public UserBean searchUser(int idUser);

  public boolean deleteUser(int idUser);

  public boolean addUser(UserBean user);

  public int idUser(String name);

}

