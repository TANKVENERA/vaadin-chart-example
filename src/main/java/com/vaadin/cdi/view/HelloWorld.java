package com.vaadin.cdi.view;

import com.vaadin.cdi.model.Employee;
import com.vaadin.cdi.service.Service;
import com.vaadin.cdi.util.StaticData;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.templatemodel.Encode;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * User: M.Belski@sam-solutions.com
 * Date: 17.12.2019
 */

@Tag("hello-world")
@NpmPackage(value = "@polymer/paper-slider", version = "3.0.1")
@NpmPackage(value = "@polymer/iron-swipeable-container", version = "3.0.1")
@NpmPackage(value = "@polymer/paper-material", version = "3.0.1")
@NpmPackage(value = "@polymer/paper-card", version = "3.0.1")
@NpmPackage(value = "@polymer/paper-dialog", version = "3.0.1")
@NpmPackage(value = "@polymer/paper-button", version = "3.0.1")
@NpmPackage(value = "@polymer/iron-list", version = "3.1.0")
@JsModule("./src/hello-world.js")
public class HelloWorld extends PolymerTemplate<HelloWorldModel> {

    public HelloWorld() {
        List<String> names = new ArrayList<>();
        for (int i = 1; i <=6; i++) {
            names.add("User: " + i);
        }
        setId("template");
        getModel().setItems(names);
        getElement().addPropertyChangeListener("hostProperty", event -> System.out
                .println("LOLOLOL " + getModel().getHostProperty()));
        getElement().addPropertyChangeListener("items", event -> System.out
                .println("AAAAA " + getModel().getItems()));
        System.out.println("DDDDD " + getModel().getItems());
        System.out.println("SSSS " + getModel().getHostProperty());
        Button btn = new Button("submit");
        btn.addClickListener(e-> System.out.println("????? " + getModel().getHostProperty()));
    }

}
