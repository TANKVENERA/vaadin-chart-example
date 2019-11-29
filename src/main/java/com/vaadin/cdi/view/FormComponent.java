package com.vaadin.cdi.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

import javax.inject.Inject;


/**
 * User: M.Belski@sam-solutions.com
 * Date: 29.11.2019
 */

//Todo need to finish implementation
public class FormComponent extends HorizontalLayout {

    @Inject
    public FormComponent() {
        CheckboxGroup<String> comboBox = new CheckboxGroup<>();
        comboBox.setItems("Russian", "English", "Belarussian");
        TextField firstName = new TextField();
        firstName.setPlaceholder("Name");
        Button button = new Button("Add employee");
        add(firstName, comboBox, button);

    }
}
