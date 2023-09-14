package jpabook.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TEAM")
public class Team{

        @Id
        @Column(name ="TEAM_ID")
        private String id;

        private String name;

        public Team() {
        }

        public Team(String id, String name) {
                this.id = id;
                this.name = name;
        }



        public String getId() {
                return id;
        }

        public void setId(String id) {
                this.id = id;
        }

        public String getname() {
                return name;
        }

        public void setname(String name) {
                this.name = name;
        }

}

