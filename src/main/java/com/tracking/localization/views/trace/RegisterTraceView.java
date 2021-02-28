package com.tracking.localization.views.trace;

import com.tracking.localization.views.IndexView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;


@Route
public class RegisterTraceView extends VerticalLayout {

    public RegisterTraceView() {

        TextField product = new TextField("Guia de producto");
        TextField location = new TextField("Ubicacion del producto");

        Button addLocation = new Button("Agregar ubicacion");
        addLocation.addClickListener(buttonClickEvent -> {

        });

        Button back = new Button("Inicio");
        back.addClickListener(buttonClickEvent -> {
            UI.getCurrent().navigate(IndexView.class);
        });

        HorizontalLayout buttonsLayout = new HorizontalLayout();
        buttonsLayout.add(addLocation, back);

        add(product, location, buttonsLayout);
        this.setAlignItems(Alignment.CENTER);
    }
}
