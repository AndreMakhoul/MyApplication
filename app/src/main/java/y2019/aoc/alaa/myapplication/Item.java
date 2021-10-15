package y2019.aoc.alaa.myapplication;

public class Item {
    private String description;
    private int resid;//image id to be loaded
    private boolean isHappy;
    private int amount;

    public Item(String description, int resid, boolean isHappy, int amount) {
        this.description = description;
        this.resid = resid;
        this.isHappy = isHappy;
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public int getResid() {
        return resid;
    }

    public boolean isHappy() {
        return isHappy;
    }

    public int getAmount() {
        return amount;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setResid(int resid) {
        this.resid = resid;
    }

    public void setHappy(boolean happy) {
        isHappy = happy;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
