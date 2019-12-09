package com.vaadin.cdi.util;

import com.vaadin.cdi.annotation.NormalUIScoped;
import com.vaadin.cdi.annotation.VaadinSessionScoped;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * User: M.Belski@sam-solutions.com
 * Date: 06.12.2019
 */


public class TimeLogger {

    private SimpleDateFormat simpleDateFormat;
    private Calendar calendar;

    public TimeLogger(SimpleDateFormat simpleDateFormat, Calendar calendar) {
        this.simpleDateFormat = simpleDateFormat;
        this.calendar = calendar;
    }

    public String getTime () {
        return simpleDateFormat.format(calendar.getTime());
    }
}
