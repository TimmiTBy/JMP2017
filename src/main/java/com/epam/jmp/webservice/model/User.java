package com.epam.jmp.webservice.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Ales on 19.03.2017.
 */
@Entity
@Table(name = "USERS")
public class User implements Serializable {

    private static final long serialVersionUID = 6893005999655239805L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "user_auto_increment")
    @SequenceGenerator(name = "user_auto_increment", sequenceName = "AUTO_INCREMENT")
    int id;
    @Column(name = "name")
    String name;

    public User() {
    }

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
