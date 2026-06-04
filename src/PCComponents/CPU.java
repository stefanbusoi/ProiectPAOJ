package PCComponents;

import java.util.Scanner;

public class CPU extends PCComponent{

    int nrOfCores;
    float processingPower;
    public CPU(float price,float powerUsage,String name,float processingPower, int nrOfCores){
        super(price,powerUsage,name);
        this.processingPower=processingPower;
        this.nrOfCores=nrOfCores;
    }

    public int getNrOfCores() {
        return nrOfCores;
    }

    public float getProcessingPower() {
        return processingPower;
    }

    @Override
    public void Edit() {
        //TODO: add implementation
    }

    public static CPU readComponent(Scanner scanner){
        float price;
        float power;
        String name;
        float processingPower;
        int nrOfCores;
        System.out.println("Introdu numele: ");
        name = scanner.nextLine();
        System.out.println("Introdu pretul: ");
        price = scanner.nextFloat();
        System.out.println("Introdu consumul: ");
        power = scanner.nextFloat();
        System.out.println("Introdu putere procesor: ");
        processingPower = scanner.nextFloat();
        System.out.println("Introdu numarul de core-uri: ");
        nrOfCores = scanner.nextInt();
        CPU cpu = new CPU(price, power, name,processingPower,nrOfCores);
        return cpu;
    }

    @Override
    public String toString() {
        String string = "CPU{" +
                "Name=" + getName() +
                "|Price=" + getPrice() +
                "|PowerConsumption=" + getPowerUsage() +
                "|ProcessingPower=" + processingPower +
                "|NumberOfCores=" + nrOfCores +
                "}";
        return string;
    }



}
