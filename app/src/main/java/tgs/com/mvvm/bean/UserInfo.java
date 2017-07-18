package tgs.com.mvvm.bean;

/**
 * Created by 田桂森 on 2017/7/13.
 */

public class UserInfo {
    private String name;
    private int age;
    
    @Override
    public String toString() {
        return "UserInfo{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
    
    public UserInfo(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public int getAge() {
        return age;
    }
    
    public void setAge(int age) {
        this.age = age;
    }
}
