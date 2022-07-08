package Models;

import java.io.Serializable;

public class Product implements Serializable {
    public static Integer count = 0;
    private Integer id;
    private String name;
    private Integer quantity;
    private String description;
    private Float price;

    public Product(String name, Integer quantity, String description, Float price) {
        count++;
        this.id = count;
        this.name = name;
        this.quantity = quantity;
        this.description = description;
        this.price = price;
    }

    public Product() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return Order.setfill(id.toString(), 12)
                + Order.setfill(name, 31)
                + Order.setfill(quantity.toString(), 21)
                + Order.setfill(description, 29)
                + Order.setfill(price.toString(), 24);
    }
}
