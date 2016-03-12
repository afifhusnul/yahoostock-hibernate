package com.ajar;

import com.ajar.config.HibernateSessionManager;
import com.ajar.entity.Tickler;
import com.ajar.entity.TrxStock;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Session;

public class Run {

    /**
     * @param args
     */
    public static void main(String[] args) {
//        Add
//        Session session = HibernateSessionManager.getSessionFactory().openSession();
//        session.beginTransaction();
//        System.out.println("Adding a customer record !");
//        Tickler t = new Tickler();
//        t.setTicklerID("ASII.JK");
//        t.setTicklerName("ASTRA INTERNATIONAL TBK");
//        session.save(t);
//        session.getTransaction().commit();

// Read data from DB
        Session session = HibernateSessionManager.getSessionFactory().openSession();
        session.beginTransaction();
        System.out.println("Reading Data from Tickler !");
        String HQL_QUERY = "from Tickler";
        org.hibernate.Query query = session.createQuery(HQL_QUERY);
        List list = query.list();

        System.out.println("Retrieving " + list.size() + " Records:");
        for (Iterator it = list.iterator(); it.hasNext();) {            
            Tickler t = (Tickler) it.next();
            System.out.println("----------");
            System.out.println("ID:" + t.getTicklerID()+ "  Company Name : " + t.getTicklerName());            
            System.out.println("----------");

            //// Get STOCK data from Yahoo
            GregorianCalendar start = new GregorianCalendar(2016, 2, 10);
            GregorianCalendar end = new GregorianCalendar(2016, 2, 12);
            StockDownloader test = new StockDownloader(t.getTicklerID(), start, end);
                        
    }
        System.out.println("Done ------ **** ------ !!!");   
        System.exit(0);
    }
}
