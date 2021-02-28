package com.tracking.localization.views.trace;

import com.tracking.localization.views.IndexView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route

public class ProductTraceView extends VerticalLayout {
    public static int TWO_SECOND = 2000;

    public ProductTraceView() {
        TextField productGuide = new TextField("Numero guia");
        Button consultGuide = new Button("Consultar Guia");
        consultGuide.addClickListener(buttonClickEvent -> {
            Notification.show("En construccion...",
                    TWO_SECOND, Notification.Position.MIDDLE);
        });

        Button back = new Button("Atras");
        back.addClickListener(buttonClickEvent -> {
            UI.getCurrent().navigate(IndexView.class);
        });

        HorizontalLayout buttonsLayout = new HorizontalLayout();
        buttonsLayout.add(consultGuide, back);
        add(productGuide, buttonsLayout);

        this.setAlignItems(Alignment.CENTER);

    }
}
