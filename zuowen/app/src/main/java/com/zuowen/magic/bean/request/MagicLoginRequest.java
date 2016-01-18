package com.zuowen.magic.bean.request;


import com.zuowen.magic.bean.BaseModel;

/**
 * Created by worm on 2015/12/31.
 */
public class MagicLoginRequest extends BaseModel {
    public String userName;
    public String password;
    public String from;
    public String sign;

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

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
