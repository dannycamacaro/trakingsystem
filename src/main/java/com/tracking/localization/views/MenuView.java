package com.tracking.localization.views;

import com.tracking.localization.controllers.IntermediaryController;
import com.tracking.localization.views.intermediary.ConsultIntermediaryView;
import com.tracking.localization.views.intermediary.ModifyIntermediaryView;
import com.tracking.localization.views.intermediary.RegisterIntermediaryView;
import com.tracking.localization.views.shipping.ConsultShippingView;
import com.tracking.localization.views.shipping.ModifyShippingView;
import com.tracking.localization.views.shipping.RegisterShippingView;
import com.tracking.localization.views.user.ConsultUsersView;
import com.tracking.localization.views.user.ModifyUserView;
import com.tracking.localization.views.user.RegisterUserView;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.contextmenu.SubMenu;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import org.springframework.beans.factory.annotation.Autowired;

@Route
public class MenuView extends VerticalLayout {
//    String imageMenu = "https://encolombia.com/wp-content/uploads/2019/10/Casillero-virtual-servientrega-box-Black-Friday-696x391.jpg";
    String imageMenu = "https://previews.123rf.com/images/hin255/hin2551606/hin255160600310/58665921-servicio-de-entrega-de-paquetes-de-recepci%C3%B3n-del-cliente-.jpg";

    @Autowired
    IntermediaryController intermediaryController;
    public MenuView() {
        this.setSizeFull();
        MenuBar menuBar = new MenuBar();

        MenuItem usersItem = menuBar.addItem("Usuarios");
//        RouterLink consultUser = new RouterLink("Consultar", ConsultUsersView.class);
        RouterLink registerUser = new RouterLink("Registrar", RegisterUserView.class);
//        RouterLink modifyUser = new RouterLink("Modificar", ModifyUserView.class);

        SubMenu usersItemSubMenu = usersItem.getSubMenu();
        MenuItem users = usersItemSubMenu.addItem("Usuarios");
        SubMenu usersSubMenu = users.getSubMenu();
        usersSubMenu.addItem(registerUser);
//        usersSubMenu.addItem(consultUser);
//        usersSubMenu.addItem(modifyUser);

        MenuItem intermediaryItem = menuBar.addItem("Intermediarios");
//        RouterLink consultIntermediary = new RouterLink("Consultar", ConsultIntermediaryView.class);
        RouterLink registerIntermediary = new RouterLink("Registrar", RegisterIntermediaryView.class);
        RouterLink modifyIntermediary = new RouterLink("Modificar", ConsultIntermediaryView.class);

        SubMenu intermediaryItemSubMenu =intermediaryItem.getSubMenu();
        MenuItem intermediaries = intermediaryItemSubMenu.addItem("Intermediarios");
        SubMenu intermediarySubMenu = intermediaries.getSubMenu();
        intermediarySubMenu.addItem(registerIntermediary);
//        intermediarySubMenu.addItem(consultIntermediary);
        intermediarySubMenu.addItem(modifyIntermediary);

        MenuItem packages = menuBar.addItem("Envios");
        RouterLink registerPackages = new RouterLink("Registrar", RegisterShippingView.class);
        RouterLink modifyPackages = new RouterLink("Modificar", ConsultShippingView.class);

        SubMenu packagesSubMenu = packages.getSubMenu();
        MenuItem packagesI = packagesSubMenu.addItem("Envios");
        SubMenu packagesISubMenuSubMenu = packagesI.getSubMenu();
        packagesISubMenuSubMenu.addItem(registerPackages);
        packagesISubMenuSubMenu.addItem(modifyPackages);

        RouterLink login = new RouterLink("Cerrar Session", IndexView.class);
        menuBar.addItem(login);


        Image image = new Image(imageMenu,"ImageMenu");
        image.setHeight("90%");
        image.setWidth("100%");
        add(menuBar,image);
        this.setAlignItems(Alignment.CENTER);
    }


}
