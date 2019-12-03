package com.vaadin.cdi;


import com.vaadin.cdi.view.ChartComponent;
import com.vaadin.cdi.view.FormComponent;
import com.vaadin.cdi.view.TableComponent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
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
@Push
public class MainView extends VerticalLayout {

        @Inject
        public MainView(final TableComponent tableComponent,
                        final ChartComponent chartComponent,
                        final FormComponent formComponent) {
            Button button = new Button("Refresh data");
            button.addClickListener((e)-> UI.getCurrent().getPage().reload());
            add(button, tableComponent, formComponent, chartComponent);
        }
}
