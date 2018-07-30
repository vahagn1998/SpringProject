package hhh;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("address")
public class Address {

    @Value("10")
    private int numberOfFlat;
    private String street;

    public int getNumberOfFlat() {
        return numberOfFlat;
    }

    public void setNumberOfFlat(int numberOfFlat) {
        this.numberOfFlat = numberOfFlat;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
