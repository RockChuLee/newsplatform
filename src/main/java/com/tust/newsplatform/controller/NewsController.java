package com.tust.newsplatform.controller;

import com.tust.newsplatform.entities.News;
import com.tust.newsplatform.entities.User;
import com.tust.newsplatform.entities.UserInfo;
import com.tust.newsplatform.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
public class NewsController {
    @Autowired
    private NewsService newsService;

    //用户自己的新闻界面，根据id回显自己的文章
    @GetMapping("/news/{loginUser}")
    public String showMyNews(@PathVariable("loginUser") String username, Model model) {
        Collection<News> news = this.newsService.selectUserNews(username);
//        for (News id:news) {
//            System.out.println(id);
//
//        }
        //放在请求域中
        model.addAttribute("news", news);
        // thymeleaf默认就会拼串
        // classpath:/templates/xxxx.html
        return "new/list";
    }

    //普通用户去到添加界面
    @GetMapping("/news")
    public String toAddPage(Model model) {

        //return add.html
        return "new/add";
    }

    //普通用户添加新闻
    @PostMapping("/news/{loginUser}")
    public String addNews(@PathVariable("loginUser") String username, News news) {

        this.newsService.addNews(username, news);
        //放在请求域中
//        model.addAttribute("news", news1);
//        System.out.println(news1);
        return "redirect:/news/{loginUser}";
    }

    //    普通用户去到修改界面
    @GetMapping("/news/update/{id}")
    public String toEditPage(@PathVariable("id") Integer id, Model model) {
        News news = this.newsService.selectOneNews(id);
        model.addAttribute("news", news);
//        System.out.println("2");
        //去到修改添加二合一
        return "new/add";
    }

    //普通用户修改新闻
    @PutMapping("/news/{loginUser}")
    public String editNews(@PathVariable("loginUser") String username, News news) {
//        System.out.println("1");
        this.newsService.updateNews(news);
//        model.addAttribute("news", news);
        return "redirect:/news/{loginUser}";
    }

    //普通用户删除新闻
    @DeleteMapping("/news/{id}")
    public String deleteNews(@PathVariable("id") Integer id) {

        this.newsService.deleteNews(id);

        return "redirect:/allnews";
    }


    //管理员浏览所有界面
    @GetMapping("admin/news")
    public String showAllNews(Model model) {
        Collection<News> news = this.newsService.selectAllAdminNews();
        //放在请求域中
        model.addAttribute("news", news);
        // thymeleaf默认就会拼串
        // classpath:/templates/xxxx.html
        return "new/listadmin";

    }

    //审核通过权限指一
    @GetMapping("admin/news/pass/{id}")
    public String passNews(@PathVariable("id") Integer id) {
        this.newsService.updatePermissionToOne(id);
//        System.out.println("+1");
        return "redirect:/admin/news";

    }

    //下架，权限置0
    @GetMapping("admin/news/unpass/{id}")
    public String unpassNews(@PathVariable("id") Integer id, Model model) {
        this.newsService.updatePermissionToZero(id);
//        System.out.println("-1");


        User user1 = new User();

        return "redirect:/admin/news";

    }

    //用户在浏览新闻界面显示所有新闻
    @GetMapping("allnews")
    public String browseAllNews(Model model) {

        Collection<News> news = this.newsService.selectAllNews();
        //放在请求域中
        model.addAttribute("news", news);

        //浏览新闻界面
        return "new/allnews";
    }

    //用户在浏览新闻界面显示所有新闻
    @GetMapping("allnews/hot")
    public String browseAllHotNews(Model model) {

        Collection<News> news = this.newsService.selectAllNewshot();
        //放在请求域中
        model.addAttribute("news", news);

        //浏览新闻界面
        return "new/allnewshot";
    }

    //社会新闻
    //用户在浏览新闻界面显示所有新闻
    @GetMapping("allnews/society")
    public String browseAllNewsSociety(Model model) {

        Collection<News> news = this.newsService.selectAllNewsSociety();
        //放在请求域中
        model.addAttribute("news", news);

        //浏览新闻界面
        return "new/allnewssociety";
    }

    //教育新闻
    //用户在浏览新闻界面显示所有新闻
    @GetMapping("allnews/education")
    public String browseAllNewsEducation(Model model) {

        Collection<News> news = this.newsService.selectAllNewsEducation();
        //放在请求域中
        model.addAttribute("news", news);

        //浏览新闻界面
        return "new/allnewseducation";
    }

    //体育新闻
    //用户在浏览新闻界面显示所有新闻
    @GetMapping("allnews/pe")
    public String browseAllNewsPe(Model model) {

        Collection<News> news = this.newsService.selectAllNewsPe();
        //放在请求域中
        model.addAttribute("news", news);

        //浏览新闻界面
        return "new/allnewspe";
    }

    //娱乐新闻
    //用户在浏览新闻界面显示所有新闻
    @GetMapping("allnews/amusement")
    public String browseAllNewsAmusement(Model model) {

        Collection<News> news = this.newsService.selectAllNewsAmusement();
        //放在请求域中
        model.addAttribute("news", news);

        //浏览新闻界面
        return "new/allnewsamusement";
    }

    //游戏新闻
    //用户在浏览新闻界面显示所有新闻
    @GetMapping("allnews/game")
    public String browseAllNewsGame(Model model) {

        Collection<News> news = this.newsService.selectAllNewsGame();
        //放在请求域中
        model.addAttribute("news", news);

        //浏览新闻界面
        return "new/allnewsgame";
    }



    //用户浏览新闻详细界面
    @GetMapping("newsdetail/{id}")
    public String browseNews(@PathVariable("id") Integer id, Model model) {
        //根据Id选择新闻，并返回news对象
        News news = this.newsService.selectOneNews(id);
        //点击率加一
        this.newsService.pageViewAddOne(news);
        //放在请求域中
        model.addAttribute("news", news);

        //浏览新闻界面
        return "new/newsdetail";
    }

    //点赞+1
    @GetMapping("addcr/{id}")
    public String clickRateAddOne(@PathVariable("id") Integer id) {

        this.newsService.addClickRate(id);
//        System.out.println("123");

        return "redirect:/newsdetail/{id}";
    }

    @GetMapping("newsdetail")
    public String returnAllNewsPage(Model model) {


        //浏览新闻界面
        return "redirect:/allnews";
    }

    //管理员在浏览新闻界面显示所有新闻
    @GetMapping("allnewsadmin")
    public String browseAllNewsAdmin(Model model) {

        Collection<News> news = this.newsService.selectAllNews();
        //放在请求域中
        model.addAttribute("news", news);

        return "new/allnewsadmin";
    }

    //管理员浏览新闻详细界面
    @GetMapping("admin/newsdetail/{id}")
    public String browseAdminNews(@PathVariable("id") Integer id, Model model) {
        //根据Id选择新闻，并返回news对象
        News news = this.newsService.selectOneNews(id);
        //点击率加一
        this.newsService.pageViewAddOne(news);
        //放在请求域中
        model.addAttribute("news", news);

        //浏览新闻界面
        return "new/newsdetailadmin";
    }

    @GetMapping("admin/newsdetail")
    public String returnAdminAllNewsPage(Model model) {


        //浏览新闻界面
        return "redirect:/admin/news";
    }


}
