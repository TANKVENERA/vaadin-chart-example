package com.vaadin.cdi.view;


import com.vaadin.cdi.annotation.NormalUIScoped;
import com.vaadin.cdi.annotation.UIScoped;
import com.vaadin.cdi.annotation.VaadinSessionScoped;
import com.vaadin.cdi.model.Employee;
import com.vaadin.cdi.service.Service;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

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
        grid.addComponentColumn(e -> getItems(e.getLanguage())).setHeader("Languages");
        grid.addComponentColumn(e -> getItems(e.getTechnology())).setHeader("Stack");
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
