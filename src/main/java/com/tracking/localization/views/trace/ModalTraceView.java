package com.tracking.localization.views.trace;

import com.tracking.localization.Services.TracerServices;
import com.tracking.localization.controllers.TracerController;
import com.tracking.localization.entitys.TraceEntity;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class ModalTraceView extends VerticalLayout {
    private final int TWO_SECOND = 2000;
    @Autowired
    TracerController tracerServices;

    public ModalTraceView(Dialog dialog, List<TraceEntity> traces) {
        this.setWidthFull();
        this.setHeightFull();
        this.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);


        Label title = new Label("Identificador del envio: " + traces.get(0).getId());
        this.add(title);
        Label line = new Label("_____________________________________________________");
        this.add(line);


        ArrayList<String> proccess = new ArrayList<>();
        for (TraceEntity trace : traces) {
            proccess.add(trace.getLocation());
        }

        for (String detail : proccess) {
            Label label = new Label(detail);
            this.add(label);
        }

        Button cancel = new Button("Cerrar");
        cancel.addClickListener(buttonClickEvent -> {
            dialog.close();
        });


        this.add(cancel);
        this.setAlignItems(FlexComponent.Alignment.CENTER);
    }


}
