package PCComponents;

import java.util.Scanner;

public class Storage extends PCComponent {

    float capacity;
    public Storage(float price, float powerUsage, String name,float capacity) {
        super(price, powerUsage, name);
        this.capacity=capacity;
    }

    public static Storage readComponent(Scanner scanner){
        float price;
        float power;
        String name;
        float capacity;
        System.out.println("Introdu numele: ");
        name = scanner.nextLine();
        System.out.println("Introdu pretul: ");
        price = scanner.nextFloat();
        System.out.println("Introdu consumul: ");
        power = scanner.nextFloat();
        System.out.println("Introdu capacitatea: ");
        capacity= scanner.nextFloat();
        Storage storage = new Storage(price, power, name,capacity);
        return storage;
    }

    @Override
    public void Edit() {

    }
    public float getCapacity(){
        return capacity;
    }
    @Override
    public String toString() {
        StringBuilder stringBuilder=new StringBuilder("Storage{");
        stringBuilder.append("Name=");
        stringBuilder.append(getName());
        stringBuilder.append("|Price=");
        stringBuilder.append(getPrice());
        stringBuilder.append("|Power=");
        stringBuilder.append(getPowerUsage());
        stringBuilder.append("|Capacity=");
        stringBuilder.append(capacity);
        stringBuilder.append("}");
        return stringBuilder.toString();
    }
}
