package com.tust.newsplatform.serviceimpl;

import com.tust.newsplatform.entities.News;
import com.tust.newsplatform.entities.NewsExample;
import com.tust.newsplatform.entities.UserInfo;
import com.tust.newsplatform.entities.UserInfoExample;
import com.tust.newsplatform.mapper.UserInfoMapper;
import com.tust.newsplatform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public UserInfo selectByUserName(String username) {

        UserInfoExample example = new UserInfoExample();
        example.createCriteria().andUserUsernameEqualTo(username);

        List<UserInfo> userInfos = userInfoMapper.selectByExample(example);
        UserInfo userInfo = userInfos.get(0);
        return userInfo;
    }

    @Override
    public void updateUserInfo(UserInfo userInfo) {

        userInfoMapper.updateByPrimaryKeySelective(userInfo);

    }
}
