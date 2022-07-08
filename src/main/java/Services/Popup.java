package Services;

import Models.Order;
import Models.Product;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javafx.scene.control.*;

import java.util.Objects;

public class Popup {
    private static Stage popupwindow;

    private static Label customerName, productName, description, phone, email, product, quantity, price, totalPrice;
    private static TextField tf_name, tf_description, tf_phone, tf_email, tf_quantity, tf_price, tf_totalPrice;
    private static ChoiceBox<String> cb_product;
    private static HBox hb_name, hb_description, hb_phone, hb_email, hb_product, hb_quantity, hb_price, hb_totalPrice;
    private static TextField tf_temp1, tf_temp2;
    private static Button enter;
    private static HBox hb_enter;
    private static VBox layout;
    private static final Product[] selected_product = {new Product()};

    private static void createPopUpWindow() {
        popupwindow = new Stage();

        popupwindow.initModality(Modality.APPLICATION_MODAL);

        tf_temp1 = new TextField();
        tf_temp2 = new TextField();
        tf_temp1.setVisible(false);
        tf_temp2.setVisible(false);

        enter = new Button("OK");
        hb_enter = new HBox(tf_temp2, enter);
        layout = new VBox();
    }

    private static void createOrderGUI() {
        customerName = new Label("   Tên khách hàng");
        phone = new Label("   Điện thoại");
        email = new Label("   Email");
        product = new Label("   Sản phẩm");
        quantity = new Label("   Số lượng");
        price = new Label("   Giá");
        totalPrice = new Label("   Thành tiền");

        customerName.setPrefWidth(100);
        phone.setPrefWidth(100);
        email.setPrefWidth(100);
        product.setPrefWidth(100);
        quantity.setPrefWidth(100);
        price.setPrefWidth(100);
        totalPrice.setPrefWidth(100);

        tf_name = new TextField();
        tf_phone = new TextField();
        tf_email = new TextField();
        cb_product = new ChoiceBox<>();

        tf_quantity = new TextField();
        tf_price = new TextField();
        tf_totalPrice = new TextField();
        for(int i = 0; i < Function.productList.getItems().size(); i++) {
            cb_product.getItems().add(Function.productList.getItems().get(i).getName());
        }
        tf_price.setEditable(false);
        tf_totalPrice.setEditable(false);
        tf_price.setDisable(true);
        tf_totalPrice.setDisable(true);
        tf_price.setStyle("-fx-control-inner-background: #A9A9A9");
        tf_totalPrice.setStyle("-fx-control-inner-background: #A9A9A9");

        hb_name = new HBox(customerName, tf_name);
        hb_phone = new HBox(phone, tf_phone);
        hb_email = new HBox(email, tf_email);
        hb_product = new HBox(product, cb_product);
        hb_quantity = new HBox(quantity, tf_quantity);
        hb_price = new HBox(price, tf_price);
        hb_totalPrice = new HBox(totalPrice, tf_totalPrice);

        tf_quantity.setText("0");
        cb_product.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
//                selected_product[0] = Function.productList.getItems().get(t1.intValue());
                tf_price.setText(String.valueOf(selected_product[0].getPrice()));
                tf_totalPrice.setText(String.valueOf(selected_product[0].getPrice() * Integer.parseInt(tf_quantity.getText())));
            }
        });
        tf_quantity.setOnKeyPressed(keyEvent -> {
            if(keyEvent.getCode() == KeyCode.ENTER || keyEvent.getCode() == KeyCode.TAB) {
                selected_product[0] = Function.productList.getItems().get(0);
                tf_price.setText(String.valueOf(selected_product[0].getPrice()));
                tf_totalPrice.setText(String.valueOf(Float.parseFloat(tf_price.getText()) * Integer.parseInt(tf_quantity.getText())));
            }
        });

        cb_product.getSelectionModel().selectFirst();

    }

    private static void createProductGUI() {
        popupwindow.setTitle("Điền thông tin sản phẩm");

        productName = new Label("   Tên sản phẩm");
        quantity = new Label("   Số lượng");
        description = new Label("   Mô tả");
        price = new Label("   Giá");

        productName.setPrefWidth(100);
        quantity.setPrefWidth(100);
        description.setPrefWidth(100);
        price.setPrefWidth(100);

        tf_name = new TextField();
        tf_quantity = new TextField();
        tf_description = new TextField();
        tf_price = new TextField();

        hb_name = new HBox(productName, tf_name);
        hb_quantity = new HBox(quantity, tf_quantity);
        hb_description = new HBox(description, tf_description);
        hb_price = new HBox(price, tf_price);

    }

    public static void displayOrder() {
        createPopUpWindow();
        popupwindow.setTitle("Điền thông tin đơn hàng");

        enter.setOnAction(actionEvent -> {
            if(!Objects.equals(tf_name.getText(), "")
                    && !Objects.equals(tf_phone.getText(), "")
                    && !Objects.equals(tf_email.getText(), "")
                    && !Objects.equals(tf_quantity.getText(), "")
                    && !Objects.equals(tf_price.getText(), "")
                    && !Objects.equals(tf_totalPrice.getText(), "")) {
                Order new_order = new Order(tf_name.getText(), tf_phone.getText(), tf_email.getText(), selected_product[0], Integer.parseInt(tf_quantity.getText()), Float.parseFloat(tf_price.getText()));
                Function.orderList.getItems().add(new_order);
            }
            popupwindow.close();
        });

        createOrderGUI();

        layout.getChildren().addAll(hb_name, hb_phone, hb_email, hb_product, hb_quantity, hb_price, hb_totalPrice, tf_temp1, hb_enter);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 300, 300);
        popupwindow.setScene(scene);
        popupwindow.show();
    }

    public static void displayProduct() {
        createPopUpWindow();
        popupwindow.setTitle("Điền thông tin sản phẩm");

        enter.setOnAction(actionEvent -> {
            if(tf_name.getText() != "" && tf_quantity.getText() != "" && tf_description.getText() != "" && tf_price.getText() != "") {
                Product new_product = new Product(tf_name.getText(), Integer.parseInt(tf_quantity.getText()), tf_description.getText(), Float.parseFloat(tf_price.getText()));
                Function.productList.getItems().add(new_product);
            }
            popupwindow.close();
        });

        createProductGUI();

        layout.getChildren().addAll(hb_name, hb_quantity, hb_description, hb_price, tf_temp1, hb_enter);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 300, 300);
        popupwindow.setScene(scene);
        popupwindow.show();
    }

    public static void displayEditWindow() {
        createPopUpWindow();

        Label stt = new Label("   Chọn số thứ tự");
        stt.setPrefWidth(100);
        ChoiceBox<Integer> cb_index = new ChoiceBox<>();
        HBox hb_stt = new HBox(stt, cb_index);
        cb_index.getSelectionModel().selectFirst();

        if(Function.showingOrder) {
            for(int i = 0; i < Function.orderList.getItems().size(); i++) {
                cb_index.getItems().add(Function.orderList.getItems().get(i).getId());
            }

            popupwindow.setTitle("Sửa thông tin hoá đơn");

            createOrderGUI();

            final int[] selected_index = new int[1];

            cb_index.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                    selected_index[0] = t1.intValue();
                    Order choosingOrder = Function.orderList.getItems().get(t1.intValue());
                    tf_name.setText(choosingOrder.getCustomerName());
                    tf_phone.setText(choosingOrder.getPhone());
                    tf_email.setText(choosingOrder.getEmail());
                    cb_product.getSelectionModel().select(choosingOrder.getProduct().getName());
                    tf_quantity.setText(String.valueOf(choosingOrder.getQuantity()));
                    tf_price.setText(String.valueOf(choosingOrder.getPrice()));
                    tf_totalPrice.setText(String.valueOf(choosingOrder.getPrice() * choosingOrder.getQuantity()));
                }
            });

            enter.setOnAction(actionEvent -> {
                if(!Objects.equals(tf_name.getText(), "")
                        && !Objects.equals(tf_phone.getText(), "")
                        && !Objects.equals(tf_email.getText(), "")
                        && !Objects.equals(tf_quantity.getText(), "")
                        && !Objects.equals(tf_price.getText(), "")
                        && !Objects.equals(tf_totalPrice.getText(), "")) {
                    Order.count--;
                    Order new_order = new Order(tf_name.getText(), tf_phone.getText(), tf_email.getText(), selected_product[0], Integer.parseInt(tf_quantity.getText()), Float.parseFloat(tf_price.getText()));
                    Function.orderList.getItems().set(selected_index[0], new_order);
                }
                popupwindow.close();
            });

            layout.getChildren().addAll(hb_stt, hb_name, hb_phone, hb_email, hb_product, hb_quantity, hb_price, hb_totalPrice, tf_temp1, hb_enter);
            layout.setAlignment(Pos.CENTER);

            Scene scene = new Scene(layout, 300, 300);
            popupwindow.setScene(scene);
            popupwindow.show();

        }

        if(Function.showingProduct) {
            for(int i = 0; i < Function.productList.getItems().size(); i++) {
                cb_index.getItems().add(Function.productList.getItems().get(i).getId());
            }

            popupwindow.setTitle("Sửa thông tin sản phẩm");

            createProductGUI();

            final int[] seleted_index = new int[1];

            cb_index.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                    seleted_index[0] = t1.intValue();
                    Product choosingProduct = Function.productList.getItems().get(t1.intValue());
                    tf_name.setText(choosingProduct.getName());
                    tf_price.setText(choosingProduct.getPrice().toString());
                    tf_description.setText(choosingProduct.getDescription());
                    tf_quantity.setText(choosingProduct.getQuantity().toString());
                }
            });

            enter.setOnAction(actionEvent -> {
                if(tf_name.getText() != "" && tf_quantity.getText() != "" && tf_description.getText() != "" && tf_price.getText() != "") {
                    Product.count--;
                    Product new_product = new Product(tf_name.getText(), Integer.parseInt(tf_quantity.getText()), tf_description.getText(), Float.parseFloat(tf_price.getText()));
                    Function.productList.getItems().set(seleted_index[0], new_product);
                }
                popupwindow.close();
            });

            layout.getChildren().addAll(hb_stt, hb_name, hb_quantity, hb_description, hb_price, tf_temp1, hb_enter);
            layout.setAlignment(Pos.CENTER);

            Scene scene = new Scene(layout, 300, 300);
            popupwindow.setScene(scene);
            popupwindow.show();

        }
    }

    public static void displayDeleteWindow() {
        createPopUpWindow();
        popupwindow.setTitle("Chọn sản phẩm cần xoá");

        Label stt = new Label("   Chọn số thứ tự");
        stt.setPrefWidth(100);
        ChoiceBox<Integer> cb_index = new ChoiceBox<>();
        HBox hb_stt = new HBox(stt, cb_index);
        cb_index.getSelectionModel().selectFirst();

        if(Function.showingOrder) {
            for(int i = 0; i < Function.orderList.getItems().size(); i++) {
                cb_index.getItems().add(Function.orderList.getItems().get(i).getId());
            }
            createOrderGUI();

            final int[] selected_index = new int[1];

            cb_index.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                    selected_index[0] = t1.intValue();
                    Order choosingOrder = Function.orderList.getItems().get(t1.intValue());
                    tf_name.setText(choosingOrder.getCustomerName());
                    tf_phone.setText(choosingOrder.getPhone());
                    tf_email.setText(choosingOrder.getEmail());
                    cb_product.getSelectionModel().select(choosingOrder.getProduct().getName());
                    tf_quantity.setText(String.valueOf(choosingOrder.getQuantity()));
                    tf_price.setText(String.valueOf(choosingOrder.getPrice()));
                    tf_totalPrice.setText(String.valueOf(choosingOrder.getPrice() * choosingOrder.getQuantity()));
                }
            });

            enter.setOnAction(actionEvent -> {
                if(!Objects.equals(tf_name.getText(), "")
                        && !Objects.equals(tf_phone.getText(), "")
                        && !Objects.equals(tf_email.getText(), "")
                        && !Objects.equals(tf_quantity.getText(), "")
                        && !Objects.equals(tf_price.getText(), "")
                        && !Objects.equals(tf_totalPrice.getText(), "")) {
                    Order.count--;
                    Function.orderList.getItems().remove(selected_index[0]);
                    for(int i = selected_index[0]+1; i < Function.orderList.getItems().size(); i++) {
                        Function.orderList.getItems().get(i).setId(i-1);
                    }
                }
                popupwindow.close();
            });

            layout.getChildren().addAll(hb_stt, hb_name, hb_phone, hb_email, hb_product, hb_quantity, hb_price, hb_totalPrice, tf_temp1, hb_enter);
            layout.setAlignment(Pos.CENTER);

            Scene scene = new Scene(layout, 300, 300);
            popupwindow.setScene(scene);
            popupwindow.show();

        }

        if(Function.showingProduct) {
            for(int i = 0; i < Function.productList.getItems().size(); i++) {
                cb_index.getItems().add(Function.productList.getItems().get(i).getId());
            }
            createProductGUI();

            final int[] selected_index = new int[1];

            cb_index.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                    selected_index[0] = t1.intValue();
                    Product choosingProduct = Function.productList.getItems().get(t1.intValue());
                    tf_name.setText(choosingProduct.getName());
                    tf_price.setText(choosingProduct.getPrice().toString());
                    tf_description.setText(choosingProduct.getDescription());
                    tf_quantity.setText(choosingProduct.getQuantity().toString());
                }
            });

            enter.setOnAction(actionEvent -> {
                if(tf_name.getText() != "" && tf_quantity.getText() != "" && tf_description.getText() != "" && tf_price.getText() != "") {
                    Product.count--;
                    Function.productList.getItems().remove(selected_index[0]);
                    for(int i = selected_index[0]+1; i < Function.productList.getItems().size(); i++) {
                        Function.productList.getItems().get(i).setId(i - 1);
                    }
                }
                popupwindow.close();
            });

            layout.getChildren().addAll(hb_stt, hb_name, hb_quantity, hb_description, hb_price, tf_temp1, hb_enter);
            layout.setAlignment(Pos.CENTER);

            Scene scene = new Scene(layout, 300, 300);
            popupwindow.setScene(scene);
            popupwindow.show();

        }

    }

    public static void displaySaveMessage() {
        Stage substage = new Stage();
        substage.setResizable(false);
        substage.initModality(Modality.APPLICATION_MODAL);
        substage.setTitle("Thông báo");

        Scene scene = new Scene(new HBox(new Label("  Lưu danh sách thành công!")), 250, 30);
        substage.setScene(scene);
        substage.show();
    }

    public static void displayLoadMessage() {
        Stage substage = new Stage();
        substage.setResizable(false);
        substage.initModality(Modality.APPLICATION_MODAL);
        substage.setTitle("Thông báo");

        Scene scene = new Scene(new HBox(new Label("  Tải lên danh sách thành công!")), 250, 30);
        substage.setScene(scene);
        substage.show();
    }
}
