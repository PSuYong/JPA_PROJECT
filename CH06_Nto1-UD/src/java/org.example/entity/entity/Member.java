package org.example.entity.entity;

import javax.persistence.*;
import java.util.TimeZone;

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

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    public Member(String username) {
        this.id = id;
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


    public void setTeam(Team team1) {
        this.team =  team1;
    }

    public Team getTeam() {
        return team;
    }
}