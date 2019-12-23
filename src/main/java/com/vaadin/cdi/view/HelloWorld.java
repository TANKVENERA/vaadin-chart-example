package com.vaadin.cdi.view;

import com.vaadin.cdi.model.Employee;
import com.vaadin.cdi.service.Service;
import com.vaadin.cdi.util.StaticData;
import com.vaadin.flow.component.ClientCallable;
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
@NpmPackage(value = "@polymer/iron-scroll-threshold", version = "3.0.1")
@JsModule("./src/hello-world.js")
public class HelloWorld extends PolymerTemplate<HelloWorldModel> {

    public HelloWorld() {
        setId("template");
    }

    @ClientCallable
    public void loadMoreData (Integer size) {
        System.out.println("DDDD "  + getModel().getItems());
        for (int i = size + 1; i <=size + 5; i++) {
            getModel().getItems().add("User: " + i);
        }
    }

    @ClientCallable
    public void deleteItem (String item) {
        System.out.println(item + "TERMINA " +  getModel().getItems());
//        getModel().getItems().remove(item);

    }

}
