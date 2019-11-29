package com.vaadin.cdi;


import com.vaadin.cdi.view.ChartComponent;
import com.vaadin.cdi.view.FormComponent;
import com.vaadin.cdi.view.TableComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.router.Route;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

/**
 * User: M.Belski@sam-solutions.com
 * Date: 27.11.2019
 */

@Route
public class MainView extends VerticalLayout {

        @Inject
        public MainView(final TableComponent tableComponent,
                        final ChartComponent chartComponent,
                        final FormComponent formComponent) {
               add(tableComponent, formComponent, chartComponent );
        }
}
