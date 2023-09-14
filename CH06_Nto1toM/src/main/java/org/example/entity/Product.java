package org.example.entity;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Product {

    @Id
    //@Column(name = "PRODUCT_ID")
    private String id;

    private String name;

    public  Product() {

    }

    @ManyToMany(mappedBy="products")
    private List<Member> members = new ArrayList<>(); // 필드 초기화


    public String getId() {
        return id;
    }

    public void setId(String  id) {
        this.id = id;
    }

    public String getName() {
        return  name;
    }

    public void setName(String name) {
        this.name= name;
    }

    public void setMembers(List<Member> members) {
        this.members =  members;
    }

    public List<Member> getMembers() {
        return members;
    }
}
