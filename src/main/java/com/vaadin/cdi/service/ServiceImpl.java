package com.vaadin.cdi.service;

import com.vaadin.cdi.model.Employee;
import com.vaadin.cdi.util.RandomEmployeeGenerator;
import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.util.Set;

/**
 * User: M.Belski@sam-solutions.com
 * Date: 27.11.2019
 */

@ApplicationScoped
public class ServiceImpl implements Service, Serializable {

    public ServiceImpl() {
    }

    @Override
    public Set<Employee> getAll() {
        return getData();
    }

    @Override
    public void delete(Employee e) {
        getData().remove(e);
    }

    @Override
    public void addEmployee(Employee e) {
        getData().add(e);
    }

    private static Set<Employee> getData() {
        return RandomEmployeeGenerator.generate();
    }
}
