package com.vaadin.cdi;


import com.vaadin.cdi.annotation.UIScoped;
import com.vaadin.cdi.util.FireEventBean;
import com.vaadin.cdi.view.ChartComponent;
import com.vaadin.cdi.view.FormComponent;
import com.vaadin.cdi.view.TableComponent;
import com.vaadin.cdi.view.ViewType;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;
import org.slf4j.Logger;

import javax.enterprise.event.Observes;
import javax.inject.Inject;


/**
 * User: M.Belski@sam-solutions.com
 * Date: 27.11.2019
 */

@Route
@Push
@UIScoped
public class MainView extends VerticalLayout implements RouterLayout {

    @Inject
    private Logger logger;

    @Inject
    public MainView(final TableComponent tableComponent, final ChartComponent chartComponent,
                    final FormComponent formComponent, final FireEventBean fireEventBean) {
            Button btn = new Button("Refresh data");
            Button chartBtn = new Button("Refresh chart");
            btn.addClickListener((e) -> UI.getCurrent().access(() -> {
                tableComponent.getGrid().getDataProvider().refreshAll();
                fireEventBean.fireEvent("Data was refreshed", ViewType.Type.MAIN);
            }));
            chartBtn.addClickListener((e) -> {
                UI.getCurrent().getPage().reload();
                fireEventBean.fireEvent("Chart was refreshed", ViewType.Type.MAIN);
            });
            add(new HorizontalLayout(btn, chartBtn), tableComponent, formComponent, chartComponent);
        }

    private void logInfo(@Observes @ViewType(ViewType.Type.MAIN) String msg) {
            logger.info(msg);
            Notification.show(msg);
        }

}
