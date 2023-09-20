package org.example.entity;

import javax.persistence.*;

/**
 * User: HolyEyE
 * Date: 13. 5. 24. Time: 오후 7:43
 */
@Entity
@Table(name="MEMBER")
public class Member {

    @Id
    @GeneratedValue
    @Column(name= "MEMBER_ID")
    private Long id; // 숫자형 타입으로 변경

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    private String username;

    public Member(String username) {
        this.username = username;
    }

    public Member() {

    }

    public Long getId(){return id;}
    public void setId(Long id) {
        this.id =  id;
    }


    public String getUsername() {
        return  username;
    }

    public void setUsername(String  username) {
        this.username=  username;
    }


    public void setTeam(Team team) {
        this.team =  team;
    }

    public Team getTeam() {
        return team;
    }


}