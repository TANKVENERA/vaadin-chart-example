package com.vaadin.cdi.util;

import com.vaadin.cdi.model.Employee;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * User: M.Belski@sam-solutions.com
 * Date: 27.11.2019
 */

public class RandomEmployeeGenerator {

    private static final String[] names = {"Bob", "Jhon", "Mike", "Bill", "Ann", "Todd", "Steve", "Nick"};
    private static final String[] languages = {"English", "Russian", "German", "Chinese", "French"};
    private static final String[] technologies = {"Java", "Php", "Ruby", "Javascript", "C++"};
    private static Set<Employee> employees;


    public static Set<Employee> generate () {
        if (employees == null) {
            employees = new HashSet<>();
            for (String name : names) {
                final Employee employee = new Employee();
                Set<String> randomLanguages = getRandomSet(languages);
                Set<String> randomTechnologies = getRandomSet(technologies);
                employee.setName(name);
                employee.setLanguage(randomLanguages);
                employee.setTechnology(randomTechnologies);
                employees.add(employee);
            }
            return employees;
        }
        return employees;
    }

    private static Set<String> getRandomSet (String[] array) {
        Set<String> items = new HashSet<>();
        final Random r = new Random();
        for (int i = 0; i <= r.nextInt(array.length); i++) {
            items.add(array[r.nextInt(array.length)]);
        }
        return items;
    }

}