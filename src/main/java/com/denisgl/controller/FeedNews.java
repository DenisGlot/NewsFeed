package com.denisgl.controller;

import com.denisgl.entity.Category;
import com.denisgl.entity.News;
import com.denisgl.service.CategoryService;
import com.denisgl.service.NewsService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class FeedNews {
    final static Logger logger = Logger.getLogger(FeedNews.class);

    @Autowired
    NewsService newsService;

    @Autowired
    CategoryService categoryService;

    @ModelAttribute("news")
    public News construct(){
        return new News();
    }

    @RequestMapping("/")
    public String getNews(ModelMap model){
        model.addAttribute("categoryNames",categoryService.getNames());
        model.addAttribute("newsList",newsService.findAll());
        return "feednews";
    }
    @RequestMapping("/{id}")
    public String getNewsByCategory(@PathVariable int id,ModelMap model){
        model.addAttribute("categoryNames",categoryService.getNames());
        model.addAttribute("newsList",newsService.getAllByCategoryId(id));
        return "feednews";
    }

    @RequestMapping(value="/" , method = RequestMethod.POST)
    public String addNews(@ModelAttribute("news") News news){
        news.setCategory(fillCategoryWithName(news));
        newsService.merge(news);
        return "redirect:/";
    }
    private Category fillCategoryWithName(News news){
        return categoryService.findByName(news.getCategory().getName());
    }

    @RequestMapping("/remove/{id}")
    public String removeNews(@PathVariable int id){
        News news = newsService.findById(id);
        newsService.delete(news);
        return "redirect:/";
    }
}
