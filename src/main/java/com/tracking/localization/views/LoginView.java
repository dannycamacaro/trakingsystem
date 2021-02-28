package com.tracking.localization.views;

import com.tracking.localization.Services.UserServices;
import com.tracking.localization.entitys.UserEntity;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;


@Route
public class LoginView extends VerticalLayout {
    public static final int TWO_SECOND = 2000;
    TextField loginTextField = new TextField("Ingrese su usuario");
    PasswordField passwordField = new PasswordField("Ingrese su password");
    Button loginButton = new Button("Entrar");
    Button cancelButton = new Button("Cancelar");
    Label title = new Label("Iniciar Sesion");
    HorizontalLayout buttonsLayout = new HorizontalLayout();
    VerticalLayout mainLayout = new VerticalLayout();


    public LoginView(Dialog dialog, UserServices userServices) {
        this.setSizeFull();
        mainLayout.setSizeFull();

        loginTextField.isRequired();
        loginTextField.isAutofocus();
        passwordField.isRequired();

        loginTextField.setWidth("200px");
        passwordField.setWidth("200px");

        loginButton.addClickListener(buttonClickEvent -> {
            if (loginTextField.isEmpty() || passwordField.isEmpty() || !checkUser(loginTextField, passwordField, userServices)) {
                checkYourInformation();
                dialog.close();
            } else {
                UI.getCurrent().navigate(MenuView.class);
                dialog.close();
            }
        });

        cancelButton.addClickListener(buttonClickEvent -> {
            dialog.close();
        });

        buttonsLayout.add(loginButton, cancelButton);
        mainLayout.add(title,loginTextField, passwordField, buttonsLayout);
        mainLayout.setAlignItems(Alignment.CENTER);

        this.add(mainLayout);
        this.setAlignItems(Alignment.CENTER);
    }

    private boolean checkUser(TextField loginTextField, PasswordField passwordField, UserServices userServices) {
        UserEntity user = findUser(loginTextField.getValue(), passwordField.getValue(), userServices);
        return user != null;
    }

    private UserEntity findUser(String login, String password, UserServices userServices) {
        return userServices.findByLoginAndPassword(login, password);
    }

    private void checkYourInformation() {
        Notification.show("Por favor verificar sus datos ", TWO_SECOND, Notification.Position.MIDDLE);
    }
}
