import PCComponents.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    static void adaugaComponenta(){
        System.out.println("1: Adauga un CPU");
        System.out.println("2: Adauga un GPU");
        System.out.println("3: Adauga o Memorie RAM");
        System.out.println("4: Adauga o placa de baza");
        System.out.println("5: Adauga o sursa");
        System.out.println("6: Adauga un Storage device");
        int componentIndex=scanner.nextInt();
        scanner.nextLine();//Reads the /n character

        switch (componentIndex){
            case 1: {
                float price;
                float power;
                String name;
                System.out.println("Introdu numele: ");
                name = scanner.nextLine();
                System.out.println("Introdu pretul: ");
                price = scanner.nextFloat();
                System.out.println("Introdu consumul: ");
                power = scanner.nextFloat();
                CPU cpu = new CPU(price, power, name);
                Service.addComponent(cpu);
            }
            break;
            case 2:
            {
                float price;
                float power;
                String name;
                System.out.println("Introdu numele: ");
                name = scanner.nextLine();
                System.out.println("Introdu pretul: ");
                price = scanner.nextFloat();
                System.out.println("Introdu consumul: ");
                power = scanner.nextFloat();
                GPU gpu = new GPU(price, power, name);
                Service.addComponent(gpu);
            }
            break;

            case 3:{
                float price;
                float power;
                String name;
                System.out.println("Introdu numele: ");
                name = scanner.nextLine();
                System.out.println("Introdu pretul: ");
                price = scanner.nextFloat();
                System.out.println("Introdu consumul: ");
                power = scanner.nextFloat();
                Memory memory = new Memory(price, power, name);
                Service.addComponent(memory);
            }
            break;
            case 4:{
                float price;
                float power;
                String name;
                System.out.println("Introdu numele: ");
                name = scanner.nextLine();
                System.out.println("Introdu pretul: ");
                price = scanner.nextFloat();
                System.out.println("Introdu consumul: ");
                power = scanner.nextFloat();
                Motherboard motherboard = new Motherboard(price, power, name);
                Service.addComponent(motherboard);
            }
            break;
            case 5:{
                float price;
                String name;
                System.out.println("Introdu numele: ");
                name = scanner.nextLine();
                System.out.println("Introdu pretul: ");
                price = scanner.nextFloat();
                PowerSupply powerSupply = new PowerSupply(price, 0, name);
                Service.addComponent(powerSupply);
            }
            break;
            case 6:{
                float price;
                float power;
                String name;
                System.out.println("Introdu numele: ");
                name = scanner.nextLine();
                System.out.println("Introdu pretul: ");
                price = scanner.nextFloat();
                System.out.println("Introdu consumul: ");
                power = scanner.nextFloat();
                Storage storage = new Storage(price, power, name);
                Service.addComponent(storage);
            }
            break;
            default:
            System.err.println("Invalid part");
            return;
        }
        System.out.println("Componenta a fost adaugata cu succes");

    }

    public static void main(String[] args) {
        PCBuild pcBuild=new PCBuild("Build");
        boolean exit=false;
        while(!exit){
            System.out.println("Introdu actiunea: ");
            System.out.println("0: iesi din program");
            System.out.println("1: afiseaza pc ul actual ");
            System.out.println("2: adauga componenta");
            System.out.println("3: afiseaza componentele");
            System.out.println("4: instaleaza componenta in PC");
            int action=scanner.nextInt();
            switch (action){
                case 0:
                    exit=true;
                break;
                case 1:
                    System.out.println(pcBuild);
                break;
                case 2:
                    adaugaComponenta();
                break;
                case 3:
                    var components=Service.getComponents();
                    System.out.println("Sunt in total "+ components.size()+" componente");
                    for(var component:components) {
                        System.out.println(component);
                    }

                break;
                case 4:
                    addComponentToPC(pcBuild);
                break;
                default:
                    System.err.println("Actiunea "+action+" nu exita");
                break;
            }


        }
    }

    private static void addComponentToPC(PCBuild pcBuild) {
        System.out.println("Introdu componenta: ");
        System.out.println("1:CPU");
        System.out.println("2:GPU");
        System.out.println("3:Memory");
        System.out.println("4:Motherboard");
        System.out.println("5:PowerSupply");
        System.out.println("6:Storage");
        int componentToAdd=scanner.nextInt();
        switch (componentToAdd){
            case 1: {
                var cpus=Service.getComponentOfType(CPU.class).toArray();
                System.out.println("care procesor?");
                for(int i=0;i<cpus.length;i++) {
                    System.out.println(i + ":" + cpus[i].toString());
                }
                var index=scanner.nextInt();
                if(index<0||index>=cpus.length)
                    System.err.println("Invalid index");
                else{
                    pcBuild.setCpu((CPU)cpus[index]);
                }
            }   break;
            case 2:{
                var gpus=Service.getComponentOfType(GPU.class).toArray();
                System.out.println("care placa video?");
                for(int i=0;i<gpus.length;i++) {
                    System.out.println(i + ":" + gpus[i].toString());
                }
                var index=scanner.nextInt();
                if(index<0||index>=gpus.length)
                    System.err.println("Invalid index");
                else{
                    pcBuild.setGpu((GPU)gpus[index]);
                }
            }
                break;
            case 3:{
                var memory=Service.getComponentOfType(Memory.class).toArray();
                System.out.println("care memorie RAM?");
                for(int i=0;i<memory.length;i++) {
                    System.out.println(i + ":" + memory[i].toString());
                }
                var index=scanner.nextInt();
                if(index<0||index>=memory.length)
                    System.err.println("Invalid index");
                else{
                    pcBuild.setMemory((Memory)memory[index]);
                }
            }
                break;
            case 4:{
                var motherboards=Service.getComponentOfType(Motherboard.class).toArray();
                System.out.println("care placa de baza?");
                for(int i=0;i<motherboards.length;i++) {
                    System.out.println(i + ":" + motherboards[i].toString());
                }
                var index=scanner.nextInt();
                if(index<0||index>=motherboards.length)
                    System.err.println("Invalid index");
                else{
                    pcBuild.setMotherboard((Motherboard)motherboards[index]);
                }
            }
                break;
            case 5:{
                var powerSupply=Service.getComponentOfType(PowerSupply.class).toArray();
                System.out.println("care sursa?");
                for(int i=0;i<powerSupply.length;i++) {
                    System.out.println(i + ":" + powerSupply[i].toString());
                }
                var index=scanner.nextInt();
                if(index<0||index>=powerSupply.length)
                    System.err.println("Invalid index");
                else{
                    pcBuild.setPowerSupply((PowerSupply)powerSupply[index]);
                }
            }
                break;
            case 6:{
                var storage=Service.getComponentOfType(Storage.class).toArray();
                System.out.println("care placa video?");
                for(int i=0;i<storage.length;i++) {
                    System.out.println(i + ":" + storage[i].toString());
                }
                var index=scanner.nextInt();
                if(index<0||index>=storage.length)
                    System.err.println("Invalid index");
                else{
                    var storageArrayList=new ArrayList<Storage>();
                    storageArrayList.add(((Storage)storage[index]));
                    pcBuild.setStorage(storageArrayList);
                }
            }
            break;
            default:
            System.err.println("Id Invalid");
        }
    }
}