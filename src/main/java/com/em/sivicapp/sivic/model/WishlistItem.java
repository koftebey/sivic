package com.em.sivicapp.sivic.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class WishlistItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private LocalDateTime tar;



    @Override
    public String toString() {
        return "WishlistItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tar=" + tar +
                '}';
    }

    public int getId() {
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

    public LocalDateTime getTar() {
        return tar;
    }

    public void setTar(LocalDateTime tar) {
        this.tar = tar;
    }


}
