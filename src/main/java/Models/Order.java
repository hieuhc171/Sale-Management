package Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
public class Order implements Serializable {
    public static int count = 0;
    private Integer id;
    private String customerName;
    private String phone;
    private String email;
    private Product product;
    private int quantity;
    private float price;

    public Order(String customerName, String phone, String email, Product product, int quantity, float price) {
        count++;
        id = count;
        this.customerName = customerName;
        this.phone = phone;
        this.email = email;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public static String setfill (String text, int length) {
        StringBuilder answer = new StringBuilder(text);
        for(int i = text.length(); i < length; i++) {
            answer.append(" ");
        }
        return answer.toString();
    }

    @Override
    public String toString() {
        return setfill(id.toString(), 15) + setfill(customerName, 28)
                + setfill(phone, 26)
                + setfill(email, 33)
                + setfill(product.getName(), 27)
                + setfill(String.valueOf(quantity), 21) + setfill(product.getPrice().toString(), 25)
                + setfill(String.valueOf(quantity * product.getPrice()), 25);
    }
}
