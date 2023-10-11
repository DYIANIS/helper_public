package com.shtura.helper.dao.aptekajet;

import org.hibernate.Session;
import org.hibernate.Transaction;


import com.shtura.helper.dao.jdbc.HibernateSessionFactoryUtil;
import com.shtura.helper.entity.aptekajet.Ostatki;

public class OstatkiDao {

    public Ostatki findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Ostatki.class, id);
    }
    
    public void save(Ostatki ostatki) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(ostatki);
        tx1.commit();
        session.close();
    }
    
    public void update(Ostatki ostatki) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(ostatki);
        tx1.commit();
        session.close();
    }
    
    public void delete(Ostatki ostatki) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(ostatki);
        tx1.commit();
        session.close();
    }
}
