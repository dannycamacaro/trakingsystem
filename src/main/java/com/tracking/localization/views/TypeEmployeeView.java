package com.tracking.localization.views;

import com.tracking.localization.Services.UserServices;
import com.tracking.localization.controllers.IntermediaryController;
import com.tracking.localization.controllers.PackageController;
import com.tracking.localization.controllers.TracerController;
import com.tracking.localization.views.trace.ModalIntermediaryView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route
public class TypeEmployeeView extends VerticalLayout {
    @Autowired
    TracerController tracerController;
    @Autowired
    IntermediaryController intermediaryController;
    @Autowired
    PackageController packageController;
    @Autowired
    UserServices userServices;


    public TypeEmployeeView() {
        this.setSizeFull();
        HorizontalLayout selectorPanel = new HorizontalLayout();
        VerticalLayout intermediaryLayout = new VerticalLayout();
        VerticalLayout employeeLayout = new VerticalLayout();
        Image employeeImage = new Image("https://www.flaticon.es/premium-icon/icons/svg/374/374267.svg", "Employee");
        Image intermediaryImage = new Image("https://www.flaticon.es/premium-icon/icons/svg/374/374426.svg", "Intermediary");


        employeeImage.addClickListener(imageClickEvent -> {
            Dialog dialog = new Dialog();
            LoginView loginView = new LoginView(dialog,userServices);
            dialog.add(loginView);
            dialog.open();
        });
        employeeImage.setHeight("400px");
        employeeImage.setWidth("300px");
        Label employeeLabel = new Label("Empleados");
        employeeLayout.add(employeeImage, employeeLabel);
        employeeLayout.setAlignItems(Alignment.CENTER);


        intermediaryImage.addClickListener(imageClickEvent -> {
            Dialog dialog = new Dialog();
            ModalIntermediaryView modalTraceView = new ModalIntermediaryView(dialog, intermediaryController, packageController, tracerController);
            dialog.add(modalTraceView);
            dialog.open();
        });
        intermediaryImage.setHeight("400px");
        intermediaryImage.setWidth("300px");
        Label intermediaryLabel = new Label("Intermediario");
        intermediaryLayout.add(intermediaryImage, intermediaryLabel);
        intermediaryLayout.setAlignItems(Alignment.CENTER);


        selectorPanel.add(employeeLayout, intermediaryLayout);
        selectorPanel.setWidth("80%");
        selectorPanel.setHeight("80%");
        selectorPanel.setAlignItems(Alignment.CENTER);

        Button backButton = new Button("Volver Atras");
        backButton.addClickListener(buttonClickEvent -> {
            UI.getCurrent().navigate(IndexView.class);
        });
        backButton.setHeight("50px");
        backButton.setWidth("300px");

        this.add(selectorPanel, backButton);
        this.setAlignItems(Alignment.CENTER);
    }
}
