package com.vaadin.cdi.service;
import com.vaadin.cdi.model.Employee;
import com.vaadin.cdi.util.RandomEmployeeGenerator;
import java.util.Set;

/**
 * User: M.Belski@sam-solutions.com
 * Date: 27.11.2019
 */

public class ServiceImpl implements Service {

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
