import PCComponents.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public abstract class Service {
    private static PCBuild pcBuild=null;
    private static boolean dirtyPC=true;
    public static PCBuild getCurentBuild(){
         if(dirtyPC == true && pcBuild != null){
            pcBuild = getBuild(pcBuild.getBuildName());
            dirtyPC = false;
        }
        return pcBuild;
    }
    static public void setDirtyPC(){
        dirtyPC=true;
    }
    static public void addPCBuild(PCBuild build){
        Service.pcBuild=build;
        saveBuild(build);
    }
    
    static public void addComponent(PCComponent component) {
        AuditService.logAction("add_component");
        String query = "INSERT INTO components (name, price, power_usage, type, nr_of_storage_slots, processing_power, nr_of_cores, graphics_power, vram, capacity, speed, ram_slots, max_power, efficiency) " +
                       "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) " +
                       "ON CONFLICT (name) DO UPDATE SET " +
                       "price = EXCLUDED.price, " +
                       "power_usage = EXCLUDED.power_usage, " +
                       "nr_of_storage_slots = EXCLUDED.nr_of_storage_slots, " +
                       "processing_power = EXCLUDED.processing_power, " +
                       "nr_of_cores = EXCLUDED.nr_of_cores, " +
                       "graphics_power = EXCLUDED.graphics_power, " +
                       "vram = EXCLUDED.vram, " +
                       "capacity = EXCLUDED.capacity, " +
                       "speed = EXCLUDED.speed, " +
                       "ram_slots = EXCLUDED.ram_slots, " +
                       "max_power = EXCLUDED.max_power, " +
                       "efficiency = EXCLUDED.efficiency";
        try {
            Connection conn = DatabaseConnection.getConnection();
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, component.getName());
                pstmt.setFloat(2, component.getPrice());
                pstmt.setFloat(3, component.getPowerUsage());
                pstmt.setString(4, component.getClass().getSimpleName());
                if (component instanceof CPU) {
                    CPU cpu = (CPU) component;
                    pstmt.setNull(5, java.sql.Types.INTEGER);
                    pstmt.setFloat(6, cpu.getProcessingPower());
                    pstmt.setInt(7, cpu.getNrOfCores());

                    // Dacă este iCPU, setăm și partea grafică
                    if (component instanceof GraphicsIntegratedCPU) {
                        GraphicsIntegratedCPU icpu = (GraphicsIntegratedCPU) component;
                        pstmt.setFloat(8, icpu.getGraphicsPower());
                        pstmt.setFloat(9, icpu.getVRam());
                    } else {
                        pstmt.setNull(8, java.sql.Types.REAL);
                        pstmt.setNull(9, java.sql.Types.REAL);
                    }
                    pstmt.setNull(10, java.sql.Types.REAL);
                    pstmt.setNull(11, java.sql.Types.REAL);
                    pstmt.setNull(12, java.sql.Types.INTEGER);
                    pstmt.setNull(13, java.sql.Types.REAL);
                    pstmt.setNull(14, java.sql.Types.REAL);

                } else if (component instanceof GPU) {
                    GPU gpu = (GPU) component;
                    pstmt.setNull(5, java.sql.Types.INTEGER);
                    pstmt.setNull(6, java.sql.Types.REAL);
                    pstmt.setNull(7, java.sql.Types.INTEGER);
                    pstmt.setFloat(8, gpu.getGraphicsPower());
                    pstmt.setFloat(9, gpu.getVRam());
                    pstmt.setNull(10, java.sql.Types.REAL);
                    pstmt.setNull(11, java.sql.Types.REAL);
                    pstmt.setNull(12, java.sql.Types.INTEGER);
                    pstmt.setNull(13, java.sql.Types.REAL);
                    pstmt.setNull(14, java.sql.Types.REAL);

                } else if (component instanceof Memory) {
                    Memory memory = (Memory) component;
                    pstmt.setNull(5, java.sql.Types.INTEGER);
                    pstmt.setNull(6, java.sql.Types.REAL);
                    pstmt.setNull(7, java.sql.Types.INTEGER);
                    pstmt.setNull(8, java.sql.Types.REAL);
                    pstmt.setNull(9, java.sql.Types.REAL);
                    pstmt.setFloat(10, memory.getCapacity());
                    pstmt.setFloat(11, memory.getSpeed());
                    pstmt.setNull(12, java.sql.Types.INTEGER);
                    pstmt.setNull(13, java.sql.Types.REAL);
                    pstmt.setNull(14, java.sql.Types.REAL);

                } else if (component instanceof Motherboard) {
                    Motherboard mb = (Motherboard) component;
                    pstmt.setInt(5, mb.getNrOfStorageSlots());
                    pstmt.setNull(6, java.sql.Types.REAL);
                    pstmt.setNull(7, java.sql.Types.INTEGER);
                    pstmt.setNull(8, java.sql.Types.REAL);
                    pstmt.setNull(9, java.sql.Types.REAL);
                    pstmt.setNull(10, java.sql.Types.REAL);
                    pstmt.setNull(11, java.sql.Types.REAL);
                    pstmt.setInt(12, mb.getNrOfMemorySlots());
                    pstmt.setNull(13, java.sql.Types.REAL);
                    pstmt.setNull(14, java.sql.Types.REAL);

                } else if (component instanceof PowerSupply) {
                    PowerSupply ps = (PowerSupply) component;
                    pstmt.setNull(5, java.sql.Types.INTEGER);
                    pstmt.setNull(6, java.sql.Types.REAL);
                    pstmt.setNull(7, java.sql.Types.INTEGER);
                    pstmt.setNull(8, java.sql.Types.REAL);
                    pstmt.setNull(9, java.sql.Types.REAL);
                    pstmt.setNull(10, java.sql.Types.REAL);
                    pstmt.setNull(11, java.sql.Types.REAL);
                    pstmt.setNull(12, java.sql.Types.INTEGER);
                    pstmt.setFloat(13, ps.getMaxPower());
                    pstmt.setFloat(14, ps.getEfficiency());

                } else if (component instanceof Storage) {
                    Storage st = (Storage) component;
                    pstmt.setNull(5, java.sql.Types.INTEGER);
                    pstmt.setNull(6, java.sql.Types.REAL);
                    pstmt.setNull(7, java.sql.Types.INTEGER);
                    pstmt.setNull(8, java.sql.Types.REAL);
                    pstmt.setNull(9, java.sql.Types.REAL);
                    pstmt.setFloat(10, st.getCapacity());
                    pstmt.setNull(11, java.sql.Types.REAL);
                    pstmt.setNull(12, java.sql.Types.INTEGER);
                    pstmt.setNull(13, java.sql.Types.REAL);
                    pstmt.setNull(14, java.sql.Types.REAL);
                }
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.err.println("Eroare la adăugarea componentei în baza de date: " + e.getMessage());
        }
    }
    private static PCComponent createComponentFromRow(String type, String name, float price, float powerUsage,int storageSlots,float processingPower,int nrOfCores,float graphicsPower,float vRAM, float capacity,float speed,int ramSlots,float maxPower,float efficiency) {
        return switch (type) {

            case "CPU" -> new CPU(price, powerUsage, name,processingPower,nrOfCores);
            case "GPU" -> new GPU(price, powerUsage, name,graphicsPower,vRAM);
            case "Memory" -> new Memory(price, powerUsage, name,capacity,speed);
            case "Motherboard" -> new Motherboard(price, powerUsage, name,storageSlots,ramSlots);
            case "PowerSupply" -> new PowerSupply(price, name,maxPower,efficiency);
            case "Storage" -> new Storage(price, powerUsage, name,capacity);
            case "GraphicsIntegratedCPU" -> new GraphicsIntegratedCPU(price, powerUsage, name,processingPower,nrOfCores,graphicsPower    );
            default -> null;
        };
    }
    static public PCComponent getComponent(String name){
        if (name == null) return null;
        AuditService.logAction("Get_Component "+name);

        String query = "SELECT * FROM components WHERE name = ?";
        try {
            Connection conn = DatabaseConnection.getConnection();
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, name);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        String type = rs.getString("type");
                        float price = rs.getFloat("price");
                        float powerUsage = rs.getFloat("power_usage");
                        int nrOfStorageSlots = rs.getInt("nr_of_storage_slots");
                        float processingPower = rs.getFloat("processing_power");
                        int nrOfCores = rs.getInt("nr_of_cores");
                        float graphicsPower = rs.getFloat("graphics_power");
                        float vRAM = rs.getFloat("vram");
                        float capacity = rs.getFloat("capacity");
                        float speed = rs.getFloat("speed");
                        int ramSlots = rs.getInt("ram_slots");
                        float maxPower = rs.getFloat("max_power");
                        float efficiency = rs.getFloat("efficiency");
                        return createComponentFromRow(type, name, price, powerUsage,nrOfStorageSlots,processingPower,nrOfCores,graphicsPower,vRAM, capacity,speed,ramSlots,maxPower,efficiency);
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Eroare la citirea componentei: " + e.getMessage());
        }
        return null;
    }
    static public List<PCComponent> getComponents() {
        List<PCComponent> componentsList = new ArrayList<>();
        AuditService.logAction("Get_Components");
        String query = "SELECT * FROM components ORDER BY name ASC";
        try {
            Connection conn = DatabaseConnection.getConnection();
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(query)) {
                while (rs.next()) {
                    String name = rs.getString("name");
                    float price = rs.getFloat("price");
                    float powerUsage = rs.getFloat("power_usage");
                    String type = rs.getString("type");
                    int nrOfStorageSlots = rs.getInt("nr_of_storage_slots");
                    float processingPower = rs.getFloat("processing_power");
                    int nrOfCores = rs.getInt("nr_of_cores");
                    float graphicsPower = rs.getFloat("graphics_power");
                    float vRAM = rs.getFloat("vram");
                    float capacity = rs.getFloat("capacity");
                    float speed = rs.getFloat("speed");
                    int ramSlots = rs.getInt("ram_slots");
                    float maxPower = rs.getFloat("max_power");
                    float efficiency = rs.getFloat("efficiency");

                    componentsList.add(createComponentFromRow(type, name, price, powerUsage,nrOfStorageSlots,processingPower,nrOfCores,graphicsPower,vRAM, capacity,speed,ramSlots,maxPower,efficiency));
                }
            }
        } catch (SQLException e) {
            System.err.println("Eroare la citirea componentelor: " + e.getMessage());
        }
        return componentsList;
    }
    static public <T extends PCComponent> List<T> getComponentOfType(Class<T> type) {
        List<T> list = new ArrayList<>();
        String query = "SELECT * FROM components WHERE type = ?";
        try {
            Connection conn = DatabaseConnection.getConnection();
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, type.getSimpleName());
                try (ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        String name = rs.getString("name");
                        float price = rs.getFloat("price");
                        float powerUsage = rs.getFloat("power_usage");
                        int nrOfStorageSlots = rs.getInt("nr_of_storage_slots");
                        float processingPower = rs.getFloat("processing_power");
                        int nrOfCores = rs.getInt("nr_of_cores");
                        float graphicsPower = rs.getFloat("graphics_power");
                        float vRAM = rs.getFloat("vram");
                        float capacity = rs.getFloat("capacity");
                        float speed = rs.getFloat("speed");
                        int ramSlots = rs.getInt("ram_slots");
                        float maxPower = rs.getFloat("max_power");
                        float efficiency = rs.getFloat("efficiency");
                        PCComponent component = createComponentFromRow(type.getSimpleName(), name, price, powerUsage,nrOfStorageSlots,processingPower,nrOfCores,graphicsPower,vRAM, capacity,speed,ramSlots,maxPower,efficiency);
                        if (type.isInstance(component)) {
                            list.add(type.cast(component));
                        }
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Eroare la filtrarea componentelor după tip: " + e.getMessage());
        }
        return list;
    }
    static public void deleteComponent(String name) {
        dirtyPC=true;
        String query = "DELETE FROM components WHERE name = ?";
        try {
            Connection conn = DatabaseConnection.getConnection();
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {

                pstmt.setString(1, name);
                int rowsAffected = pstmt.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Componenta '" + name + "' a fost ștearsă cu succes.");
                } else {
                    System.out.println("Componenta '" + name + "' nu a fost găsită în baza de date.");
                }
            }
        } catch (SQLException e) {
            System.err.println("Eroare la ștergerea componentei din baza de date: " + e.getMessage());
        }
    }
    static public List<PCBuild> getBuildOptions(){
        List<PCBuild> builds = new ArrayList<>();
        String query = "SELECT build_name,motherboard_name,cpu_name,gpu_name,memory_name,power_supply_name FROM pc_builds ORDER BY build_name ASC";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                String buildName = rs.getString("build_name");
                String motherboardName = rs.getString("motherboard_name");
                String cpuName = rs.getString("cpu_name");
                String gpuName = rs.getString("gpu_name");
                String memoryName = rs.getString("memory_name");
                String powerSupplyName = rs.getString("power_supply_name");
                PCBuild build = new PCBuild(buildName);
                build.setMotherboard((Motherboard)getComponent(motherboardName));
                build.setCpu((CPU)getComponent(cpuName));
                build.setGpu((GPU)getComponent(gpuName));
                build.setMemory((Memory)getComponent(memoryName));
                build.setPowerSupply((PowerSupply)getComponent(powerSupplyName));

                String storageQuery = "SELECT storage_name FROM build_storage WHERE build_name = ?";
                try (PreparedStatement storagePstmt = conn.prepareStatement(storageQuery)) {
                    storagePstmt.setString(1, buildName);
                    try (ResultSet storageRs = storagePstmt.executeQuery()) {
                        List<Storage> storages = new ArrayList<>();
                        while (storageRs.next()) {
                            Storage s = (Storage) getComponent(storageRs.getString("storage_name"));
                            if (s != null) {
                                storages.add(s);
                            }
                        }
                        build.setStorage(storages);
                    }
                }
                builds.add(build);
            }
        } catch (SQLException e) {
            System.err.println("Eroare la citirea PCBuilds: " + e.getMessage());
        }
        return builds;
    }
    static public PCBuild getBuild(String buildName){
        String query = "SELECT * FROM pc_builds WHERE build_name = ?";
        try {
            Connection conn = DatabaseConnection.getConnection();
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, buildName);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        String motherboardName = rs.getString("motherboard_name");
                        String cpuName = rs.getString("cpu_name");
                        String gpuName = rs.getString("gpu_name");
                        String memoryName = rs.getString("memory_name");
                        String powerSupplyName = rs.getString("power_supply_name");
                        PCBuild build = new PCBuild(buildName);
                        build.setMotherboard((Motherboard)getComponent(motherboardName));
                        build.setCpu((CPU)getComponent(cpuName));
                        build.setGpu((GPU)getComponent(gpuName));
                        build.setMemory((Memory)getComponent(memoryName));
                        build.setPowerSupply((PowerSupply)getComponent(powerSupplyName));
                        String storageQuery = "SELECT storage_name FROM build_storage WHERE build_name = ?";
                        try (PreparedStatement storagePstmt = conn.prepareStatement(storageQuery)) {
                            storagePstmt.setString(1, buildName);
                            try (ResultSet storageRs = storagePstmt.executeQuery()) {
                                List<Storage> storages = new ArrayList<>();
                                while (storageRs.next()) {
                                    Storage s = (Storage) getComponent(storageRs.getString("storage_name"));
                                    if (s != null) {
                                        storages.add(s);
                                    }
                                }
                                build.setStorage(storages);
                            }
                        }
                        return build;
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Eroare la citirea PCBuilds: " + e.getMessage());
        }
        return null;
    }
    static public void saveBuild(PCBuild pcBuild){
        dirtyPC=true;
        AuditService.logAction("save_build");
        String query = "INSERT INTO pc_builds (build_name, motherboard_name, cpu_name, gpu_name, memory_name, power_supply_name) " +
                "VALUES (?, ?, ?, ?, ?, ?) " +
                "ON CONFLICT (build_name) " +
                "DO UPDATE SET " +
                "motherboard_name = EXCLUDED.motherboard_name, " +
                "cpu_name = EXCLUDED.cpu_name, " +
                "gpu_name = EXCLUDED.gpu_name, " +
                "memory_name = EXCLUDED.memory_name, " +
                "power_supply_name = EXCLUDED.power_supply_name";        try {
            Connection conn = DatabaseConnection.getConnection();
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, pcBuild.getBuildName());
                if(pcBuild.getMotherboard()==null){
                    pstmt.setNull(2,Types.VARCHAR);
                }
                else{
                    pstmt.setString(2, pcBuild.getMotherboard().getName());
                }
                if(pcBuild.getCpu()==null){
                    pstmt.setNull(3, Types.VARCHAR);
                }
                else{
                    pstmt.setString(3, pcBuild.getCpu().getName());
                }
                if(pcBuild.getGpu()==null){
                    pstmt.setNull(4,Types.VARCHAR);
                }
                else{
                    pstmt.setString(4, pcBuild.getGpu().getName());
                }
                if(pcBuild.getMemory()==null){
                    pstmt.setNull(5,Types.VARCHAR);
                }
                else{
                    pstmt.setString(5, pcBuild.getMemory().getName());
                }
                if(pcBuild.getPowerSupply()==null){
                    pstmt.setNull(6,Types.VARCHAR);
                }
                else{
                    pstmt.setString(6, pcBuild.getPowerSupply().getName());
                }
                pstmt.executeUpdate();
            }
            //TODO: update the storage table
            String deleteQuery = "DELETE FROM build_storage WHERE build_name = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(deleteQuery)) {
                pstmt.setString(1, pcBuild.getBuildName());
                pstmt.executeUpdate();
            }
            String insertQuery = "INSERT INTO build_storage (build_name, storage_name) VALUES (?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(insertQuery)) {
                for (Storage storage : pcBuild.getStorage()) {
                    pstmt.setString(1, pcBuild.getBuildName());
                    pstmt.setString(2, storage.getName());
                    pstmt.executeUpdate();
                }
            }
        } catch (SQLException e) {
            System.err.println("Eroare la salvarea PCBuilds: " + e.getMessage());
        }
    }
}
