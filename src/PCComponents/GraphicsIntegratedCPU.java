package PCComponents;

import java.util.Scanner;

public class GraphicsIntegratedCPU extends CPU implements GraphicsCapability{

    float graphicsPower;
    public GraphicsIntegratedCPU(float price, float powerUsage, String name,float processingPower,int nrOfCores, float graphicsPower) {
        super(price, powerUsage, name,processingPower,nrOfCores);
        this.graphicsPower=graphicsPower;
    }
    public static GraphicsIntegratedCPU readComponent(Scanner scanner){
        float price;
        float power;
        String name;
        float processingPower;
        int nrOfCores;
        float graphicsPower;
        System.out.println("Introdu numele: ");
        name = scanner.nextLine();
        System.out.println("Introdu pretul: ");
        price = scanner.nextFloat();
        System.out.println("Introdu consumul: ");
        power = scanner.nextFloat();
        System.out.println("Introdu puterea grafica: ");
        graphicsPower = scanner.nextFloat();
        System.out.println("Introdu numarul de nuclee: ");
        nrOfCores = scanner.nextInt();
        System.out.println("Introdu puterea de procesare: ");
        processingPower = scanner.nextFloat();
        GraphicsIntegratedCPU graphicsIntegratedCPU = new GraphicsIntegratedCPU(price, power, name,processingPower,nrOfCores,graphicsPower);
        return graphicsIntegratedCPU;
    }
    @Override
    public String toString(){
        String string = "iCPU{" +
                "Name=" + getName() +
                "|Price=" + getPrice() +
                "|PowerConsumption=" + getPowerUsage() +
                "|ProcessingPower=" + processingPower +
                "|NumberOfCores=" + nrOfCores +
                "|Graphics Power=" + getGraphicsPower()+
                "}";
        return string;
    }

    @Override
    public float getGraphicsPower() {
        return graphicsPower;
    }

    @Override
    public float getVRam() {
        
        return 0;
    }


    @Override
    public void Edit() {

    }
}
