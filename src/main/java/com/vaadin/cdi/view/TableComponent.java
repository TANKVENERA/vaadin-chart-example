package com.vaadin.cdi.view;


import com.vaadin.cdi.annotation.NormalUIScoped;
import com.vaadin.cdi.annotation.UIScoped;
import com.vaadin.cdi.annotation.VaadinSessionScoped;
import com.vaadin.cdi.model.Employee;
import com.vaadin.cdi.service.Service;
import com.vaadin.cdi.util.StaticData;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.renderer.Renderer;
import com.vaadin.flow.data.selection.SingleSelect;
import javafx.scene.control.ComboBox;
import org.vaadin.gatanaso.MultiselectComboBox;

import javax.enterprise.context.SessionScoped;
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
    public TableComponent(final Service service) {
        grid = new Grid<>(Employee.class);
        Set<Employee> employees = service.getAll();
        grid.setItems(employees);
        grid.setColumns("name");
        grid.setItemDetailsRenderer(new ComponentRenderer<>(person -> {
            HorizontalLayout horizontalLayout = new HorizontalLayout();
            TextField nameField = new TextField("", person.getName(), "");
            Div nameFieldDiv = new Div();
            Div langsDiv = new Div();
            Div stackDiv = new Div();
            nameFieldDiv.setWidth("160px");
            langsDiv.setWidth("420px");
            stackDiv.setWidth("420px");
            MultiselectComboBox<String > langs = new MultiselectComboBox<>();
            langsDiv.add(langs);
            MultiselectComboBox<String > stack = new MultiselectComboBox<>();
            stackDiv.add(stack);
            langs.setItems(StaticData.languages);
            langs.setValue(person.getLanguage());
            stack.setItems(StaticData.technologies);
            stack.setValue(person.getTechnology());
            Button editBtn = new Button("Update", event -> {

                person.setName(nameField.getValue());
                person.setLanguage(langs.getSelectedItems());
                person.setTechnology(stack.getSelectedItems());
                grid.getDataProvider().refreshAll();
            });
            horizontalLayout.add(nameField, langsDiv, stackDiv, editBtn);
            return horizontalLayout;
        }));
        grid.addComponentColumn(e -> getItems(e.getLanguage())).setHeader("Languages").setWidth("400px");
        grid.addComponentColumn(e -> getItems(e.getTechnology())).setHeader("Stack").setWidth("400px");
        grid.addComponentColumn(e -> new Button("Remove", event -> {
                service.delete(e);
                grid.getDataProvider().refreshAll();
        }));
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
}
