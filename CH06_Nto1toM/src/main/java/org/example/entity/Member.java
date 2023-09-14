package org.example.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * User: HolyEyE
 * Date: 13. 5. 24. Time: 오후 7:43
 */
@Entity
public class Member {

    @Id
    //primary key
    @Column(name = "MEMBER_ID")
    private String id;

    private String username;

    @ManyToMany
    @JoinTable(
            name = "MEMBER_PRODUCT",
            joinColumns = @JoinColumn(name = "MEMBER_ID"),
            inverseJoinColumns = @JoinColumn(name = "PRODUCT_ID"))
    private List<Product> products = new ArrayList<>();

    public Member(String username) {
        this.username = username;
    }

    public Member() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return  username;
    }

    public void setUsername(String  username) {
        this.username=  username;
    }


    public void setProducts(List<Product> products) {
        this.products =  products;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product){
        products.add(product);
        product.getMembers().add(this);
    }
}