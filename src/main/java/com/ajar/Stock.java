/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ajar;

import com.ajar.config.HibernateSessionManager;
import java.util.GregorianCalendar;
import org.hibernate.Session;

/**
 *
 * @author nusnafif
 */
public class Stock {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        GregorianCalendar start = new GregorianCalendar(2016, 1, 1);
        GregorianCalendar end = new GregorianCalendar(2016, 2, 8);
        StockDownloader test = new StockDownloader("ASII.JK", start, end);        
                       
    }

}
