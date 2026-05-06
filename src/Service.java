import PCComponents.PCComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public abstract class Service {
    static private TreeSet<PCComponent> pcComponents=new TreeSet<>();

    static public void addComponent(PCComponent component){
        pcComponents.add(component);
    }


    static public  <T extends PCComponent> List<T> getComponentOfType(Class<T> type){
        List<T> list=new ArrayList<>();
        for(PCComponent component:pcComponents) {
            if (type.isInstance(component)) {
                list.add(type.cast(component));
            }
        }
        return list;
    }
    static public List<PCComponent> getComponents(){
        List<PCComponent> componentsList=new ArrayList<PCComponent>();
        for(PCComponent component:pcComponents) {
            componentsList.add(component);
        }
        return componentsList;
    }


}
