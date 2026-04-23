public class CPU extends PCComponent{

    public CPU(float price,float power,String name){
        super(price,power,name);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder=new StringBuilder("CPU{");
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
