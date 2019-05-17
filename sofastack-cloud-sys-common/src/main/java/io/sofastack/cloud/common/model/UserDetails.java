package io.sofastack.cloud.common.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @author: guolei.sgl (guolei.sgl@antfin.com) 2019/3/5 4:18 PM
 * @since:
 **/
@Entity(name = "SofastackCloudUser")
public class UserDetails implements Serializable {

    @Id
    @GeneratedValue
    private int    userId;
    @Column(name = "user_name", nullable = false, unique = true)
    private String userName;
    @Column(name = "nick_name", unique = true)
    private String nickName;
    @Column(name = "id_card", unique = true)
    private String idCard;
    @Column(name = "password", unique = true)
    private String password;
    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "create_time", unique = true)
    private Date   createTime;
    @Column(name = "old_password", unique = true)
    private String oldPassword;
    @Column(name = "role_type", unique = true)
    private String roleType;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }
}
