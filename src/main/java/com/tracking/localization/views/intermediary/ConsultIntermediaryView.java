package com.tracking.localization.views.intermediary;

import com.tracking.localization.controllers.IntermediaryController;
import com.tracking.localization.entitys.IntermediaryEntity;
import com.tracking.localization.views.MenuView;
import com.tracking.localization.views.trace.ModalIntermediaryView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Route
public class ConsultIntermediaryView extends VerticalLayout {
    IntermediaryController intermediaryController;
    Button back = new Button("Volver al menu");

    @Autowired
    public ConsultIntermediaryView(IntermediaryController intermediaryController) {
        this.setSizeFull();
        this.intermediaryController = intermediaryController;
        List<IntermediaryEntity> intermediaries = intermediaryController.getAll();

        Label title = new Label("Lista de Intermediarios");
        Grid<IntermediaryEntity> grid = new Grid<>();
        DataProvider provider = new ListDataProvider(intermediaries);
        grid.setDataProvider(provider);
        grid.addColumn(IntermediaryEntity::getCompleteName).setHeader("Nombre Completo");
        grid.addColumn(IntermediaryEntity::getEmail).setHeader("Email");
        grid.addColumn(IntermediaryEntity::getPhoneNumber).setHeader("Telefonos");


        grid.addItemDoubleClickListener(event -> {
            Optional<IntermediaryEntity> intermediaryEntity = intermediaryController.findById(event.getItem().getId());
            if (intermediaryEntity.isPresent()) {
                Dialog dialog = new Dialog();
                ModifyIntermediaryView modalTraceView = new ModifyIntermediaryView(dialog, intermediaryController, intermediaryEntity);
                dialog.add(modalTraceView);
                dialog.open();
            } else {
                Notification.show("Intermediario no puede ser modificado", 2000, Notification.Position.MIDDLE);
            }

        });

        back.addClickListener(buttonClickEvent -> {
            UI.getCurrent().navigate(MenuView.class);
        });

        this.add(title, grid, back);
        this.setAlignItems(Alignment.CENTER);
    }
}
