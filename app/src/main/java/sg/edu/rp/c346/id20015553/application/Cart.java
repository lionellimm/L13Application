package sg.edu.rp.c346.id20015553.application;

public class Cart {

    private int id;
    private String name;
    private String description;
    private float cost;
    private boolean purchased;

    public Cart(String name, String description, float cost, boolean purchased) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.purchased = purchased;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public boolean isPurchased() {
        return purchased;
    }

    public void setPurchased(boolean purchased) {
        this.purchased = purchased;
    }
}
