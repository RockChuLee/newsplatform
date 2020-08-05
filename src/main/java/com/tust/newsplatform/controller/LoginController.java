package com.tust.newsplatform.controller;

import com.tust.newsplatform.entities.User;
import com.tust.newsplatform.mapper.UserMapper;
import com.tust.newsplatform.service.LoginService;
import com.tust.newsplatform.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;


    //    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    @PostMapping("/user/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Map<String, Object> map, HttpSession session) {

        if (!this.loginService.isHaveUsername(username)) {
            map.put("msg", "用户名不存在");
            return "login";
        } else {

            if (!StringUtils.isEmpty(this.loginService.selectUsername(username)) && this.loginService.selectPassword(username).equals(password)) {
                //登陆成功,为了防止表单重复提交，可以重定向到主页
                if (username.equals("admin")) {
                    session.setAttribute("loginUser", username);
                    return "redirect:/admin.html";
                } else {
                    session.setAttribute("loginUser", username);
                    return "redirect:/main.html";
                }

            } else {
                //登录失败
                map.put("msg", "用户名密码错误");
                return "login";
            }
        }

    }

    @PostMapping("/user/signup")
    public String signup(@RequestParam("username") String username,
                         @RequestParam("password") String password,
                         Map<String, Object> map, HttpSession session) {

//        System.out.println("123");


        if (this.loginService.isHaveUsername(username)) {

            map.put("msg", "用户名已经存在");
            return "signup";
        } else {

            User user = new User();
            user.setUsername(username);
            user.setPassword(password);

            this.loginService.addUser(user);


            return "redirect:/login.html";
        }

    }

}
