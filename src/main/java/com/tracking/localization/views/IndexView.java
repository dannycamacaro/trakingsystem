package com.tracking.localization.views;

import com.tracking.localization.controllers.IntermediaryController;
import com.tracking.localization.controllers.PackageController;
import com.tracking.localization.controllers.TracerController;
import com.tracking.localization.entitys.TraceEntity;
import com.tracking.localization.views.intermediary.RegisterIntermediaryView;
import com.tracking.localization.views.trace.ModalIntermediaryView;
import com.tracking.localization.views.trace.ModalTraceView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Route("")
public class IndexView extends VerticalLayout {
//    String mainImage = "https://www.servientrega.com/wps/wcm/connect/fc38b1bc-70eb-44f2-a286-a2f635ae79d8/BANNER-SERVIENTREGA-PERSONAS.jpg?MOD=AJPERES&CACHEID=ROOTWORKSPACE-fc38b1bc-70eb-44f2-a286-a2f635ae79d8-nryV33X";
    String mainImage = "https://image.freepik.com/vector-gratis/seguimiento-entregas-servicio-entrega-envio-iconos_186930-455.jpg";

    @Autowired
    TracerController tracerController;
    @Autowired
    IntermediaryController intermediaryController;
    @Autowired
    PackageController packageController;

    public IndexView() {
        this.setSizeFull();
        this.setJustifyContentMode(JustifyContentMode.CENTER);

        RouterLink loginButton = new RouterLink("Empleados y Socios", TypeEmployeeView.class);

        HorizontalLayout companyButtons = new HorizontalLayout();
        companyButtons.add(loginButton);
        companyButtons.setAlignItems(Alignment.END);
        Image image = new Image(mainImage, "IMAGE");
        image.setWidth("100%");
        image.setHeight("80%");
        HorizontalLayout bottonPanel = getBottonPanel();

        add(companyButtons, image, bottonPanel);
        this.setAlignItems(Alignment.CENTER);
    }

    private HorizontalLayout getBottonPanel() {
        HorizontalLayout bottonPanel = new HorizontalLayout();
        Label consultGuide = new Label("Rastrea tu paquete");
        NumberField guideField = new NumberField();
        guideField.setWidth("300px");
        guideField.setPlaceholder("Numero de guia");
        Button findShipping = new Button("Buscar Envio");
        findShipping.addClickListener(buttonClickEvent -> {
            if (guideField.isEmpty()) {
                Notification.show("El campo Numero de guia se encuentra vacio", 2000, Notification.Position.MIDDLE);
            } else {

                List<TraceEntity> traceList = tracerController.findAllByPackageId(guideField.getValue().longValue());
                if (traceList.isEmpty()) {
                    Notification.show("El Numero de guia no se encuentra en registrado", 2000, Notification.Position.MIDDLE);
                } else {
                    Dialog dialog = new Dialog();
                    ModalTraceView modalTraceView = new ModalTraceView(dialog, traceList);
                    dialog.add(modalTraceView);
                    dialog.open();
                }
            }
        });
        bottonPanel.add(consultGuide, guideField, findShipping);

        bottonPanel.setAlignSelf(Alignment.CENTER, consultGuide, guideField, findShipping);
        return bottonPanel;
    }
}
