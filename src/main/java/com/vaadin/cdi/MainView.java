package com.vaadin.cdi;


import com.vaadin.cdi.annotation.UIScoped;
import com.vaadin.cdi.service.Service;
import com.vaadin.cdi.util.FireEventBean;
import com.vaadin.cdi.util.StaticData;
import com.vaadin.cdi.view.*;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.html.Input;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.ironlist.IronList;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.menubar.MenuBarVariant;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;
import org.slf4j.Logger;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.util.*;
import java.util.function.Predicate;


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
            HorizontalLayout horizontalLayout = new HorizontalLayout();
            MenuBar menuBar = new MenuBar();
            MenuItem item1 = menuBar.addItem(new Icon(VaadinIcon.MENU));
            item1.getElement().setProperty("closeOn", "vaadin-overlay-outside-click");
            Arrays.asList(StaticData.languages).forEach(item -> {
                MenuItem i = item1.getSubMenu().addItem(item, e -> {
                    if (e.getSource().isChecked()) {
                        horizontalLayout.add(new Button(item, VaadinIcon.CLOSE.create()));
                    } else {
                        horizontalLayout.getChildren().forEach(e2 -> {
                            if (e2.getElement().getText().equals(item)) {
                                horizontalLayout.remove(e2);
                            }
                        });
                    }
                });
                i.setCheckable(true);

            });
            horizontalLayout.add(menuBar);
        add(new HelloWorld());
        }

    private void logInfo(@Observes @ViewType(ViewType.Type.MAIN) String msg) {
            logger.info(msg);
            Notification.show(msg);
        }

}
