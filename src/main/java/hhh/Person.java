package hhh;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("person")
public class Person {
    
    @Value("Name")
    private String name;

    @Value("last name")
    private String lassName;

    @Value("12")
    private byte age;

    private Address address;

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    private List<Address> addresses;

    public String getName() {
        return name;
    }

    public String getLassName() {
        return lassName;
    }

    public byte getAge() {
        return age;
    }

    public Address getAddress() {
        return address;
    }

    @Autowired
    public void setAddress(Address address) {
        this.address = address;
    }
}
