package PCComponents;

public class GraphicsIntegratedCPU extends CPU implements GraphicsCapability{


    public GraphicsIntegratedCPU(float price, float power, String name) {
        super(price, power, name);
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
        //TODO:IMPLEMENT
        return 0;
    }
}
