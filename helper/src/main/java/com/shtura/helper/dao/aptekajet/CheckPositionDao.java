package com.shtura.helper.dao.aptekajet;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.shtura.helper.dao.jdbc.HibernateSessionFactoryUtil;
import com.shtura.helper.entity.aptekajet.CheckPosition;

public class CheckPositionDao {

    public CheckPosition findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(CheckPosition.class, id);
    }
    
    public void save(CheckPosition checkPosition) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(checkPosition);
        tx1.commit();
        session.close();
    }
    
    public void update(CheckPosition checkPosition) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(checkPosition);
        tx1.commit();
        session.close();
    }
    
    public void delete(CheckPosition checkPosition) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(checkPosition);
        tx1.commit();
        session.close();
    }
    
    public List<CheckPosition> findByCheckKey(Integer checkKey) {
        return (List<CheckPosition>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("from CheckPosition as chPos where chPos.check.key = :checkKey").setParameter("checkKey", checkKey).list();
        //return (Seller) HibernateSessionFactoryUtil.getSessionFactory().openSession().createCriteria(Seller.class).add(Restrictions.eq("Имя", name)).list().get(0);
    }
    
	public List<Integer> findCheckKeyForbiddenСhecksForkickStorage(Integer startCheckKey, Integer endCheckKey) {
        //что - то типо того .. на случай если sql бует понимать разницу между пустым полем и полем со значением null
	    //пока sql считает что '' = NULL (для текстового поля)
	    //List<Integer> forbiddenСhecks = (List<Integer>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("Select DISTINCT chPos.check.key from CheckPosition as chPos where chPos.check.key >= :startCheckKey AND chPos.check.key <= :endCheckKey AND chPos.medCardId != :medCardIdEmpty AND chPos.medCardId != :medCardIdNull").setParameter("startCheckKey", startCheckKey).setParameter("endCheckKey", endCheckKey).setParameter("medCardIdEmpty", "").setParameter("medCardIdNull", null).list();
        
	    List<Integer> forbiddenСhecks = (List<Integer>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("Select DISTINCT chPos.check.key from CheckPosition as chPos where chPos.check.key >= :startCheckKey AND chPos.check.key <= :endCheckKey AND chPos.medCardId != :medCardIdEmpty").setParameter("startCheckKey", startCheckKey).setParameter("endCheckKey", endCheckKey).setParameter("medCardIdEmpty", "").list();
        return forbiddenСhecks;
    }
}
