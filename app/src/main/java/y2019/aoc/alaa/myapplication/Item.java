package y2019.aoc.alaa.myapplication;

public class Item {
    private String description;
    private int resid;//image id to be loaded

    public Item(String description, int resid) {
        this.description = description;
        this.resid = resid;
    }

    public Item() {
    }

    public String getDescription() {
        return description;
    }

    public int getResid() {
        return resid;
    }


    public void setDescription(String description) {
        this.description = description;
    }

    public void setResid(int resid) {
        this.resid = resid;
    }
}
