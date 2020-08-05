package com.tust.newsplatform.serviceimpl;

import com.tust.newsplatform.entities.News;
import com.tust.newsplatform.entities.NewsExample;
import com.tust.newsplatform.entities.User;
import com.tust.newsplatform.entities.UserExample;
import com.tust.newsplatform.mapper.NewsMapper;
import com.tust.newsplatform.mapper.UserMapper;
import com.tust.newsplatform.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsMapper newsMapper;

    @Autowired
    private UserMapper userMapper;

    //根据时间浏览重大新闻
    @Override
    public List<News> selectAllNews() {

        NewsExample example = new NewsExample();
        example.createCriteria().andPermissionEqualTo(1);

        //根据时间倒叙排列
        example.setOrderByClause("createtime desc");

        List<News> list = newsMapper.selectByExample(example);
        return list;
    }

    //根据点记录算法浏览热点新闻
    @Override
    public List<News> selectAllNewshot() {

        NewsExample example = new NewsExample();
        example.createCriteria().andPermissionEqualTo(1);

        //根据时间倒叙排列
        example.setOrderByClause("((click_rate*10 + page_view) / page_view) desc");

        List<News> list = newsMapper.selectByExample(example);
        return list;
    }

    @Override
    public List<News> selectAllAdminNews() {
        List<News> list = newsMapper.selectByExample(null);
        return list;
    }

    @Override
    public List<News> selectAllNewsSociety() {

        NewsExample example = new NewsExample();
        example.createCriteria().andCategoryEqualTo("社会").andPermissionEqualTo(1);

        List<News> list = newsMapper.selectByExample(example);
        return list;
    }

    @Override
    public List<News> selectAllNewsEducation() {
        NewsExample example = new NewsExample();
        example.createCriteria().andCategoryEqualTo("教育").andPermissionEqualTo(1);

        List<News> list = newsMapper.selectByExample(example);
        return list;
    }

    @Override
    public List<News> selectAllNewsPe() {
        NewsExample example = new NewsExample();
        example.createCriteria().andCategoryEqualTo("体育").andPermissionEqualTo(1);

        List<News> list = newsMapper.selectByExample(example);
        return list;
    }

    @Override
    public List<News> selectAllNewsAmusement() {
        NewsExample example = new NewsExample();
        example.createCriteria().andCategoryEqualTo("娱乐").andPermissionEqualTo(1);

        List<News> list = newsMapper.selectByExample(example);
        return list;
    }

    @Override
    public List<News> selectAllNewsGame() {
        NewsExample example = new NewsExample();
        example.createCriteria().andCategoryEqualTo("游戏").andPermissionEqualTo(1);

        List<News> list = newsMapper.selectByExample(example);
        return list;
    }

    @Override
    public List<News> selectUserNews(String username) {

        NewsExample example = new NewsExample();
        example.createCriteria().andUserUsernameEqualTo(username);

        List<News> list = newsMapper.selectByExample(example);
        return list;
    }

    @Override
    public News selectOneNews(Integer id) {

        NewsExample example = new NewsExample();
        example.createCriteria().andIdEqualTo(id);

        List<News> newsDetail = newsMapper.selectByExample(example);
        News news = newsDetail.get(0);
        return news;
    }

    @Override
    public void addNews(String username, News news) {

        UserExample example = new UserExample();
        example.createCriteria().andUsernameEqualTo(username);

        List<User> user = userMapper.selectByExample(example);
        news.setUserId(user.get(0).getId());

        news.setUserUsername(username);
        news.setCreatetime(new Date());
        news.setPageView(0);
        news.setClickRate(0);
        news.setPermission(0);

        newsMapper.insertSelective(news);


    }

    @Override
    public void updateNews(News news) {
        news.setPermission(0);
        news.setCreatetime(new Date());
        newsMapper.updateByPrimaryKeySelective(news);
    }

    @Override
    public void deleteNews(Integer id) {
        newsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void pageViewAddOne(News news) {

        news.setPageView(news.getPageView() + 1);
        newsMapper.updateByPrimaryKeySelective(news);
    }

    @Override
    public void updatePermissionToZero(Integer id) {
        News news = newsMapper.selectByPrimaryKey(id);
        news.setId(id);
        news.setPermission(0);

        newsMapper.updateByPrimaryKeySelective(news);
    }

    @Override
    public void updatePermissionToOne(Integer id) {
        News news = newsMapper.selectByPrimaryKey(id);
        news.setId(id);
        news.setPermission(1);

        newsMapper.updateByPrimaryKeySelective(news);

    }

    @Override
    public void addClickRate(Integer id) {

        News news = newsMapper.selectByPrimaryKey(id);
        news.setClickRate(news.getClickRate()+1);
        news.setPageView(news.getPageView()-1);

        newsMapper.updateByPrimaryKeySelective(news);

    }
}
