package entity;

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

    @OneToMany(mappedBy="member")
    private List<MemberProduct> memberProducts;

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

    public List<MemberProduct> memberProducts() {
        return  memberProducts();
    }

    public void setUsername( List<MemberProduct> memberProducts) {
        this.memberProducts=  memberProducts;
    }



}