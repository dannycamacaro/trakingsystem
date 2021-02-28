package com.tracking.localization.views.user;

import com.tracking.localization.Services.UserServices;
import com.tracking.localization.entitys.UserEntity;
import com.tracking.localization.views.MenuView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route
public class RegisterUserView extends VerticalLayout {

    @Autowired
    UserServices userServices;
    private final int TWO_SECOND = 2000;

    public RegisterUserView() {
        this.setWidthFull();
        this.setHeightFull();
        this.setJustifyContentMode(JustifyContentMode.CENTER);
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        HorizontalLayout horizontalLayout1 = new HorizontalLayout();
        HorizontalLayout horizontalLayout2 = new HorizontalLayout();
        HorizontalLayout horizontalLayout3 = new HorizontalLayout();
        HorizontalLayout horizontalLayout4 = new HorizontalLayout();

        TextField loginField = new TextField("Login de Usuario");
        horizontalLayout.add(loginField);

        PasswordField passwordField = new PasswordField("Contraseña");
        PasswordField confirmPasswordField = new PasswordField("Confirmar contraseña");
        horizontalLayout1.add(passwordField, confirmPasswordField);

        TextField nameField = new TextField("Primer Nombre");
        TextField secondNameField = new TextField("Segundo Nombre");
        horizontalLayout2.add(nameField, secondNameField);

        TextField firstLastNameField = new TextField("Primer Apellido");
        TextField secondLastNameField = new TextField("Segundo Apellido");
        horizontalLayout3.add(firstLastNameField, secondLastNameField);

        EmailField emailField = new EmailField("Email");
        TextField phoneField = new TextField("Telefono");
        horizontalLayout4.add(emailField, phoneField);

        HorizontalLayout buttonsLayout = new HorizontalLayout();

        Button save = new Button("Guardar");
        save.addClickListener(buttonClickEvent -> {
            UserEntity userEntity = new UserEntity();
            userEntity.setLogin(loginField.getValue());
            userEntity.setPassword(loginField.getValue());
            userEntity.setRepeatPassword(loginField.getValue());
            userEntity.setFirstName(nameField.getValue());
            userEntity.setSecondName(secondNameField.getValue());
            userEntity.setLastName(firstLastNameField.getValue());
            userEntity.setSecondLastName(secondLastNameField.getValue());
            userEntity.setEmail(emailField.getValue());
            userEntity.setPhone(phoneField.getValue());

            if (validateData(userEntity)) {
                userServices.save(userEntity);
                Notification.show("Sus datos se han guardado exitosamente....", TWO_SECOND, Notification.Position.MIDDLE);
                UI.getCurrent().navigate(MenuView.class);
            } else {
                Notification.show("Debe verificar sus datos....", TWO_SECOND, Notification.Position.MIDDLE);
            }
        });

        Button cancel = new Button("Cancelar");
        cancel.addClickListener(buttonClickEvent -> {
            UI.getCurrent().navigate(MenuView.class);
        });

        buttonsLayout.add(save, cancel);

        this.add(horizontalLayout, horizontalLayout1, horizontalLayout2, horizontalLayout3, horizontalLayout4, buttonsLayout);
        this.setAlignItems(Alignment.CENTER);
    }

    private boolean validateData(UserEntity userEntity) {
        if (!userEntity.getLogin().isEmpty()
                && !userEntity.getPassword().isEmpty()
                && !userEntity.getRepeatPassword().isEmpty()
        ) {
            return true;
        }
        return false;
    }


}
