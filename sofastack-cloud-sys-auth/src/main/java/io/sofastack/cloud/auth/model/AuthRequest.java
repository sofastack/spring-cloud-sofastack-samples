package io.sofastack.cloud.auth.model;

/**
 * @author: guolei.sgl (guolei.sgl@antfin.com) 2019/3/28 9:19 PM
 * @since:
 **/
public class AuthRequest {

    private String userName;

    private String password;

    private String backUrl;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBackUrl() {
        return backUrl;
    }

    public void setBackUrl(String backUrl) {
        this.backUrl = backUrl;
    }

    @Override
    public String toString() {
        return "AuthRequest{" + "userName='" + userName + '\'' + ", password='" + password + '\''
               + ", backUrl='" + backUrl + '\'' + '}';
    }
}
