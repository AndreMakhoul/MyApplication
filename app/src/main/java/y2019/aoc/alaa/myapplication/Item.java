package y2019.aoc.alaa.myapplication;

public class Item {
    private String description;
    private int resid;//image id to be loaded
    private String yearpre;
    private String amount;

    public Item(String description, int resid, String yearpre, String amount) {
        this.description = description;
        this.resid = resid;
        this.yearpre = yearpre;
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public int getResid() {
        return resid;
    }

    public String getYearpre() {
        return yearpre;
    }

    public String getAmount() {
        return amount;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setResid(int resid) {
        this.resid = resid;
    }

    public void setYearpre(String yearpre) {
        yearpre = yearpre;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
