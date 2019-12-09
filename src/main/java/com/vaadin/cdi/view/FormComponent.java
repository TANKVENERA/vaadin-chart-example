package com.vaadin.cdi.view;

import com.vaadin.cdi.MainView;
import com.vaadin.cdi.annotation.NormalUIScoped;
import com.vaadin.cdi.annotation.UIScoped;
import com.vaadin.cdi.annotation.VaadinSessionScoped;
import com.vaadin.cdi.model.Employee;
import com.vaadin.cdi.service.Service;
import com.vaadin.cdi.util.StaticData;
import com.vaadin.cdi.util.TimeLogger;
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

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;


/**
 * User: M.Belski@sam-solutions.com
 * Date: 29.11.2019
 */

@UIScoped
public class FormComponent extends HorizontalLayout {



    @Inject
    public FormComponent(final Service service, final TimeLogger timeLogger) {
        FormLayout formLayout = new FormLayout();
        HorizontalLayout hor = new HorizontalLayout();
        TextField name = new TextField("", "Name");
        CheckboxGroup<String> language = new CheckboxGroup<>();
        language.setItems(StaticData.languages);
        CheckboxGroup<String> technology = new CheckboxGroup<>();
        technology.setItems(StaticData.technologies);
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
                Notification.show(createdEmployee.getName() + " added at: " + timeLogger.getTime());
                binder.readBean(null);
            } else {
                Notification.show("Fix data and retry!");
            }
        });
    }



}
