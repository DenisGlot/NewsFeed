package com.denisgl.service;

import com.denisgl.entity.Category;
import com.denisgl.entity.News;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Date;

@Service
public class InitDataBase {

    final static Logger logger = Logger.getLogger(InitDataBase.class);

    private static SessionFactory sessionFactory;

    private static SessionFactory buildSessionFactory(){
        return new Configuration().configure().addAnnotatedClass(Category.class).addAnnotatedClass(News.class).buildSessionFactory();
    }

    private static boolean isInitialized;

    @PostConstruct
    public void init() {
        if(!isInitialized) {
            sessionFactory = getSessionFactory();
            Session session = sessionFactory.openSession();
            Transaction tx = session.beginTransaction();
            Category sports = new Category();
            sports.setName("sport");
            session.persist(sports);
            Category politics = new Category();
            politics.setName("politics");
            session.persist(politics);
            Category tech = new Category();
            tech.setName("tech");
            session.persist(tech);
            News news = new News();
            news.setName("Donald Trump");
            news.setContent("Democratic senators wore them to highlight the US opioid crisis. " +
                    "In 2016, more than 42,000 people in the US died of drug overdoses linked to the strong painkillers - the worst year in history.");
            news.setPublication(new Date());
            news.setCategory(politics);
            session.merge(news);
            tx.commit();
            session.close();
        }
        isInitialized =true;
    }

    public static SessionFactory getSessionFactory() {
        if(sessionFactory == null) {
            synchronized (InitDataBase.class){
                sessionFactory = buildSessionFactory();
            }
        }
        return sessionFactory;
    }
}