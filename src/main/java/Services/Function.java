package Services;

import Models.Order;
import Models.Product;
import javafx.geometry.Orientation;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Function {

    public static VBox product_display = new VBox();
    public static VBox order_display = new VBox();

    private static String src = "C:\\Users\\hieum\\Downloads\\QuanLyBanHang\\src\\main\\java\\";

    public static ImageView image = new ImageView(src + "Material\\Empty_sheet.png");
    public static final VBox pane = new VBox();
    public static boolean showingProduct = false;
    public static boolean showingOrder = false;

    public static ListView<Product> productList = new ListView<>();
    public static ListView<Order> orderList = new ListView<>();

    public static final ListView<Label> titleOrder = Function.createTitleOrder();
    public static final ListView<Label> titleProduct = Function.createTitleProduct();

    public static ListView<Label> createTitleOrder() {
        ListView<Label> titleOrder = new ListView<>();
        titleOrder.setMaxSize(100, 30);
        titleOrder.setOrientation(Orientation.HORIZONTAL);

        Label stt = new Label("STT  ");
        stt.setStyle("-fx-fill: Azure");
        stt.setStyle("-fx-text-fill: Black");

        Label name = new Label("Tên khách hàng    ");
        name.setStyle("-fx-fill: Azure");
        name.setStyle("-fx-text-fill: Black");

        Label phone = new Label("Số điện thoại   ");
        phone.setStyle("-fx-fill: Azure");
        phone.setStyle("-fx-text-fill: Black");

        Label email = new Label("Email             ");
        email.setStyle("-fx-fill: Azure");
        email.setStyle("-fx-text-fill: Black");

        Label product = new Label("Sản phẩm     ");
        product.setStyle("-fx-fill: Azure");
        product.setStyle("-fx-text-fill: Black");

        Label quantity = new Label("Số lượng   ");
        quantity.setStyle("-fx-fill: Azure");
        quantity.setStyle("-fx-text-fill: Black");

        Label price = new Label("Giá        ");
        price.setStyle("-fx-fill: Azure");
        price.setStyle("-fx-text-fill: Black");

        Label totalPrice = new Label("Thành tiền   ");
        totalPrice.setStyle("-fx-fill: Azure");
        totalPrice.setStyle("-fx-text-fill: Black");

        titleOrder.getItems().addAll(stt, name, phone, email, product, quantity, price, totalPrice);

        return titleOrder;
    }

    public static ListView<Label> createTitleProduct() {
        ListView<Label> titleProduct = new ListView<>();
        titleProduct.setMaxSize(100, 30);
        titleProduct.setOrientation(Orientation.HORIZONTAL);

        Label prd_stt = new Label("STT  ");
        prd_stt.setStyle("-fx-fill: Azure");
        prd_stt.setStyle("-fx-text-fill: Black");

        Label product_name = new Label("Tên sản phẩm         ");
        product_name.setStyle("-fx-fill: Azure");
        product_name.setStyle("-fx-text-fill: Black");

        Label prd_quantity = new Label("Số lượng   ");
        prd_quantity.setStyle("-fx-fill: Azure");
        prd_quantity.setStyle("-fx-text-fill: Black");

        Label description = new Label("Mô tả                            ");
        description.setStyle("-fx-fill: Azure");
        description.setStyle("-fx-text-fill: Black");

        Label prd_price = new Label("Giá           ");
        prd_price.setStyle("-fx-fill: Azure");
        prd_price.setStyle("-fx-text-fill: Black");

        titleProduct.getItems().addAll(prd_stt, product_name, prd_quantity, description, prd_price);

        return titleProduct;
    }

    public static void showListChoice(Label list, Menu listMenu) {
        list.setOnMouseEntered(mouseEvent -> {
            listMenu.show();
        });
        list.setOnMouseClicked(mouseEvent -> {
            listMenu.show();
        });
    }

    public static void showProducts(Label products, Menu listMenu) {
        products.setOnMouseClicked(mouseEvent -> {
            showingOrder = false;
            showingProduct = true;
            listMenu.hide();

            pane.getChildren().remove(image);

            titleProduct.setMaxSize(800, 30);
            titleProduct.setMinSize(800, 30);

            titleOrder.setMaxSize(0, 0);
            titleOrder.setMinSize(0, 0);

            product_display = new VBox(productList);
            order_display.setMaxSize(0, 0);
            order_display.setMinSize(0, 0);
            pane.getChildren().add(product_display);
        });
    }

    public static void showOrders(Label orders, Menu listMenu) {

        orders.setOnMouseClicked(mouseEvent -> {
            if(productList.getItems().size() == 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Lỗi");
                alert.setResizable(false);
                alert.setHeaderText("Chưa có sản phẩm nào trong kho!");
                alert.setContentText("Bạn phải nhập ít nhất 1 sản phẩm trước");
                alert.showAndWait();
                return;
            }
            showingOrder = true;
            showingProduct = false;
            listMenu.hide();

            pane.getChildren().remove(image);

            titleProduct.setMaxSize(0, 0);
            titleProduct.setMinSize(0, 0);

            titleOrder.setMaxSize(800, 30);
            titleOrder.setMinSize(800, 30);

            order_display = new VBox(orderList);
            product_display.setMaxSize(0, 0);
            product_display.setMinSize(0, 0);
            pane.getChildren().add(order_display);
        });
    }

    public static void addFunction(Label add) {
        add.setOnMouseClicked(mouseEvent -> {
            if(showingProduct) {
                Popup.displayProduct();
            }
            else if(showingOrder) {
                Popup.displayOrder();
            }
        });
    }

    public static void editFunction(Label edit) {
        edit.setOnMouseClicked(mouseEvent -> {
            Popup.displayEditWindow();
        });
    }

    public static void deleteFunction(Label delete) {
        delete.setOnMouseClicked(mouseEvent -> {
            Popup.displayDeleteWindow();
        });
    }

    public static void saveFunction(Label save) {
        save.setOnMouseClicked(mouseEvent -> {
            try {
                ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(src + "Material\\product_output.dat"));
                List<Product> products = new ArrayList<>(productList.getItems());
                out.writeObject(products);
                out.close();

                out = new ObjectOutputStream(new FileOutputStream(src + "Material\\order_output.dat"));
                List<Order> orders = new ArrayList<>(orderList.getItems());
                out.writeObject(orders);
                out.close();

                Popup.displaySaveMessage();

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public static void loadFunction(Label load) {
        load.setOnMouseClicked(mouseEvent -> {
            try {
                ObjectInputStream in = new ObjectInputStream(new FileInputStream(src + "Material\\product_output.dat"));
                List<Product> products = (List<Product>) in.readObject();
                for (Product product : products) {
                    productList.getItems().add(product);
                }
                in.close();

                in = new ObjectInputStream(new FileInputStream(src + "Material\\order_output.dat"));
                List<Order> orders = (List<Order>) in.readObject();
                for (Order order : orders) {
                    orderList.getItems().add(order);
                }
                in.close();

                Popup.displayLoadMessage();

            } catch (IOException | ClassNotFoundException e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Lỗi");
                alert.setHeaderText("Lỗi danh sách");
                alert.setContentText("");
                alert.showAndWait();

                e.printStackTrace();
            }
        });
    }
}
