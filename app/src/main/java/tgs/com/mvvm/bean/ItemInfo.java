package tgs.com.mvvm.bean;

/**
 * Created by 田桂森 on 2017/7/17.
 */

public class ItemInfo {
    private  String name;
    
    public ItemInfo(String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
        return "ItemInfo{" +
                "name='" + name + '\'' +
                '}';
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
}
