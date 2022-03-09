package y2019.aoc.alaa.myapplication;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
@IgnoreExtraProperties
public class Car implements Serializable {
    private String description;
    private String type;
    private int year;
    private int price;
    private int left;
    private int image; //resid
    private int noOfSeats;//
    private Boolean isElectric;

    public Car() { }

    public Car(String description, String type, int year, int price, int left, int image, int noOfSeats, Boolean isElectric) {
        this.description = description;
        this.type = type;
        this.year = year;
        this.price = price;
        this.left = left;
        this.image = image;
        this.noOfSeats = noOfSeats;
        this.isElectric = isElectric;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getNoOfSeats() {
        return noOfSeats;
    }

    public void setNoOfSeats(int noOfSeats) {
        this.noOfSeats = noOfSeats;
    }

    public Boolean isElectric() {
        return isElectric;
    }

    public void setElectric(Boolean electric) {
        isElectric = electric;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
