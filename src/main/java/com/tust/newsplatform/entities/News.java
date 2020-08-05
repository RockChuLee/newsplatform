package com.tust.newsplatform.entities;

import java.util.Date;

public class News {
    private Integer id;

    private Integer userId;

    private String userUsername;

    private String title;

    private String category;

    private Date createtime;

    private String content;

    private Integer clickRate;

    private Integer pageView;

    private Integer permission;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserUsername() {
        return userUsername;
    }

    public void setUserUsername(String userUsername) {
        this.userUsername = userUsername == null ? null : userUsername.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getClickRate() {
        return clickRate;
    }

    public void setClickRate(Integer clickRate) {
        this.clickRate = clickRate;
    }

    public Integer getPageView() {
        return pageView;
    }

    public void setPageView(Integer pageView) {
        this.pageView = pageView;
    }

    public Integer getPermission() {
        return permission;
    }

    public void setPermission(Integer permission) {
        this.permission = permission;
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", userId=" + userId +
                ", userUsername='" + userUsername + '\'' +
                ", title='" + title + '\'' +
                ", category='" + category + '\'' +
                ", createtime=" + createtime +
                ", content='" + content + '\'' +
                ", clickRate=" + clickRate +
                ", pageView=" + pageView +
                ", permission=" + permission +
                '}';
    }
}