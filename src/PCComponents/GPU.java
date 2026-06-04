package PCComponents;

import java.util.Scanner;

public class GPU extends PCComponent implements GraphicsCapability {

    float graphicsPower;
    float vRAM;
    public GPU(float price, float powerUsage, String name,float graphicsPower, float vRAM) {
        super(price, powerUsage, name);
        this.graphicsPower=graphicsPower;
        this.vRAM=vRAM;
    }

    public static GPU readComponent(Scanner scanner){
        float price;
        float power;
        String name;
        float graphicsPower;
        float vRAM;
        System.out.println("Introdu numele: ");
        name = scanner.nextLine();
        System.out.println("Introdu pretul: ");
        price = scanner.nextFloat();
        System.out.println("Introdu consumul: ");
        power = scanner.nextFloat();
        System.out.println("Introdu puterea grafica: ");
        graphicsPower = scanner.nextFloat();
        System.out.println("Introdu memoria video: ");
        vRAM = scanner.nextFloat();
        GPU gpu = new GPU(price, power, name,graphicsPower,vRAM);
        return gpu;
    }   

    @Override
    public void Edit() {
        //TODO: add implementation

    }

    @Override
    public String toString() {
        StringBuilder stringBuilder=new StringBuilder("GPU{");
        stringBuilder.append("Name=");
        stringBuilder.append(getName());
        stringBuilder.append("|Price=");
        stringBuilder.append(getPrice());
        stringBuilder.append("|Power=");
        stringBuilder.append(getPowerUsage());
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    @Override
    public float getGraphicsPower() {
        return graphicsPower;
    }

    @Override
    public float getVRam() {
        return vRAM;
    }

}
