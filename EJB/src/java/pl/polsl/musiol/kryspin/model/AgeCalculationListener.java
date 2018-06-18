/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.musiol.kryspin.model;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;

/**
 * Entity Listener for calculating the age of a persisted Painter basing on it's birthday
 * @author Kryspin Musiol
 * 
 * @version 1.0
 */
public class AgeCalculationListener {

    @PostLoad
    @PostPersist
    @PostUpdate
    private void calculateAge(Painter painter) {
        if (painter.getDateOfBirth() == null) {
            painter.setAge(null);
            return;
        }

        Calendar birth = new GregorianCalendar();
        birth.setTime(painter.getDateOfBirth());
        Calendar now = new GregorianCalendar();
        now.setTime(new Date());

        int adjust = 0;

        if (now.get(Calendar.DAY_OF_YEAR) - birth.get(Calendar.DAY_OF_YEAR) < 0) {
            adjust = -1;
        }

        painter.setAge(now.get(Calendar.YEAR) - birth.get(Calendar.YEAR) + adjust);
    }
}
