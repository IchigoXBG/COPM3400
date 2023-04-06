package com.xbg3.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import org.apache.ibatis.type.Alias;

@Alias("user")
public class User {

    @TableId("userid")
    private String userId;
    @TableField("userName")
    private String userName;
    @TableField("upass")
    private String upass;

    public User() {
    }

    public User(String userId, String upass) {
        this.userId = userId;
        this.upass = upass;
    }

    public User(String userId, String userName, String upass) {
        this.userId = userId;
        this.userName = userName;
        this.upass = upass;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUpass() {
        return upass;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUpass(String upass) {
        this.upass = upass;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", upass='" + upass + '\'' +
                '}';
    }
}
