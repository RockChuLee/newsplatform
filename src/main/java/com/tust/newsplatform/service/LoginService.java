package com.tust.newsplatform.service;

import com.tust.newsplatform.entities.User;

public interface LoginService {

    /**
     * 查询用户名
     */
    public String selectUsername(String username);

    /**
     *  查询密码
     */
    public String selectPassword(String username);

    //查询数据库里面有没有改用户名

    public boolean isHaveUsername(String username);

    //添加用户

    public void addUser(User user);

}
