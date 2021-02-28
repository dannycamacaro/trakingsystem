package com.tracking.localization.views.intermediary;

import com.tracking.localization.controllers.IntermediaryController;
import com.tracking.localization.entitys.IntermediaryEntity;
import com.tracking.localization.views.MenuView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import java.util.Optional;

@Route
public class ModifyIntermediaryView extends VerticalLayout {

    private final int TWO_SECOND = 2000;
    IntermediaryEntity intermediaryEntity;

    TextField completeName = new TextField("Nombre Completo");
    EmailField email = new EmailField("Email");
    TextField phone = new TextField("Telefono");


    public ModifyIntermediaryView(Dialog dialog, IntermediaryController intermediaryController, Optional<IntermediaryEntity> intermediaryEntity) {
        this.setWidthFull();
        this.setHeightFull();
        this.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        this.intermediaryEntity = intermediaryEntity.get();
        completeName.setValue(this.intermediaryEntity.getCompleteName());
        email.setValue(this.intermediaryEntity.getCompleteName());
        phone.setValue(this.intermediaryEntity.getPhoneNumber());

        HorizontalLayout buttonsLayout = new HorizontalLayout();

        Button save = new Button("Guardar");
        save.addClickListener(buttonClickEvent -> {
            this.intermediaryEntity.setCompleteName(completeName.getValue());
            this.intermediaryEntity.setEmail(email.getValue());
            this.intermediaryEntity.setPhoneNumber(phone.getValue());

            if (validateData(this.intermediaryEntity)) {
                intermediaryController.save(this.intermediaryEntity);
                Notification.show("Sus datos se han guardado exitosamente....", TWO_SECOND, Notification.Position.MIDDLE);
                dialog.close();
            } else {
                Notification.show("Debe verificar sus datos....", TWO_SECOND, Notification.Position.MIDDLE);
            }
        });

        Button cancel = new Button("Cancelar");
        cancel.addClickListener(buttonClickEvent -> {
            dialog.close();
        });

        buttonsLayout.add(save, cancel);

        this.add(completeName, email, phone, buttonsLayout);
        this.setAlignItems(FlexComponent.Alignment.CENTER);
    }

    private boolean validateData(IntermediaryEntity intermediaryEntity) {
        if (!intermediaryEntity.getCompleteName().isEmpty()
                && !intermediaryEntity.getEmail().isEmpty()
                && !intermediaryEntity.getPhoneNumber().isEmpty()
        ) {
            return true;
        }
        return false;
    }
}
