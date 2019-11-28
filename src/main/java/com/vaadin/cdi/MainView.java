package com.vaadin.cdi;

import com.vaadin.cdi.model.Employee;
import com.vaadin.cdi.service.Service;
import com.vaadin.cdi.service.ServiceImpl;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.*;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.details.DetailsVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.function.ValueProvider;
import com.vaadin.flow.router.Route;

import java.awt.*;
import java.util.Set;

/**
 * User: M.Belski@sam-solutions.com
 * Date: 27.11.2019
 */

@Route
public class MainView extends VerticalLayout {

        Grid<Employee> grid;
        private Service service = new ServiceImpl();

        public MainView() {
                Chart chart = new Chart();
                chart.getConfiguration().getChart().setType(ChartType.PIE);
                Configuration conf = chart.getConfiguration();
                DataSeries series = new DataSeries();
                series.add(new DataSeriesItem("English", 1));
                series.add(new DataSeriesItem("Russian", 2));
                conf.setSeries(series);
                this.grid = new Grid<>(Employee.class);
                grid.setItems(service.getAll());
                grid.setColumns("name");
                grid.addComponentColumn(e -> getItems(e.getLanguage())).setHeader("Languages");
                grid.addComponentColumn(e -> getItems(e.getTechnology())).setHeader("Stack");
                add(grid, chart);
        }

        public static HorizontalLayout getItems (Set<String> set) {
            HorizontalLayout horizontalLayout = new HorizontalLayout();
            set.forEach(l -> horizontalLayout.add(new Label(l)));
            return horizontalLayout;
        }



}
