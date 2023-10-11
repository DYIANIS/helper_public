package com.shtura.helper.dao.aptekajet;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.shtura.helper.dao.jdbc.HibernateSessionFactoryUtil;
import com.shtura.helper.entity.aptekajet.Seller;

public class SellerDao {

    public Seller findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Seller.class, id);
    }
    
    public List<Seller> findByName(String name) {
        
        return (List<Seller>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("from Seller as sell where sell.name = :name").setParameter("name", name).list();
        
        /*List<Seller> sellesrs = (List<Seller>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("from Seller as sell where sell.name = :name").setParameter("name", name).list();
        
        if (sellesrs == null) {
            return null;
        } else {
            return sellesrs.get(0);
        }*/
        
        //return (Seller) HibernateSessionFactoryUtil.getSessionFactory().openSession().createCriteria(Seller.class).add(Restrictions.eq("Имя", name)).list().get(0);
    }
    
    public void save(Seller seller) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(seller);
        tx1.commit();
        session.close();
    }
    
    public void update(Seller seller) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(seller);
        tx1.commit();
        session.close();
    }
    
    public void delete(Seller seller) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(seller);
        tx1.commit();
        session.close();
    }

    public List<Seller> findAll() {
        List<Seller> sellers = (List<Seller>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Seller").list();
        return sellers;
    }
}
