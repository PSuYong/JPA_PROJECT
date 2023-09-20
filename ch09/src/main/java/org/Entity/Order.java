package org.Entity;

import javax.persistence.*;

@Entity
@Table(name ="ORDERS")
public class Order {

    @Id
    @GeneratedValue
    @Column(name="ORDER_ID")
    private Long id;

//        @OneToMany(mappedBy = "team",fetch=FetchType.EAGER)
//        private List<Member> members = new ArrayList<>();

    private int orderAmount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    @Embedded
    private Address address;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }


    public int getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(int orderAmount) {
        this.orderAmount = orderAmount;
    }


    public Address getAddress() {
        return address;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
