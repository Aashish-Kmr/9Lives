package entity.medical.items;

public abstract class Item {
    private final int id;
    private String name;
    private String forIllness;
    private int cost;

    public Item(int id, String name, String forIllness, int cost) {
        this.id = id;
        this.name = name;
        this.forIllness = forIllness;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getForIllness() {
        return forIllness;
    }

    public void setForIllness(String forIllness) {
        this.forIllness = forIllness;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
