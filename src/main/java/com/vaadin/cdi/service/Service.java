package com.vaadin.cdi.service;

import com.vaadin.cdi.model.Employee;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import java.util.List;
import java.util.Set;

/**
 * User: M.Belski@sam-solutions.com
 * Date: 27.11.2019
 */

public interface Service {

    Set<Employee> getAll();

    void delete (Employee e);
}
