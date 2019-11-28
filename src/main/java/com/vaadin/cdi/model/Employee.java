package com.vaadin.cdi.model;

import java.util.Set;

/**
 * User: M.Belski@sam-solutions.com
 * Date: 27.11.2019
 */

public class Employee {

    private String name;
    private Set<String> technology;
    private Set<String> language;

    public Employee() {
    }

    public Employee(String name, Set<String> technology, Set<String> language) {
        this.name = name;
        this.technology = technology;
        this.language = language;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getTechnology() {
        return technology;
    }

    public void setTechnology(Set<String> technology) {
        this.technology = technology;
    }

    public Set<String> getLanguage() {
        return language;
    }

    public void setLanguage(Set<String> language) {
        this.language = language;
    }
}
