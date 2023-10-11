package com.shtura.helper.dao.aptekajet;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.shtura.helper.dao.jdbc.HibernateSessionFactoryUtil;
import com.shtura.helper.entity.aptekajet.Buyer;

public class BuyerDao {

    public Buyer findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Buyer.class, id);
    }
    
    public void save(Buyer buyer) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(buyer);
        tx1.commit();
        session.close();
    }
    
    public void update(Buyer buyer) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(buyer);
        tx1.commit();
        session.close();
    }
    
    public void delete(Buyer buyer) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(buyer);
        tx1.commit();
        session.close();
    }

    public List<Buyer> findAll() {
        List<Buyer> sellers = (List<Buyer>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Buyer").list();
        return sellers;
    }
}
