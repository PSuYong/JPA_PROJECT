package org.example.entity;

import javax.persistence.*;

/**
 * User: HolyEyE
 * Date: 13. 5. 24. Time: 오후 7:43
 */
@Entity
public class Member {

    @Id
    @GeneratedValue
    //primary key
    @Column(name = "MEMBER_ID")
    private Long id;
    private String username;

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
        this.username =  username;
    }


    @OneToOne
    @JoinColumn(name = "LOCKER_ID")
    private Locker locker;


    public void setLocker(Locker locker) {
        this.locker = locker;
    }


    public Locker getLocker() {
        return locker;
    }
}