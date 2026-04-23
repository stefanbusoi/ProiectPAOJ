import java.util.Comparator;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    static void adaugaComponenta(){
        System.out.println("Introdu componenta: ");
        System.out.println("1:CPU");
        System.out.println("2:GPU");
        System.out.println("3:Memory");
        System.out.println("4:Motherboard");
        System.out.println("5:PowerSupply");
        System.out.println("6:Storage");
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
                float power;
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
        while(true){
            System.out.println("Introdu actiunea: ");
            System.out.println("1: afiseaza pc ul actual ");
            System.out.println("2: adauga componenta ");
            System.out.println("3: afiseaza componentele");
            int action=scanner.nextInt();
            switch (action){
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

                break;
                default:
                    System.err.println("Actiunea "+action+" nu exita");
                break;
            }


        }
    }
}