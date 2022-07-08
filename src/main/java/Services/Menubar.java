package Services;

import Services.Function;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class Menubar {
    public static MenuBar createMenu() {
        MenuBar menuBar = new MenuBar();

        Label list = new Label("Danh sách...");
        Menu listMenu = new Menu("", list);
        Function.showListChoice(list, listMenu);
        listMenu.setStyle("-fx-border-color: Black");

        Label add = new Label("Thêm");
        Menu addMenu = new Menu("", add);
        Function.addFunction(add);
        addMenu.setStyle("-fx-border-color: Black");

        Label edit = new Label("Sửa");
        Menu editMenu = new Menu("", edit);
        Function.editFunction(edit);
        editMenu.setStyle("-fx-border-color: Black");

        Label delete = new Label("Xoá");
        Menu deleteMenu = new Menu("", delete);
        Function.deleteFunction(delete);
        deleteMenu.setStyle("-fx-border-color: Black");

        Label save = new Label("Lưu");
        Menu saveMenu = new Menu("", save);
        Function.saveFunction(save);
        saveMenu.setStyle("-fx-border-color: Black");

        Label load = new Label("Mở");
        Menu loadMenu = new Menu("", load);
        Function.loadFunction(load);
        loadMenu.setStyle("-fx-border-color: Black");

        Label products = new Label("Sản phẩm");
        MenuItem productListMenu = new Menu("", products);
        products.setStyle("-fx-text-fill: Black");

        Label orders = new Label("Đơn hàng");
        MenuItem orderListMenu = new Menu("", orders);
        orders.setStyle("-fx-text-fill: Black");

        Function.showOrders(orders, listMenu);
        Function.showProducts(products, listMenu);
        listMenu.getItems().addAll(productListMenu, orderListMenu);

        menuBar.getMenus().addAll(listMenu, addMenu, editMenu, deleteMenu, saveMenu, loadMenu);
        return menuBar;
    }

}
