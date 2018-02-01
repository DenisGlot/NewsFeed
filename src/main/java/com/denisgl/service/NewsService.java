package com.denisgl.service;

import com.denisgl.entity.Category;
import com.denisgl.entity.News;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService {

    @Autowired
    CategoryService categoryService;

    final static Logger logger = Logger.getLogger(NewsService.class);

    public List<News> findAll(){
        SessionFactory sessionFactory = InitDataBase.getSessionFactory();
        Session session = sessionFactory.openSession();
        List<News> newsList = session.createQuery("SELECT n FROM News n").getResultList();
        session.close();
        return newsList;
    }

    public News findById(Integer id){
        SessionFactory sessionFactory = InitDataBase.getSessionFactory();
        Session session = sessionFactory.openSession();
        List<News> newsList = session.createQuery("SELECT n FROM News n WHERE n.newsId=:id").setParameter("id",id).getResultList();
        session.close();
        if(newsList == null){
            return null;
        }
        return newsList.get(0);
    }

    public List<News> getAllByCategoryId(Integer category_id){
        Category category = categoryService.findById(category_id);
        SessionFactory sessionFactory = InitDataBase.getSessionFactory();
        Session session = sessionFactory.openSession();
        List<News> newsList = session.createQuery(
                "SELECT n FROM News n WHERE n.category =:category").setParameter("category",category).getResultList();
        session.close();
        return newsList;
    }

    public void save(News news){
        if(news == null){return;}
        SessionFactory sessionFactory = InitDataBase.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(news);
        tx.commit();
        session.close();
    }

    public void merge(News news) {
        if(news == null){return;}
        SessionFactory sessionFactory = InitDataBase.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.merge(news);
        tx.commit();
        session.close();
    }

    public void delete(News news){
        if(news == null){return;}
        SessionFactory sessionFactory = InitDataBase.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.remove(news);
        tx.commit();
        session.close();
    }

}
