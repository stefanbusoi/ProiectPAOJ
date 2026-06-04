import PCComponents.*;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
   static void selectOrAddPC(){
        var buildOptions = Service.getBuildOptions();
       AuditService.logAction("get_build_options");

       for(int i = 0; i < buildOptions.size(); i++){
            System.out.println(i + ":" + buildOptions.get(i).toString());
        }
        System.out.println("Apasa -1 pentru a adauga un PC nou");
        var index = scanner.nextInt();
        scanner.nextLine();
        if(index == -1){
            System.out.println("Introdu numele noului PC: ");
            String name = scanner.nextLine();
            PCBuild newPC = new PCBuild(name);
            Service.addPCBuild(newPC);
            System.out.println("PC-ul '" + name + "' a fost creat ca PC activ.");
            return;
        }
        if(index < 0 || index >= buildOptions.size()){
            System.err.println("Index invalid!");
            return;
        }
        Service.addPCBuild(buildOptions.get(index));
        System.out.println("A fost selectat PC-ul: " + buildOptions.get(index).getBuildName());
    }
    static void adaugaComponenta(){
        System.out.println("1: Adauga un CPU");
        System.out.println("2: Adauga un GPU");
        System.out.println("3: Adauga o Memorie RAM");
        System.out.println("4: Adauga o placa de baza");
        System.out.println("5: Adauga o sursa");
        System.out.println("6: Adauga un Storage device");
        int componentIndex=scanner.nextInt();
        scanner.nextLine();

        switch (componentIndex){
            case 1: {
                System.out.println("1: CPU are placa video integrata");
                System.out.println("2: CPU nu are placa video integrata");
                int option=scanner.nextInt();
                if(option!=1&&option!=2){
                    System.err.println("Invalid option");
                    break;
                }
                scanner.nextLine();
                if(option==1){
                    GraphicsIntegratedCPU graphicsIntegratedCPU = GraphicsIntegratedCPU.readComponent(scanner);
                    Service.addComponent(graphicsIntegratedCPU);
                }
                else if(option==2){
                    CPU cpu = CPU.readComponent(scanner);
                    Service.addComponent(cpu);
                }
            }
            break;
            case 2:
            {
                GPU gpu = GPU.readComponent(scanner);
                Service.addComponent(gpu);
            }
            break;

            case 3:{
                Memory memory = Memory.readComponent(scanner);
                Service.addComponent(memory);
            }
            break;
            case 4:{
                Motherboard motherboard = Motherboard.readComponent(scanner);
                Service.addComponent(motherboard);
            }
            break;
            case 5:{
                PowerSupply powerSupply = PowerSupply.readComponent(scanner);
                Service.addComponent(powerSupply);
            }
            break;
            case 6:{
                Storage storage = Storage.readComponent(scanner);
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
        boolean exit=false;
        while(!exit){
            System.out.println("Introdu actiunea: ");
            System.out.println("0: iesi din program");
            System.out.println("1: afiseaza pc ul actual ");
            System.out.println("2: adauga componenta");
            System.out.println("3: afiseaza componentele");
            System.out.println("4: instaleaza componenta in PC");
            System.out.println("5: selecteaza pc ul pe care vrei sa il modifici");
            System.out.println("6: sterge o componenta");
            System.out.println("7: reseteaza baza de date la seed data");
            int action=scanner.nextInt();
            switch (action){
                case 0:
                    DatabaseConnection.closeConnection();
                    exit=true;
                break;
                case 1:
                    PCBuild build=Service.getCurentBuild();
                    if(build==null){
                        System.out.println("Nu ai nici un build selectat");
                    }else {
                        System.out.println(build);
                    }
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
                    addComponentToPC(Service.getCurentBuild());
                break;
                case 5:
                    selectOrAddPC();
                break;
                case 6:
                    deleteComponent();
                break;
                case 7:
                    DatabaseConnection.resetDatabase();
                break;
                default:
                    System.err.println("Actiunea "+action+" nu exita");
                break;
            }


        }
    }

    private static void deleteComponent() {
        AuditService.logAction("delete_component");

        var components = Service.getComponents();
        if (components.isEmpty()) {
            System.out.println("Nu există componente în baza de date pe care să le ștergi.");
            return;
        }

        System.out.println("Selectează componenta pe care dorești să o ștergi:");
        for (int i = 0; i < components.size(); i++) {
            var comp = components.get(i);
            System.out.println(i + ": " + comp.getName() + " [" + comp.getClass().getSimpleName() + "]");
        }

        int index = scanner.nextInt();
        scanner.nextLine();

        if (index < 0 || index >= components.size()) {
            System.err.println("Index invalid!");
            return;
        }

        String nameToDelete = components.get(index).getName();
        Service.deleteComponent(nameToDelete);
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
                System.out.println("care procesor?");
                var cpus=Service.getComponentOfType(CPU.class).toArray();
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
                System.out.println("care stocare?");
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
        Service.saveBuild(pcBuild);
    }
    
}