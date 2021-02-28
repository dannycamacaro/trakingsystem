package com.tracking.localization.views.trace;

import com.tracking.localization.controllers.IntermediaryController;
import com.tracking.localization.controllers.PackageController;
import com.tracking.localization.controllers.TracerController;
import com.tracking.localization.entitys.IntermediaryEntity;
import com.tracking.localization.entitys.PackageEntity;
import com.tracking.localization.entitys.TraceEntity;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Route
public class ModalIntermediaryView extends VerticalLayout {
    IntermediaryController intermediaryController;
    PackageController packageController;
    TracerController tracerController;

    public static final int TWO_SECOND = 2000;

    public ModalIntermediaryView(Dialog dialog, IntermediaryController intermediaryController,
                                 PackageController packageController, TracerController tracerController) {
        this.intermediaryController = intermediaryController;
        this.packageController = packageController;
        this.tracerController = tracerController;


        this.setSizeFull();
        TextField intermediaryCode = new TextField("Email del intermediario");
        intermediaryCode.isRequired();
        intermediaryCode.isPreventInvalidInput();
        Button validateIntermediary = new Button("Validar Codigo");
        Button saveLocation = new Button("Guardar Ubicacion");
        saveLocation.setVisible(false);
        Button findProduct = new Button("Buscar Producto");

        findProduct.setVisible(false);

        TextField productCode = new TextField("Codigo del producto");
        productCode.setVisible(Boolean.FALSE);

        TextField addLocation = new TextField("Agregar ubicacion");
        addLocation.setVisible(Boolean.FALSE);


        validateIntermediary.addClickListener(buttonClickEvent -> {
            IntermediaryEntity intermediaryEntity = intermediaryController.findByEmail(intermediaryCode.getValue());
            if (intermediaryEntity != null) {
                Notification.show("Bienvenido : " + intermediaryEntity.getCompleteName(),
                        500, Notification.Position.MIDDLE);
                productCode.setVisible(true);
                findProduct.setVisible(true);
                validateIntermediary.setVisible(false);
                intermediaryCode.setEnabled(false);
            } else {
                Notification.show("El codigo de intermediario : " + intermediaryCode.getValue() + " no se encuentra registrado",
                        TWO_SECOND, Notification.Position.MIDDLE);
            }
        });


        findProduct.addClickListener(buttonClickEvent -> {
            Optional<PackageEntity> packageEntity = packageController.findById(Long.valueOf(productCode.getValue()));
            if (packageEntity.get() != null) {
                Notification.show("Producto encontrado", 500, Notification.Position.MIDDLE);
                addLocation.setVisible(true);
                saveLocation.setVisible(true);
                findProduct.setVisible(false);
                productCode.setEnabled(false);
            } else {
                Notification.show("El codigo de producto no fue encontrado",
                        TWO_SECOND, Notification.Position.MIDDLE);
            }
        });


        saveLocation.addClickListener(buttonClickEvent -> {
            if (!intermediaryCode.isEmpty() && !productCode.isEmpty() && !addLocation.isEmpty()) {
                TraceEntity traceEntity = new TraceEntity();
                traceEntity.setProductId(Long.valueOf(productCode.getValue()));
                traceEntity.setIntermediary(intermediaryCode.getValue());
                traceEntity.setLocation(addLocation.getValue());
                tracerController.save(traceEntity);
                Notification.show("Registrada la ubicacion " + addLocation.getValue() + " del producto " + productCode.getValue()
                                + "por el agente " + intermediaryCode.getValue(),
                        TWO_SECOND, Notification.Position.MIDDLE);
                addLocation.setEnabled(false);
                saveLocation.setVisible(false);
            } else {
                Notification.show("Debe verificar los datos anteriores",
                        TWO_SECOND, Notification.Position.MIDDLE);
            }
        });


        Button close = new Button("Cerrar");
        close.addClickListener(buttonClickEvent -> {
            dialog.close();
        });


        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.add(validateIntermediary);
        add(intermediaryCode, buttonLayout, productCode, findProduct, addLocation, saveLocation, close);
        this.setAlignItems(Alignment.CENTER);
    }
}
