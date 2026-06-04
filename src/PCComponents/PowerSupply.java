package PCComponents;

import java.util.Scanner;

public class PowerSupply extends PCComponent{
    float efficiency;
    float maxPower;
    public PowerSupply(float price, String name,float maxPower,float efficiency) {
        super(price, 0, name);
        this.efficiency=efficiency;
        this.maxPower=maxPower;
    }

    public static PowerSupply readComponent(Scanner scanner){
        float price;
        String name;
        float efficiency;
        float maxPower;
        System.out.println("Introdu numele: ");
        name = scanner.nextLine();
        System.out.println("Introdu pretul: ");
        price = scanner.nextFloat();
        System.out.println("Introdu eficienta: ");
        efficiency = scanner.nextFloat();
        System.out.println("Introdu puterea maxima: ");
        maxPower = scanner.nextFloat();
        PowerSupply powerSupply = new PowerSupply(price, name,maxPower,efficiency);
        return powerSupply;
    }

    @Override
    public void Edit() {

    }
    public float getEfficiency(){
        return efficiency;
    }
    public float getMaxPower(){
        return maxPower;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder=new StringBuilder("Power Supply{");
        stringBuilder.append("Name=");
        stringBuilder.append(getName());
        stringBuilder.append("|Price=");
        stringBuilder.append(getPrice());
        stringBuilder.append("|Power=");
        stringBuilder.append(getPowerUsage());
        stringBuilder.append("|MaxPower=");
        stringBuilder.append(maxPower);
        stringBuilder.append("|Efficiency=");
        stringBuilder.append(efficiency);
        stringBuilder.append("}");
        return stringBuilder.toString();
    }
}
