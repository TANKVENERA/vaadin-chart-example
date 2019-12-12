package com.vaadin.cdi.view;

import com.vaadin.cdi.annotation.UIScoped;
import com.vaadin.cdi.model.Employee;
import com.vaadin.cdi.service.Service;
import com.vaadin.cdi.util.FireEventBean;
import com.vaadin.cdi.util.StaticData;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import org.slf4j.Logger;
import org.vaadin.gatanaso.MultiselectComboBox;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.util.Set;

/**
 * User: M.Belski@sam-solutions.com
 * Date: 29.11.2019
 */

@UIScoped
public class TableComponent extends VerticalLayout {

    private Grid<Employee> grid;
    @Inject
    private Logger logger;

    @Inject
    public TableComponent(final Service service, final FireEventBean fireEventBean) {
        grid = new Grid<>(Employee.class);
        Set<Employee> employees = service.getAll();
        grid.setItems(employees);
        grid.setColumns("name");
        grid.setItemDetailsRenderer(new ComponentRenderer<>(person -> {
            HorizontalLayout horizontalLayout = new HorizontalLayout();
            TextField nameField = new TextField("", person.getName(), "");
            MultiselectComboBox<String > langs = new MultiselectComboBox<>();
            langs.getStyle().set("width", "500px");
            langs.getStyle().set("padding-right", "190px");
            MultiselectComboBox<String > stack = new MultiselectComboBox<>();
            stack.getStyle().set("width", "500px");
            stack.getStyle().set("padding-right", "200px");
            langs.setItems(StaticData.languages);
            langs.setValue(person.getLanguage());
            langs.setClearButtonVisible(true);
            stack.setItems(StaticData.technologies);
            stack.setValue(person.getTechnology());
            stack.setClearButtonVisible(true);
            Button editBtn = new Button("Edit", event -> {
                person.setName(nameField.getValue());
                person.setLanguage(langs.getSelectedItems());
                person.setTechnology(stack.getSelectedItems());
                grid.getDataProvider().refreshAll();
                fireEventBean.fireEvent(person.getName() + " was updated", ViewType.Type.TABLE);
            });
            editBtn.getStyle().set("cursor", "pointer");
            horizontalLayout.add(nameField, langs, stack, editBtn);
            return horizontalLayout;
        }));
        grid.addComponentColumn(employee -> getItems(employee.getLanguage())).setHeader("Languages").setWidth("600px");
        grid.addComponentColumn( employee -> getItems(employee.getTechnology())).setHeader("Stack").setWidth("600px");
        grid.addComponentColumn(person -> {
                Button button = new Button("Remove");
                button.getStyle().set("cursor", "pointer");
                button.addClickListener(event -> {
                    fireEventBean.fireEvent(person.getName() + " was deleted", ViewType.Type.TABLE);
                    service.delete(person);
                    grid.getDataProvider().refreshAll();
                });
                return button;
            });
       add(grid);

    }

    private static HorizontalLayout getItems (Set<String> items) {
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        items.forEach(item -> horizontalLayout.add(new Label(item)));
        return horizontalLayout;
    }

    public Grid<Employee> getGrid() {
        return grid;
    }

    public void setGrid(Grid<Employee> grid) {
        this.grid = grid;
    }

    private void logInfo(@Observes @ViewType(ViewType.Type.TABLE) String msg){
        logger.info(msg);
        Notification.show(msg);
    }
}
