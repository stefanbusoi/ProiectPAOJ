package PCComponents;

public class GPU extends PCComponent implements GraphicsCapability {
    public GPU(float price, float powerUsage, String name) {
        super(price, powerUsage, name);
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
        //TODO:IMPLEMENT
        return 0;
    }

    @Override
    public float getVRam() {
        //TODO:IMPLEMENT
        return 0;
    }

    @Override
    public float getPowerConsumption() {
        return getPowerUsage();
    }
}
