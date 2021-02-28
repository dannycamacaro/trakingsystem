package com.tracking.localization.views.shipping;

import com.tracking.localization.controllers.PackageController;
import com.tracking.localization.entitys.PackageEntity;
import com.tracking.localization.views.MenuView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collection;

@Route
public class RegisterShippingView extends VerticalLayout {
    private final int FIVE_SECOND = 5000;
    @Autowired
    PackageController packageServices;

    FormLayout senderLayout = new FormLayout();
    FormLayout receptorLayout = new FormLayout();

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


    public RegisterShippingView() {
        this.setWidthFull();
        this.setHeightFull();
        this.setJustifyContentMode(JustifyContentMode.CENTER);

        tipoDocumento.setItems(typesDocuments());
        receptorTipoDocumento.setItems(typesDocuments());

        senderLayout.add(tipoDocumento, documento, nombreCompleto, emailSender, phoneSender,producto, peso, valorDeclarado, direccionEnvio);
        senderLayout.setWidth("60%");

        receptorLayout.add(receptorTipoDocumento, receptorDocumento, receptorNombreCompleto,receptorEmailSender, receptorPhoneSender);
        receptorLayout.setWidth("60%");

        HorizontalLayout buttonsLayout = new HorizontalLayout();

        Button save = new Button("Guardar");
        save.addClickListener(buttonClickEvent -> {
            PackageEntity packageEntity = getEntity();
            packageServices.save(packageEntity);
            Notification.show("Guia numero : " + packageEntity.getId(), FIVE_SECOND, Notification.Position.MIDDLE);
        });

        Button cancel = new Button("Cancelar");
        cancel.addClickListener(buttonClickEvent -> {
            UI.getCurrent().navigate(MenuView.class);
        });

        buttonsLayout.add(save, cancel);
        buttonsLayout.setWidth("60%");

        Label sendData = new Label("Datos de Envio");
        sendData.setWidth("60%");

        Label reciverData = new Label("Datos de Receptor");
        reciverData.setWidth("60%");

        buttonsLayout.setAlignItems(Alignment.CENTER);
        this.add(sendData, senderLayout, reciverData, receptorLayout, buttonsLayout);
        this.setHorizontalComponentAlignment(Alignment.CENTER,sendData, senderLayout, reciverData, receptorLayout, buttonsLayout);
        this.setAlignItems(Alignment.CENTER);
    }

    private PackageEntity getEntity() {
        PackageEntity shipping = new PackageEntity();
        shipping.setSenderTypeDocument(tipoDocumento.getValue().toString());
        shipping.setSenderDocument(documento.getValue());
        shipping.setSenderCompleteName(nombreCompleto.getValue());
        shipping.setSenderEmail(emailSender.getValue());
        shipping.setSenderPhone(phoneSender.getValue());
        shipping.setSenderProduct(producto.getValue());
        shipping.setProductWeight(peso.getValue());
        shipping.setProductValue(valorDeclarado.getValue());
        shipping.setTargetAddress(direccionEnvio.getValue());
        shipping.setReciverTypeDocument(receptorTipoDocumento.getValue().toString());
        shipping.setReciverDocument(receptorDocumento.getValue());
        shipping.setReciverCompleteName(receptorNombreCompleto.getValue());
        shipping.setReciverEmail(receptorEmailSender.getValue());
        shipping.setReciverPhone(receptorPhoneSender.getValue());
        return shipping;
    }

    private Collection typesDocuments() {
        ArrayList<String> typeDocuments = new ArrayList<>();
        typeDocuments.add("Cedula");
        typeDocuments.add("Pasaporte");
        typeDocuments.add("Rif");
        return typeDocuments;
    }
}
