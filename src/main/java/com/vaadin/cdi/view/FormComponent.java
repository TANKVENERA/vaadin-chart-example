package com.vaadin.cdi.view;

import com.vaadin.cdi.model.Employee;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.*;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.Binder.Binding;
import com.vaadin.flow.data.validator.StringLengthValidator;
import com.vaadin.flow.data.value.ValueChangeMode;

import javax.inject.Inject;
import java.awt.*;


/**
 * User: M.Belski@sam-solutions.com
 * Date: 29.11.2019
 */

//Todo need to finish implementation
public class FormComponent extends HorizontalLayout {

    @Inject
    public FormComponent() {
        FormLayout formLayout = new FormLayout();
        TextField name = new TextField("", "Name");
        CheckboxGroup<String> language = new CheckboxGroup<>();
        language.setItems("English", "Russian", "Chinese", "Russian", "German");
        CheckboxGroup<String> technology = new CheckboxGroup<>();
        language.setItems("English", "Russian", "Chinese", "French", "German");
        technology.setItems("Java", "Php", "Ruby", "Javascript", "C++");
        formLayout.addFormItem(name, "");
        formLayout.addFormItem(technology, "");
        formLayout.addFormItem(language, "");
        Employee createdEmployee = new Employee();
        Binder<Employee> binder = new Binder<>();
        Button save = new Button("Save");

        add(formLayout, save);
        binder.forField(name).withValidator(new StringLengthValidator("Set employee name!", 1, null))
                .bind(Employee::getName, Employee::setName);
        binder.forField(language).withValidator(e -> e.size() > 0, "At least one language is needed!" ).bind(Employee::getLanguage, Employee::setLanguage);
        binder.forField(technology).withValidator(e -> e.size() > 0, "At least one technology is needed!" ).bind(Employee::getTechnology, Employee::setTechnology);
        save.addClickListener(e -> {
            if (binder.writeBeanIfValid(createdEmployee)) {
                System.out.println(createdEmployee.getLanguage() + "LLLLL" + createdEmployee.getTechnology());
            } else {

            }
        });

    }
}
