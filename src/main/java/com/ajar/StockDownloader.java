/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ajar;

import com.ajar.config.HibernateSessionManager;
import com.ajar.entity.TrxStock;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;
import org.hibernate.Session;

/**
 *
 * @author nusnafif
 */
public class StockDownloader {

    public static final int DATE = 0;
    public static final int OPEN = 0;
    public static final int HIGH = 0;
    public static final int LOW = 0;
    public static final int CLOSE = 0;
    public static final int VOLUME = 0;
    public static final int ADJCLOSE = 0;

    private ArrayList<GregorianCalendar> dates;
    private ArrayList<Double> opens;
    private ArrayList<Double> highs;
    private ArrayList<Double> lows;
    private ArrayList<Double> closes;
    private ArrayList<Integer> volumes;
    private ArrayList<Double> adjcloses;

    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    public StockDownloader(String symbol, GregorianCalendar start, GregorianCalendar end) {

        dates = new ArrayList<GregorianCalendar>();
        opens = new ArrayList<Double>();
        highs = new ArrayList<Double>();
        lows = new ArrayList<Double>();
        closes = new ArrayList<Double>();
        volumes = new ArrayList<Integer>();
        adjcloses = new ArrayList<Double>();

        //http://finance.yahoo.com/q/hp?s=ASII.JK&a=09&b=17&c=2000&d=01&e=7&f=2016&g=d
        //http://real-chart.finance.yahoo.com/table.csv?s=ASII.JK&a=09&b=17&c=2000&d=01&e=7&f=2016&g=d&ignore=.csv
//        http://real-chart.finance.yahoo.com/table.csv?s=ASII.JK&d=2&e=7&f=2016&g=d&a=9&b=17&c=2000&ignore=.csv
//        String url = "http://ichart.finance.yahoo.com/table.csv?s=" + symbol
        String url = "http://real-chart.finance.yahoo.com/table.csv?s=" + symbol
                + "&a=" + start.get(Calendar.MONTH)
                + "&b=" + start.get(Calendar.DAY_OF_MONTH)
                + "&c=" + start.get(Calendar.YEAR)
                + "&d=" + end.get(Calendar.MONTH)
                + "&e=" + end.get(Calendar.DAY_OF_MONTH)
                + "&f=" + end.get(Calendar.YEAR)
                //                + "&g=d&ignore=.csv";
                + "&g=d";

        try {
            URL yahoofinn = new URL(url);
            URLConnection data = yahoofinn.openConnection();
            Scanner input = new Scanner(data.getInputStream());

            if (input.hasNext()) //Skip line, its just header
            {
                input.nextLine();
            }

//            Save to DB 
            Session session = HibernateSessionManager.getSessionFactory().openSession();
            session.beginTransaction();
            // Start read line
            while (input.hasNextLine()) {
                String line = input.nextLine();
                // TODO - Correct data to the correct arraylist
                // Print all result directly to prompt                 
//                System.out.println(symbol+","+line);  
//                Afif's property to save into Table DB
                      
                String str = symbol + "," + line;
                String strArray[] = str.split(",");
//                System.out.println("Save to DB");

                TrxStock trx = new TrxStock();
                trx.setTicklerID(strArray[0]);
                trx.setTicklerDT(formatter.parse(strArray[1]));
                trx.setTicklerOpen(Float.valueOf(strArray[2]));
                trx.setTicklerHigh(Float.valueOf(strArray[3]));
                trx.setTicklerLow(Float.valueOf(strArray[4]));
                trx.setTicklerClose(Float.valueOf(strArray[5]));
                trx.setTicklerVol(Float.valueOf(strArray[6]));
                trx.setTicklerInt(Float.valueOf(strArray[7]));
                session.save(trx);

                for (int i = 0; i < 100000; i++) {
                    if (i % 50 == 0) { //20, same as the JDBC batch size
                        //flush a batch of inserts and release memory:
                        session.flush();
                        session.clear();
                    }
                }
            }
            session.getTransaction().commit();
            System.out.println("Save to DB Success");
            session.close();
        } catch (Exception e) {
            System.err.println(e);
        }

    }

    public ArrayList<GregorianCalendar> getDates() {
        return dates;
    }

    public ArrayList<Double> getOpens() {
        return opens;
    }

}
