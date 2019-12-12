package com.vaadin.cdi.view;

import com.vaadin.cdi.annotation.UIScoped;
import com.vaadin.cdi.model.Employee;
import com.vaadin.cdi.service.Service;
import com.vaadin.cdi.util.FireEventBean;
import com.vaadin.cdi.util.StaticData;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.validator.StringLengthValidator;
import org.slf4j.Logger;

import javax.enterprise.event.Observes;
import javax.inject.Inject;


/**
 * User: M.Belski@sam-solutions.com
 * Date: 29.11.2019
 */

@UIScoped
public class FormComponent extends HorizontalLayout {

    @Inject
    private Logger logger;

    @Inject
    public FormComponent(final Service service, final FireEventBean fireEventBean) {
        TextField name = new TextField("", "Name");
        CheckboxGroup<String> language = new CheckboxGroup<>();
        language.setItems(StaticData.languages);
        CheckboxGroup<String> technology = new CheckboxGroup<>();
        technology.setItems(StaticData.technologies);
        Button save = new Button("Save");
        save.getStyle().set("cursor", "pointer");
        Binder<Employee> binder = new Binder<>();
        add(name, language, technology, save);
        binder.forField(name).withValidator(new StringLengthValidator("Employee name should not be empty!", 1, null))
                .bind(Employee::getName, Employee::setName);
        binder.forField(language).withValidator(e -> e.size() > 0, "At least one language is needed!" ).bind(Employee::getLanguage, Employee::setLanguage);
        binder.forField(technology).withValidator(e -> e.size() > 0, "At least one technology is needed!" ).bind(Employee::getTechnology, Employee::setTechnology);
        save.addClickListener(e -> {
            Employee createdEmployee = new Employee();
            if (binder.writeBeanIfValid(createdEmployee)) {
                service.addEmployee(createdEmployee);
                fireEventBean.fireEvent(createdEmployee.getName() + " was saved", ViewType.Type.FORM);
                binder.readBean(null);
            } else {
                fireEventBean.fireEvent("Fix data and retry!", ViewType.Type.FORM);
            }
        });
    }

    private void logInfo (@Observes @ViewType(ViewType.Type.FORM) String msg) {
        logger.info(msg);
        Notification.show(msg);
    }
}
