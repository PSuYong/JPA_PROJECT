package org.Entity;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * User: HolyEyE
 * Date: 13. 5. 24. Time: 오후 7:43
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "findAllEmployees", query = "SELECT m FROM Member m"),
        @NamedQuery(name = "findEmployeeByName", query = "SELECT m FROM Member m WHERE m.team.name = :name")
})
public class Member {

    @Id
    //primary key
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    private String username;

//    @OneToMany(mappedBy="member")
//    private List<MemberProduct> memberProducts;

    private int age;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<Order>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEAM_ID")
    //COlumn이 하나 추가가 됨
    private Team team;

    public Member(String username) {
        this.username = username;
    }

    public Member() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }




    public String getUsername() {
        return  username;
    }

    public void setUsername(String  username) {
        this.username=  username;
    }




    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Order> getOrders(){return orders;}

    public void setOrders(List<Order> orders){this.orders = orders;}

    public void setTeam(Team team) {
        this.team = team;
    }

    public Team getTeam(){ return team;}

//    public List<MemberProduct> memberProducts() {
//        return  memberProducts();
//    }
//
//    public void setUsername( List<MemberProduct> memberProducts) {
//        this.memberProducts=  memberProducts;
//    }
//


}