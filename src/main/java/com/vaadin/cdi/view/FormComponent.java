package com.vaadin.cdi.view;

import com.vaadin.cdi.MainView;
import com.vaadin.cdi.model.Employee;
import com.vaadin.cdi.service.Service;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.validator.StringLengthValidator;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;

import javax.inject.Inject;


/**
 * User: M.Belski@sam-solutions.com
 * Date: 29.11.2019
 */


public class FormComponent extends HorizontalLayout {

    @Inject
    public FormComponent(final Service service, TableComponent tableComponent) {
        FormLayout formLayout = new FormLayout();
        HorizontalLayout hor = new HorizontalLayout();
        TextField name = new TextField("", "Name");
        CheckboxGroup<String> language = new CheckboxGroup<>();
        language.setItems("English", "Russian", "Chinese", "French", "German");
        CheckboxGroup<String> technology = new CheckboxGroup<>();
        technology.setItems("Java", "Php", "Ruby", "Javascript", "C++");
        Button save = new Button("Save");
        hor.add(name, language, technology, save);
        formLayout.add(hor);
        formLayout.setWidth("2000px");
        Binder<Employee> binder = new Binder<>();
        add(formLayout);
        binder.forField(name).withValidator(new StringLengthValidator("Employee name should not be empty!", 1, null))
                .bind(Employee::getName, Employee::setName);
        binder.forField(language).withValidator(e -> e.size() > 0, "At least one language is needed!" ).bind(Employee::getLanguage, Employee::setLanguage);
        binder.forField(technology).withValidator(e -> e.size() > 0, "At least one technology is needed!" ).bind(Employee::getTechnology, Employee::setTechnology);
        save.addClickListener(e -> {
            Employee createdEmployee = new Employee();
            if (binder.writeBeanIfValid(createdEmployee)) {
                service.addEmployee(createdEmployee);
                Notification.show(createdEmployee.getName() + " was successfully added to employee list!");
                binder.readBean(null);
            } else {
                Notification.show("Fix data and retry!");
            }
        });

    }



}
