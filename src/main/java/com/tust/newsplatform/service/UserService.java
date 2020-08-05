package com.tust.newsplatform.service;

import com.tust.newsplatform.entities.UserInfo;

public interface UserService {

    public UserInfo selectByUserName(String username);

    public void updateUserInfo(UserInfo userInfo);
}
