import java.util.ArrayList;
import java.util.List;

public class PCBuild {
    private String buildName;
    private Motherboard motherboard;
    private CPU cpu;
    private GPU gpu;
    private Memory memory;
    private PowerSupply powerSupply;
    private List<Storage> storage;

    PCBuild(String buildName){
        this.buildName=buildName;
        storage=new ArrayList<Storage>();
    }

    @Override
    public String toString() {
        StringBuilder builder;
        builder= new StringBuilder("Name:");
        builder.append(buildName);
        builder.append("|Motherboard:");
        if(motherboard==null)
            builder.append("None");
        else
            builder.append(motherboard.getName());
        builder.append("|CPU:");
        if(cpu==null)
            builder.append("None");
        else
            builder.append(cpu.getName());
        builder.append("|GPU:");
        if(gpu==null)
            builder.append("None");
        else
            builder.append(gpu.getName());
        builder.append("|Power Supply:");
        if(powerSupply==null)
            builder.append("None");
        else
            builder.append(powerSupply.getName());
        builder.append("|Storage ");
        builder.append(storage.size());
        builder.append(" elements:{");
        for(var st:storage){
            builder.append(st.getName());
            builder.append(" ");
        }
        builder.append("}");

        builder.append("|Memory:");
        if(memory==null)
            builder.append("None");
        else
            builder.append(memory.getName());
        return builder.toString();
    }

    public boolean isFunctionalBuild(){
        if(motherboard==null)
            return false;
        if(cpu==null)
            return false;
        if(gpu==null)
            return false;
        if(memory==null)
            return false;
        if(powerSupply==null)
            return false;
        if(storage.isEmpty())
            return false;
        return true;
    }
    public float getTotalPrice(){
        float totalPrice=0;

        for(var storageUnit:storage) {
            totalPrice += storage.getFirst().getPrice();
        }
        return totalPrice;
    }

    public List<Storage> getStorage() {
        return storage;
    }
    public void setStorage(List<Storage> storage) {
        this.storage = storage;
    }
    public PowerSupply getPowerSupply() {
        return powerSupply;
    }
    public void setPowerSupply(PowerSupply powerSupply) {
        this.powerSupply = powerSupply;
    }
    public Memory getMemory() {
        return memory;
    }
    public void setMemory(Memory memory) {
        this.memory = memory;
    }
    public GPU getGpu() {
        return gpu;
    }
    public void setGpu(GPU gpu) {
        this.gpu = gpu;
    }
    public CPU getCpu() {
        return cpu;
    }
    public void setCpu(CPU cpu) {
        this.cpu = cpu;
    }
    public Motherboard getMotherboard() {
        return motherboard;
    }
    public void setMotherboard(Motherboard motherboard) {
        this.motherboard = motherboard;
    }
}
