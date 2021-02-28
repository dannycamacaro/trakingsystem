package com.tracking.localization.views.shipping;

import com.tracking.localization.controllers.PackageController;
import com.tracking.localization.entitys.PackageEntity;
import com.tracking.localization.views.MenuView;
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
public class ConsultShippingView extends VerticalLayout {

    Button back = new Button("Volver al menu");

    @Autowired
    public ConsultShippingView(PackageController packageController) {
        this.setSizeFull();
        List<PackageEntity> packageEntities = packageController.getAll();

        Label title = new Label("Lista de Envios");
        Grid<PackageEntity> grid = new Grid<>();
        DataProvider provider = new ListDataProvider(packageEntities);
        grid.setDataProvider(provider);
        grid.addColumn(PackageEntity::getSenderProduct).setHeader("Producto Enviado");
        grid.addColumn(PackageEntity::getSenderTypeDocument).setHeader("Tipo Documento Despacha");
        grid.addColumn(PackageEntity::getSenderDocument).setHeader("Cedula");
        grid.addColumn(PackageEntity::getSenderCompleteName).setHeader("Nombre");
        grid.addColumn(PackageEntity::getReciverTypeDocument).setHeader("Tipo Documento Receptor");
        grid.addColumn(PackageEntity::getReciverDocument).setHeader("Cedula Receptor");
        grid.addColumn(PackageEntity::getReciverCompleteName).setHeader("Nombre Receptor");


        grid.addItemDoubleClickListener(event -> {
            Optional<PackageEntity> packageEntity = packageController.findById(event.getItem().getId());
            if (packageEntity.isPresent()) {
                Dialog dialog = new Dialog();
                ModifyShippingView modalTraceView = new ModifyShippingView(dialog, packageController, packageEntity);
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
