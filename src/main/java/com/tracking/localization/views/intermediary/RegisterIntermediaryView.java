package com.tracking.localization.views.intermediary;

import com.tracking.localization.controllers.IntermediaryController;
import com.tracking.localization.entitys.IntermediaryEntity;
import com.tracking.localization.views.MenuView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route
public class RegisterIntermediaryView extends VerticalLayout {
    @Autowired
    IntermediaryController intermediaryController;

    private final int TWO_SECOND = 2000;


    TextField completeName = new TextField("Nombre Completo");
    EmailField email = new EmailField("Email");
    TextField phone = new TextField("Telefono");


    public RegisterIntermediaryView() {
        this.setWidthFull();
        this.setHeightFull();
        this.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);


        HorizontalLayout buttonsLayout = new HorizontalLayout();

        Button save = new Button("Guardar");
        save.addClickListener(buttonClickEvent -> {
            IntermediaryEntity intermediaryEntity = new IntermediaryEntity();
            intermediaryEntity.setCompleteName(completeName.getValue());
            intermediaryEntity.setEmail(email.getValue());
            intermediaryEntity.setPhoneNumber(phone.getValue());

            if (validateData(intermediaryEntity)) {
                intermediaryController.save(intermediaryEntity);
                Notification.show("Sus datos se han guardado exitosamente....", TWO_SECOND, Notification.Position.MIDDLE);
            } else {
                Notification.show("Debe verificar sus datos....", TWO_SECOND, Notification.Position.MIDDLE);
            }
        });

        Button cancel = new Button("Cancelar");
        cancel.addClickListener(buttonClickEvent -> {
            UI.getCurrent().navigate(MenuView.class);
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
