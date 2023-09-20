package entity;

import javax.persistence.*;
import java.util.ArrayList;
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

//        @OneToMany
//        private List<Member> members = new ArrayList<>();
//
//



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

}

