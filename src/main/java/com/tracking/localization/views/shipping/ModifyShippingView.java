package com.tracking.localization.views.shipping;

import com.tracking.localization.controllers.PackageController;
import com.tracking.localization.entitys.PackageEntity;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Route
public class ModifyShippingView extends VerticalLayout {
    private final int FIVE_SECOND = 5000;

    FormLayout senderLayout = new FormLayout();
    FormLayout receptorLayout = new FormLayout();
    HorizontalLayout buttonsLayout = new HorizontalLayout();
    HorizontalLayout mainLayout = new HorizontalLayout();
    ComboBox tipoDocumento = new ComboBox("Tipo de Documento");
    TextField documento = new TextField("Numero de documento");
    TextField nombreCompleto = new TextField("Nombre completo");
    TextField emailSender = new TextField("Email de remitente");
    TextField phoneSender = new TextField("Telefono de remitente");
    TextField producto = new TextField("Producto enviado");
    TextField peso = new TextField("Peso del producto");
    TextField valorDeclarado = new TextField("Valor de producto");
    TextField direccionEnvio = new TextField("Direccion de envio");
    ComboBox receptorTipoDocumento = new ComboBox("Tipo de Documento");
    TextField receptorDocumento = new TextField("Numero de documento");
    TextField receptorNombreCompleto = new TextField("Nombre completo");
    TextField receptorEmailSender = new TextField("Email");
    TextField receptorPhoneSender = new TextField("Telefono");
    Label sendData = new Label("Datos de Envio");
    Label reciverData = new Label("Datos de Receptor");

    public ModifyShippingView(Dialog dialog, PackageController packageController, Optional<PackageEntity> packageEntity) {

        tipoDocumento.setItems(typesDocuments());
        receptorTipoDocumento.setItems(typesDocuments());

        tipoDocumento.setValue(packageEntity.get().getSenderTypeDocument());
        documento.setValue(packageEntity.get().getSenderDocument());
        nombreCompleto.setValue(packageEntity.get().getSenderCompleteName());
        emailSender.setValue(packageEntity.get().getSenderEmail());
        phoneSender.setValue(packageEntity.get().getReciverPhone());
        producto.setValue(packageEntity.get().getSenderProduct());
        peso.setValue(packageEntity.get().getProductValue());
        valorDeclarado.setValue(packageEntity.get().getProductValue());
        direccionEnvio.setValue(packageEntity.get().getTargetAddress());
        receptorTipoDocumento.setValue(packageEntity.get().getReciverTypeDocument());
        receptorDocumento.setValue(packageEntity.get().getReciverDocument());
        receptorNombreCompleto.setValue(packageEntity.get().getReciverCompleteName());
        receptorEmailSender.setValue(packageEntity.get().getReciverEmail());
        receptorPhoneSender.setValue(packageEntity.get().getReciverPhone());

        senderLayout.add(sendData,
                tipoDocumento,
                documento,
                nombreCompleto,
                emailSender,
                phoneSender,
                producto,
                peso,
                valorDeclarado,
                direccionEnvio);
        senderLayout.setWidth("400px");

        receptorLayout.add(reciverData,
                receptorTipoDocumento,
                receptorDocumento,
                receptorNombreCompleto,
                receptorEmailSender,
                receptorPhoneSender);
        receptorLayout.setWidth("400px");

        mainLayout.add(senderLayout,receptorLayout);

        Button save = new Button("Guardar");
        save.addClickListener(buttonClickEvent -> {
            getEntity(packageEntity);

            packageController.update(packageEntity.get());
            Notification.show("Guia numero : " + packageEntity.get().getId(), FIVE_SECOND, Notification.Position.MIDDLE);
            dialog.close();
        });

        Button cancel = new Button("Cancelar");
        cancel.addClickListener(buttonClickEvent -> {
            dialog.close();
        });

        buttonsLayout.add(save, cancel);

        buttonsLayout.setAlignItems(Alignment.CENTER);
        this.add(mainLayout, buttonsLayout);
        this.setAlignItems(Alignment.CENTER);
        this.setWidth("800px");
    }


    private void getEntity(Optional<PackageEntity> packageEntity) {
        packageEntity.get().setSenderTypeDocument(tipoDocumento.getValue().toString());
        packageEntity.get().setSenderDocument(documento.getValue());
        packageEntity.get().setSenderCompleteName(nombreCompleto.getValue());
        packageEntity.get().setSenderEmail(emailSender.getValue());
        packageEntity.get().setSenderPhone(phoneSender.getValue());
        packageEntity.get().setSenderProduct(producto.getValue());
        packageEntity.get().setProductWeight(peso.getValue());
        packageEntity.get().setProductValue(valorDeclarado.getValue());
        packageEntity.get().setTargetAddress(direccionEnvio.getValue());
        packageEntity.get().setReciverTypeDocument(receptorTipoDocumento.getValue().toString());
        packageEntity.get().setReciverDocument(receptorDocumento.getValue());
        packageEntity.get().setReciverCompleteName(receptorNombreCompleto.getValue());
        packageEntity.get().setReciverEmail(receptorEmailSender.getValue());
        packageEntity.get().setReciverPhone(receptorPhoneSender.getValue());

    }

    private Collection typesDocuments() {
        ArrayList<String> typeDocuments = new ArrayList<>();
        typeDocuments.add("Cedula");
        typeDocuments.add("Pasaporte");
        typeDocuments.add("Rif");
        return typeDocuments;
    }
}
