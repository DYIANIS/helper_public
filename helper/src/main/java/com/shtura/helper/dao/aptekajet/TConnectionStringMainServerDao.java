package com.shtura.helper.dao.aptekajet;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.shtura.helper.dao.jdbc.HibernateSessionFactoryUtil;

public class TConnectionStringMainServerDao {

    public String getTradePointName() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        
        Transaction tx1 = session.beginTransaction();
        
        List<Object> tradePointNameList;
        try {
            tradePointNameList = session.createSQLQuery("select TradePoint_name from T_ConnectionStringMainServer").list();//session.createSQLQuery("select tradePointName from TConnectionStringMainServer").list();
        }
        catch (Exception e) {
            return "TradePoint_name not found (";
        }
        
        tx1.commit();
        session.close();
        
        if(!tradePointNameList.isEmpty())
        {
            return tradePointNameList.get(0).toString();
        }else
            return "TradePoint_name not found (";
    }
}
