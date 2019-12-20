package com.vaadin.cdi.view;

/**
 * User: M.Belski@sam-solutions.com
 * Date: 17.12.2019
 */

import com.vaadin.cdi.model.Employee;
import com.vaadin.flow.templatemodel.TemplateModel;

import java.util.List;
import java.util.Set;

/**
 * Model for the template.
 */
public interface HelloWorldModel extends TemplateModel {

     void setHostProperty(String propertyValue);
     String getHostProperty();
     void setItems(List<String> items);
     List<String> getItems();

}
