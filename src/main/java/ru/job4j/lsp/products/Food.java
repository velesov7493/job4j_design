package ru.job4j.lsp.products;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Food {

    private String name;
    private Date createDate;
    private Date expiryDate;
    private float price;
    private float discount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public int getExpiredRatio() {
        long totalTime = expiryDate.getTime() - createDate.getTime();
        long currentTime = System.currentTimeMillis() - createDate.getTime();
        return Math.round(100 * (float) currentTime / totalTime);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Food food = (Food) o;
        return
                Float.compare(food.price, price) == 0
                && Float.compare(food.discount, discount) == 0
                && Objects.equals(name, food.name)
                && Objects.equals(createDate, food.createDate)
                && Objects.equals(expiryDate, food.expiryDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, createDate, expiryDate, price, discount);
    }

    @Override
    public String toString() {
        SimpleDateFormat f = new SimpleDateFormat("dd.MM.yyyy");
        return
                "Food{"
                + "name='" + name + '\''
                + ", createDate=" + f.format(createDate)
                + ", expiryDate=" + f.format(expiryDate)
                + ", price=" + price
                + ", discount=" + discount
                + '}';
    }
}
