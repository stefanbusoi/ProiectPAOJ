package PCComponents;

import java.util.Scanner;

public class Memory extends PCComponent {

    float capacity;
    float speed;
    public Memory(float price, float powerUsage, String name,float capacity,float speed) {
        super(price, powerUsage, name);
        this.capacity=capacity;
        this.speed=speed;
    }

    public static Memory readComponent(Scanner scanner){
        float price;
        float power;
        String name;
        float capacity;
        float speed;
        System.out.println("Introdu numele: ");
        name = scanner.nextLine();
        System.out.println("Introdu pretul: ");
        price = scanner.nextFloat();
        System.out.println("Introdu consumul: ");
        power = scanner.nextFloat();
        System.out.println("Introdu capacitatea: ");
        capacity = scanner.nextFloat();
        System.out.println("Introdu viteza: ");
        speed = scanner.nextFloat();
        Memory memory = new Memory(price, power, name,capacity,speed);
        return memory;
    }

    public float getSpeed() {
        return speed;
    }

    public float getCapacity() {
        return capacity;
    }

    @Override
    public void Edit() {

    }

    @Override
    public String toString() {
        StringBuilder stringBuilder=new StringBuilder("Memory{");
        stringBuilder.append("Name=");
        stringBuilder.append(getName());
        stringBuilder.append("|Price=");
        stringBuilder.append(getPrice());
        stringBuilder.append("|Power=");
        stringBuilder.append(getPowerUsage());
        stringBuilder.append("}");
        return stringBuilder.toString();
    }
}
