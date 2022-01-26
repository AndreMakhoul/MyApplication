package y2019.aoc.alaa.myapplication;

public class Car {
    private String description;
    private String category;
    private int year;
    private double price;
    private int left;
    private int image; //resid
    private int noOfSeats;//
    private boolean isElectric;

    public Car() { }

    public Car(String description, String category, int year, double price, int left, int image, int noOfSeats, boolean isElectric) {
        this.description = description;
        this.category = category;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
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

    public boolean isElectric() {
        return isElectric;
    }

    public void setElectric(boolean electric) {
        isElectric = electric;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
