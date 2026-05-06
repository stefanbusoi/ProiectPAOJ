package PCComponents;

public class CPU extends PCComponent{

    public CPU(float price,float power,String name){
        super(price,power,name);
    }

    @Override
    public String toString() {
        String string = "CPU{" +
                "Name=" + getName() +
                "|Price=" + getPrice() +
                "|Power=" + getPowerUsage() +
                "}";
        return string;
    }
}
