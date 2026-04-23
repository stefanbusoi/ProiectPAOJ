public class Memory extends PCComponent {
    Memory(float price, float powerUsage, String name) {
        super(price, powerUsage, name);
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
