package org.example.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TEAM")
public class Team{


        private String name;
        @Id
        @GeneratedValue
        @Column(name="TEAM_ID")
        private Long id;

//        @OneToMany(mappedBy = "team",fetch=FetchType.EAGER)
//        private List<Member> members = new ArrayList<>();

        public Team(String name) {
                this.name = name;
        }


        public Team() {

        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public Long getId() {
                return id;
        }
}

