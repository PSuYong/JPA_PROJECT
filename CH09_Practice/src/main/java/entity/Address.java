package entity;

import javax.persistence.Column;
import javax.persistence.Embedded;

public class Address {

    @Column(name="city")
    private String city;
    private String street;
    //private String zipcode;

    @Embedded Zipcode zipcode;

}
