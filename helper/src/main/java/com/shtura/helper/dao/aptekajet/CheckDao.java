package com.shtura.helper.dao.aptekajet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.internal.SessionImpl;
import org.springframework.transaction.annotation.Transactional;

import com.shtura.helper.dao.jdbc.HibernateSessionFactoryUtil;
import com.shtura.helper.entity.aptekajet.Сheck;

public class CheckDao {

    public Сheck findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Сheck.class, id);
    }
    
    public List<Сheck> findByIdAndDate(int id, String date) {
        return (List<Сheck>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("from Сheck as ch where ch.key = :id AND CONVERT(date, ch.openingDateTime) = CONVERT(date, :date)").setParameter("id", id).setParameter("date", date).list();
    }
    
    public List<Сheck> findByNumber(String number, String needRegistrar, String needDate) {
        return (List<Сheck>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("from Сheck as ch where ch.number = :number AND ch.registrar =:needRegistrar AND CONVERT(date, ch.openingDateTime) = CONVERT(date, :needDate)").setParameter("number", number).setParameter("needRegistrar", needRegistrar).setParameter("needDate", needDate).list();   
    }
    
    public List<Сheck> findByNumberWisoutNeedRegistrar(String number, String needDate) {
        return (List<Сheck>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("from Сheck as ch where ch.number = :number AND CONVERT(date, ch.openingDateTime) = CONVERT(date, :needDate) ORDER BY ch.key ASC").setParameter("number", number).setParameter("needDate", needDate).list();   
    }
    
    public void save(Сheck check) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(check);
        tx1.commit();
        session.close();
    }
    
    public void update(Сheck check) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(check);
        tx1.commit();
        session.close();
    }
    
    public void delete(Сheck check) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(check);
        tx1.commit();
        session.close();
    }

    public List<Сheck> findAll() {
        List<Сheck> sellers = (List<Сheck>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Сheck").list();
        return sellers;
    }
       
    public List<String> getAllFR() {
        List<String> sellers = (List<String>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("Select DISTINCT ch.registrar from Сheck as ch").list();
        return sellers;
    }
    
    public List<Сheck> findСhecksForEdit(Integer startCheckKey, Integer endCheckKey, Integer sellerIdFrom, String needRegistrar, String needDate) {
        List<Сheck> sellers = (List<Сheck>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("from Сheck ch where ch.key>= :startCheckKey AND ch.key <= :endCheckKey AND ch.seller.key = :sellerIdFrom AND ch.registrar = :needRegistrar AND CONVERT(date, ch.openingDateTime) = CONVERT(date, :needDate)").setParameter("startCheckKey", startCheckKey).setParameter("endCheckKey", endCheckKey).setParameter("sellerIdFrom", sellerIdFrom).setParameter("needRegistrar", needRegistrar).setParameter("needDate", needDate).list();
        return sellers;
    }
    
    public List<Сheck> findСhecksForEditWisoutFromSeller(Integer startCheckKey, Integer endCheckKey, String needRegistrar, String needDate) {
        List<Сheck> sellers = (List<Сheck>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("from Сheck ch where ch.key >= :startCheckKey AND ch.key <= :endCheckKey AND ch.registrar = :needRegistrar AND CONVERT(date, ch.openingDateTime) = CONVERT(date, :needDate)").setParameter("startCheckKey", startCheckKey).setParameter("endCheckKey", endCheckKey).setParameter("needRegistrar", needRegistrar).setParameter("needDate", needDate).list();
        return sellers;
    }
	
	public List<Сheck> findСhecksByDateTime(String dateTime) {
        List<Сheck> checks = (List<Сheck>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("from Сheck ch where CONVERT(date, ch.openingDateTime) = CONVERT(date, :dateTime) AND CONVERT(time, ch.openingDateTime) = CONVERT(time, :dateTime) ORDER BY ch.key ASC").setParameter("dateTime", dateTime).list();
        return checks;
    }
	
    public List<Сheck> findStartСhecksByDateTime(String dateTime) {
        List<Сheck> checks = (List<Сheck>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("from Сheck ch where CONVERT(date, ch.openingDateTime) = CONVERT(date, :dateTime) AND CONVERT(time, ch.openingDateTime) >= CONVERT(time, :dateTime) ORDER BY ch.key ASC").setParameter("dateTime", dateTime).list();
        return checks;
    }
   
    public List<Сheck> findEndСhecksByDateTime(String dateTime) {
        List<Сheck> checks = (List<Сheck>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("from Сheck ch where CONVERT(date, ch.openingDateTime) = CONVERT(date, :dateTime) AND CONVERT(time, ch.openingDateTime) <= CONVERT(time, :dateTime) ORDER BY ch.key DESC").setParameter("dateTime", dateTime).list();
        return checks;
    }
    
    @Transactional
    public void editSellerInСhecks(Integer startKey, Integer endKey, Integer sellerIdFrom, Integer sellerIdTo, String needRegistrar, String fromDateTime, String toDateTime) {
        //List<Сheck> sellers = (List<Сheck>)  
                HibernateSessionFactoryUtil.getSessionFactory().openSession().createNativeQuery("UPDATE Сheck ch set ch.seller.key = :sellerIdTo where ch.key>= :startKey AND ch.key <= :endKey AND ch.seller.key = :sellerIdFrom AND ch.registrar = :needRegistrar AND DATEDIFF(minute, ch.openingTime, :fromDateTime) >= 0 AND DATEDIFF(minute, ch.openingTime, :toDateTime) >= 0").setParameter("sellerIdTo", sellerIdTo).setParameter("startKey", startKey).setParameter("endKey", endKey).setParameter("sellerIdFrom", sellerIdFrom).setParameter("needRegistrar", needRegistrar).setParameter("fromDateTime", fromDateTime).setParameter("toDateTime", toDateTime).executeUpdate();
        //return sellers;
    }
	
	public void kickStorage(Integer checkKey) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        
        SessionImpl sessionImpl = (SessionImpl) session;
        Connection conn = sessionImpl.connection();
        
        PreparedStatement st;
        try {
            st = conn.prepareStatement("{call JSON_CreateToCheck(?)}");
            st.setInt(1, checkKey);
            st.execute();
        } catch (SQLException e) {
            System.out.println("--------------------- CheckDao ---------------------");
            e.printStackTrace();
        }
                        
        tx1.commit();
        session.close();
	}
}
