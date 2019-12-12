package com.vaadin.cdi.view;

import javax.inject.Qualifier;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * User: M.Belski@sam-solutions.com
 * Date: 12.12.2019
 */


@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
public @interface ViewType {

    Type value();

    enum Type {
        MAIN, CHART, FORM, TABLE
    }
}
