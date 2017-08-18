package tgs.com.mvvm.bean;

/**
 * Created by 田桂森 on 2017/8/3.
 */
public class BannerInfo {
    
    private String title;
    private String value;
    private String image;
    private int type;
    private int weight;
    private String remark;
    private String hash;
    
    @Override
    public String toString() {
        return "BannerInfo{" +
                "title='" + title + '\'' +
                ", value='" + value + '\'' +
                ", image='" + image + '\'' +
                ", type=" + type +
                ", weight=" + weight +
                ", remark='" + remark + '\'' +
                ", hash='" + hash + '\'' +
                '}';
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getValue() {
        return value;
    }
    
    public void setValue(String value) {
        this.value = value;
    }
    
    public String getImage() {
        return image;
    }
    
    public void setImage(String image) {
        this.image = image;
    }
    
    public int getType() {
        return type;
    }
    
    public void setType(int type) {
        this.type = type;
    }
    
    public int getWeight() {
        return weight;
    }
    
    public void setWeight(int weight) {
        this.weight = weight;
    }
    
    public String getRemark() {
        return remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    public String getHash() {
        return hash;
    }
    
    public void setHash(String hash) {
        this.hash = hash;
    }
}
