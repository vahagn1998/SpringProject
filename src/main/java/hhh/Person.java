package hhh;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.security.MessageDigest;
import java.util.List;

@Service("person")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class Person implements DisposableBean, BeanNameAware {

    private MessageDigest digest;
    
    @Value("Name")
    private String name;

    @Value("last name")
    private String lassName;

    @InjectRandomInt(max = 100)
    private int age;

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

    public int getAge() {
        return age;
    }

    public Address getAddress() {
        return address;
    }

    @Autowired
    public void setDigest(MessageDigest digest) {
        this.digest = digest;
    }

    public void digest(String msg) {
        System.out.println( "Using alogrithm: " + digest.getAlgorithm());
        digest.reset();
        byte[] bytes = msg.getBytes();
        byte[] out = digest.digest(bytes);
        System.out.println(out);
    }

    @Autowired
    public void setAddress(Address address) {
        this.address = address;
    }

    @PostConstruct
    public void init() {
        if(address.getStreet() != null) {
            System.out.println("lava");
        } else {
            System.out.println("vata");
        }
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("sfds");
    }

    @Override
    public void setBeanName(String name) {
        System.out.println(name);
    }
}
