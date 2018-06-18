/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.musiol.kryspin.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Util helper class. Helps parsing the date provided by client to the proper
 * format
 *
 * @author Kryspin Musiol
 * @version 1.0
 */
public class Util {

    /**
     * performes parsing on passed string
     *
     * @param s String date to get parsed into date
     * @return Properly prepared date
     */
    public static Date date(String s)  {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return dateFormat.parse(s);
        } catch (ParseException pe) {
             Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, pe);
        }
        return null;
    }

}
