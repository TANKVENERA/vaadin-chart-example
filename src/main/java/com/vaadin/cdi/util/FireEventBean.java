package com.vaadin.cdi.util;

import com.vaadin.cdi.view.ViewType;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;

/**
 * User: M.Belski@sam-solutions.com
 * Date: 12.12.2019
 */

@ApplicationScoped
public class FireEventBean {

    @Inject
    @ViewType(ViewType.Type.MAIN)
    private Event<String> mainEvent;

    @Inject
    @ViewType(ViewType.Type.FORM)
    private Event<String> formEvent;

    @Inject
    @ViewType(ViewType.Type.CHART)
    private Event<String> chartEvent;

    @Inject
    @ViewType(ViewType.Type.TABLE)
    private Event<String> tableEvent;

    public void fireEvent (String msg, ViewType.Type type) {
        switch (type) {
            case MAIN: mainEvent.fire(msg);
                break;
            case TABLE: tableEvent.fire(msg);
                break;
            case CHART: chartEvent.fire(msg);
                break;
            case FORM: formEvent.fire(msg);
                break;

        }
    }

}
