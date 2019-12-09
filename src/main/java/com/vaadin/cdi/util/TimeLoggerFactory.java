package com.vaadin.cdi.util;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * User: M.Belski@sam-solutions.com
 * Date: 09.12.2019
 */

@ApplicationScoped
public class TimeLoggerFactory {

    @Produces
    public TimeLogger getTimeLogger() {
        return new TimeLogger(new SimpleDateFormat("HH:mm:ss"), Calendar.getInstance());
    }
}
