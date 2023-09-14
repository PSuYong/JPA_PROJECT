package org.example.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Entity
public class Team{

        @Id
        @GeneratedValue
        @Column(name ="TEAM_ID")
        private Long id;

        private String name;

        public Team() {
        }

        public Team(String name) {
                this.name = name;
        }

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        @OneToMany
        @JoinColumn(name="TEAM_ID")
        private List<Member> members = new ArrayList<Member>();

        public List<Member> getMembers() {
                return members;
        }

        public void addMember(Member member) {
                this.members.add(member);
        }


}

