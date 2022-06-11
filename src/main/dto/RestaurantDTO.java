package main.dto;

import lombok.Data;

@Data
public class RestaurantDTO implements Comparable<RestaurantDTO>{

    private String storeName;
    private String menu;
    private int price;
    private float rating;
    private String insertDate;
    private String updateDate;

    public RestaurantDTO(String storeName, String menu, int price, float rating, String insertDate, String updateDate) {
        this.storeName = storeName;
        this.menu = menu;
        this.price = price;
        this.rating = rating;
        this.insertDate = insertDate;
        this.updateDate = updateDate;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(String insertDate) {
        this.insertDate = insertDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        return "RestaurantDTO{" +
                "storeName='" + storeName + '\'' +
                ", menu='" + menu + '\'' +
                ", price=" + price +
                ", rating=" + rating +
                ", insertDate='" + insertDate + '\'' +
                ", updateDate='" + updateDate + '\'' +
                '}';
    }

    @Override
    public int compareTo(RestaurantDTO o) {
        return (int) ((o.getRating() * 10) - (this.rating * 10));
    }
}
