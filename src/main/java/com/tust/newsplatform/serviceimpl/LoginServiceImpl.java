package com.tust.newsplatform.serviceimpl;

import com.tust.newsplatform.entities.User;
import com.tust.newsplatform.entities.UserExample;
import com.tust.newsplatform.mapper.UserMapper;
import com.tust.newsplatform.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserMapper userMapper;


    /**
     * @param username
     * @return
     */
    @Override
    public String selectUsername(String username) {
        UserExample example = new UserExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<User> user = userMapper.selectByExample(example);
        return user.get(0).getUsername();
    }

    @Override
    public String selectPassword(String username) {
        UserExample example = new UserExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<User> user = userMapper.selectByExample(example);
        return user.get(0).getPassword();
    }

    @Override
    public boolean isHaveUsername(String username) {

        List<User> users = userMapper.selectByExample(null);

        for (User user : users) {

            if (username.equals(user.getUsername())) {
                return true;
            }

        }
        return false;
    }

    @Override
    public void addUser(User user) {

        userMapper.insertSelective(user);

    }
}
