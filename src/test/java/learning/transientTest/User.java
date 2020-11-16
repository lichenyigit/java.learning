package learning.transientTest;

import java.io.Serializable;

/**
 * @author Lichenyi
 * @date 2017-9-12 0012
 */
public class User implements Serializable {
    private static String username;
    private transient String passwd;

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPasswd() {
        return passwd;
    }
    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
}
