package com.shtura.helper.dao.jdbc;

import javax.annotation.PostConstruct;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shtura.helper.entity.aptekajet.Seller;
import com.shtura.helper.entity.aptekajet.TConnectionStringMainServer;
import com.shtura.helper.entity.aptekajet.Buyer;
import com.shtura.helper.entity.aptekajet.Сheck;
import com.shtura.helper.entity.helperdb.UserDBForCurrentUser;
import com.shtura.helper.repositories.helperdb.UserDBForCurrentUserService;
import com.shtura.helper.entity.aptekajet.CheckPosition;
import com.shtura.helper.entity.aptekajet.Ostatki;

@Component
public class HibernateSessionFactoryUtil {
	
	private static String connectionIPAdress = "***.***.***.***";
    private static String connectionUserDB = "******";
    private static String connectionPassDB= "******";
    
    private static UserDBForCurrentUserService userDBForCurrentUserService;
    
    @Autowired
    private UserDBForCurrentUserService autowiredUserDBForCurrentUserService;

    private HibernateSessionFactoryUtil() {}
    
    public static SessionFactory getSessionFactory() {
        SessionFactory sessionFactory;
        
        try {
            
            UserDBForCurrentUser userDBForCurrentUser = userDBForCurrentUserService.getUserDBForCurrentUser();
            connectionIPAdress = userDBForCurrentUser.getConnectionIPAdress();
            connectionUserDB = userDBForCurrentUser.getConnectionUserDB();
            connectionPassDB = userDBForCurrentUser.getConnectionPassDB();
            
			Configuration configuration = new Configuration();
			
			configuration.addAnnotatedClass(Seller.class);
			configuration.addAnnotatedClass(Buyer.class);
			configuration.addAnnotatedClass(Сheck.class);
			configuration.addAnnotatedClass(Ostatki.class);
			configuration.addAnnotatedClass(CheckPosition.class);
			configuration.addAnnotatedClass(TConnectionStringMainServer.class);
			
			// Database connection settings 
			
			configuration.setProperty("hibernate.connection.driver_class", "com.microsoft.sqlserver.jdbc.SQLServerDriver");
			configuration.setProperty("hibernate.connection.url", "jdbc:sqlserver://" + connectionIPAdress + ";databaseName=aptekajet");
			configuration.setProperty("hibernate.connection.username", connectionUserDB);
			configuration.setProperty("hibernate.connection.password", connectionPassDB);
			
			// Echo all executed SQL to stdout 
			
			configuration.setProperty("show_sql", "true");
			
			// SQL dialect 
			
			configuration.setProperty("dialect", "org.hibernate.dialect.SQLServer2012Dialect");
			configuration.setProperty("hibernate.connection.useUnicode", "true");
			configuration.setProperty("hibernate.connection.CharSet", "UTF-8");
			configuration.setProperty("hibernate.connection.characterSetResults", "UTF-8");
			configuration.setProperty("hibernate.connection.characterEncoding", "UTF-8");
			configuration.setProperty("hibernate.naming.physical-strategy", "com.shtura.helper.dao.jdbc.CustomPhysicalNamingStrategy");
			configuration.setProperty("hibernate.naming.physical-strategy", "org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl");
			
			//sessionFactory = configuration.buildSessionFactory();
			
			StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
			sessionFactory = configuration.buildSessionFactory(builder.build());

        } catch (Exception e) {
            System.out.println("--------------------- HibernateSessionFactoryUtil ---------------------");
            System.out.println("--------------------- Exception ---------------------");
            
            Configuration configuration = new Configuration();
            
            configuration.addAnnotatedClass(Seller.class);
            configuration.addAnnotatedClass(Buyer.class);
            configuration.addAnnotatedClass(Сheck.class);
            configuration.addAnnotatedClass(Ostatki.class);
            configuration.addAnnotatedClass(CheckPosition.class);
            configuration.addAnnotatedClass(TConnectionStringMainServer.class);
            
            configuration.setProperty("hibernate.connection.driver_class", "com.microsoft.sqlserver.jdbc.SQLServerDriver");
            configuration.setProperty("hibernate.connection.url", "jdbc:sqlserver://" + connectionIPAdress + ";databaseName=aptekajet");
            configuration.setProperty("hibernate.connection.username", connectionUserDB);
            configuration.setProperty("hibernate.connection.password", connectionPassDB);
            
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
            sessionFactory = configuration.buildSessionFactory(builder.build());
            
            e.printStackTrace();
        }
        
		System.out.println("************************ CREATE NEW SessionFactory *****************************");
        System.out.println("** " + connectionIPAdress + " ** " + connectionUserDB + " *** " + connectionPassDB + " ***"); 
        return sessionFactory;
    }

    @PostConstruct
    private void init() {
        userDBForCurrentUserService = this.autowiredUserDBForCurrentUserService;
    }
    
    public static String getConnectionIPAdress() {
        return connectionIPAdress;
    }

    public static void setConnectionIPAdress(String connectionIPAdress) {
        HibernateSessionFactoryUtil.connectionIPAdress = connectionIPAdress;
    }

    public static String getConnectionUserDB() {
        return connectionUserDB;
    }

    public static void setConnectionUserDB(String connectionUserDB) {
        HibernateSessionFactoryUtil.connectionUserDB = connectionUserDB;
    }

    public static String getConnectionPassDB() {
        return connectionPassDB;
    }

    public static void setConnectionPassDB(String connectionPassDB) {
        HibernateSessionFactoryUtil.connectionPassDB = connectionPassDB;
    }
}
