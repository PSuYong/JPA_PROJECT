package org.Entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Product {


    @Id
    @GeneratedValue
    @Column(name="PRODUCT_ID")
    private Long id;

    private String name;

//    @OneToMany(mappedBy="member")
//    private List<MemberProduct> memberProducts;

    @OneToMany(mappedBy = "product")
    private List<Order> orders = new ArrayList<Order>();

    private int price;

    private int stockAmount;

    public Product (String name) {
        this.name = name;
    }

    public Product () {

    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }





    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name=  name;
    }




    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStockAmount() {
        return stockAmount;
    }

    public void setStockAmount(int stockAmount) {
        this.stockAmount = stockAmount;
    }
}
