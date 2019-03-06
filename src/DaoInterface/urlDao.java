package DaoInterface;

import Bean.UrlBean;

/**
 * Created by your dad on 2018/7/27.
 */
public interface urlDao {

    public boolean addUrl(UrlBean url);

    public boolean deleteUrl(int idUrl);

    public UrlBean searchUrl(int idUrl);

    public boolean modifyUrl(int idUrl,String url);

    public int idUrl(String url);



}
