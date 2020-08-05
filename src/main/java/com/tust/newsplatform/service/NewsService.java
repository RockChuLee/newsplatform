package com.tust.newsplatform.service;

import com.tust.newsplatform.entities.News;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface NewsService {

    public List<News> selectAllNews();
    public List<News> selectAllNewshot();

    public List<News> selectAllAdminNews();

    public List<News> selectAllNewsSociety();
    public List<News> selectAllNewsEducation();
    public List<News> selectAllNewsPe();
    public List<News> selectAllNewsAmusement();
    public List<News> selectAllNewsGame();

    public List<News> selectUserNews(String username);

    public News selectOneNews(Integer id);

    public void addNews(String username, News news);

    public void updateNews(News news);

    public void deleteNews(Integer id);

    public void pageViewAddOne(News news);

    public void updatePermissionToZero(Integer id);

    public void updatePermissionToOne(Integer id);

    public void addClickRate(Integer id);
}
