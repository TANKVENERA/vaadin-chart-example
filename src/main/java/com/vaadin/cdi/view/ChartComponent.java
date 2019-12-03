package com.vaadin.cdi.view;

import com.vaadin.cdi.model.Employee;
import com.vaadin.cdi.service.Service;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.*;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

import javax.inject.Inject;
import java.util.*;

/**
 * User: M.Belski@sam-solutions.com
 * Date: 29.11.2019
 */


public class ChartComponent extends HorizontalLayout {

    @Inject
    public ChartComponent(final Service service) {
        Set<Employee> employees = service.getAll();
        int emplCount = employees.size();
        List<String> langs = new ArrayList<>();
        List<String> stacks = new ArrayList<>();
        service.getAll().forEach(e -> {
            langs.addAll(e.getLanguage());
            stacks.addAll(e.getTechnology());
        });
        setWidth("100%");
        add(getChart(langs, emplCount), getChart(stacks, emplCount));
    }

    private Chart getChart(List<String> items, final int emplCount) {
        Chart chart = new Chart();
        chart.getConfiguration().getChart().setType(ChartType.PIE);

        Configuration conf = chart.getConfiguration();
        DataSeries series = new DataSeries();
        Tooltip tooltip = new Tooltip();
        tooltip.setValueDecimals(1);
        Set<String> set = new HashSet<>(items);
        set.forEach(item -> series.add(new DataSeriesItem(item + " %: ", (double) Collections.frequency(items, item) / emplCount * 100)));
        conf.setTooltip(tooltip);
        conf.setSeries(series);
        return chart;
    }

}
