package io.sofastack.cloud.auth.model;

/**
 * @author: guolei.sgl (guolei.sgl@antfin.com) 2019/4/19 5:35 PM
 * @since:
 **/
public class RegisterRequest {

    private String userName;

    private String password;

    private String email;

    private String payPassword;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPayPassword() {
        return payPassword;
    }

    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword;
    }
}
