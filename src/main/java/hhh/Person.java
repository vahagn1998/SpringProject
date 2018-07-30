package hhh;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("person")
public class Person {
    
    @Value("Name")
    private String name;

    @Value("last name")
    private String lassName;

    @Value("12")
    private byte age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLassName() {
        return lassName;
    }

    public void setLassName(String lassName) {
        this.lassName = lassName;
    }

    public byte getAge() {
        return age;
    }

    public void setAge(byte age) {
        this.age = age;
    }
}
