package com.denisgl.service;

import com.denisgl.entity.Category;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {
    final static Logger logger = Logger.getLogger(CategoryService.class);

    private static SessionFactory buildSessionFactory(Class clazz){
        return new Configuration().configure().addAnnotatedClass(clazz).buildSessionFactory();
    }

    public List<Category> findAll(){
        SessionFactory sessionFactory = InitDataBase.getSessionFactory();
        Session session = sessionFactory.openSession();
        List<Category> categories = session.createQuery("SELECT c FROM Category c").getResultList();
        session.close();
        return categories;
    }

    public Category findById(int id){
        SessionFactory sessionFactory = InitDataBase.getSessionFactory();
        Session session = sessionFactory.openSession();
        List<Category> categories = session.createQuery("SELECT c FROM Category c WHERE c.categoryId =:id").setParameter("id",new Integer(id)).getResultList();
        session.close();
        if(categories == null){
            return null;
        }
        return categories.get(0);
    }

    public Category findByName(String name){
        SessionFactory sessionFactory = InitDataBase.getSessionFactory();
        Session session = sessionFactory.openSession();
        List<Category> categories = session.createQuery("SELECT c FROM Category c WHERE c.name =:name").setParameter("name",name).getResultList();
        session.close();
        if(categories == null){
            return null;
        }
        return categories.get(0);
    }

    public List<String> getNames(){
        SessionFactory sessionFactory = InitDataBase.getSessionFactory();
        Session session = sessionFactory.openSession();
        List<Category> categories = session.createQuery("SELECT c FROM Category c").getResultList();
        session.close();
        List<String> names = new ArrayList<>(3);
        categories.forEach((c)->{
            names.add(c.getName());
        });
        return names;
    }
 }
