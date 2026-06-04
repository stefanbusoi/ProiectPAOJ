package PCComponents;


import java.util.function.Function;


public abstract class PCComponent implements Comparable<PCComponent> {

    private String name;
    private float price;
    private float powerUsage;

    PCComponent(float price,float powerUsage,String name){
        this.price=price;
        this.powerUsage=powerUsage;
        this.name=name;
    }
    @Override
    public int compareTo(PCComponent other) {
        return this.name.compareTo(other.name);
    }
    public void setPrice(float price) {
        this.price = price;
    }
    public float getPrice() {
        return price;
    }
    public void setPowerUsage(float powerUsage) {
        this.powerUsage = powerUsage;
    }
    public float getPowerUsage() {
        return powerUsage;
    }
    public String getName() {
        return name;
    }


    public abstract void Edit();
    public static String getNameOrNone(PCComponent pcComponent){
        if (pcComponent==null)
            return "None";
        else
            return pcComponent.getName();
    }


}
