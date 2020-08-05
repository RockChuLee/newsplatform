package com.tust.newsplatform.controller;

import com.tust.newsplatform.entities.News;
import com.tust.newsplatform.entities.UserInfo;
import com.tust.newsplatform.service.NewsService;
import com.tust.newsplatform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.Collection;

@Controller
public class UserInfoController {

    @Autowired
    private UserService userService;

    //管理员的个人信息页面
    @GetMapping("/userinfoadmin/{loginUser}")
    public String showMyInfoAdmin(@PathVariable("loginUser") String username, Model model) {

        UserInfo userInfo = this.userService.selectByUserName(username);

        model.addAttribute("userinfo", userInfo);

        return "user/userinfoadmin";

    }

    //普通用户的个人信息页面
    @GetMapping("/userinfo/{loginUser}")
    public String showMyInfo(@PathVariable("loginUser") String username, Model model) {

        UserInfo userInfo = this.userService.selectByUserName(username);

        model.addAttribute("userinfo", userInfo);

        return "user/userinfo";

    }

    //修改普通用户的个人信息
    //springmvc自动将请求参数和入参对象的属性进行一一绑定；要求请求参数的名字和javabean入参的对象里面的属性名是一样的
    @GetMapping("/userinfo")
    public String updateMyinfo(UserInfo userInfo, Model model) {

//        System.out.println(userInfo);

        this.userService.updateUserInfo(userInfo);

        return "redirect:/allnews";
    }
}
