package org.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserDTO {
    private String username;
    private int age;
    @Id
    private Long id;

    public UserDTO(String username, int age){
        this.username = username;
        this.age = age;
    }

    public UserDTO() {

    }


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
