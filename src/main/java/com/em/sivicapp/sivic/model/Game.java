package com.em.sivicapp.sivic.model;

import javax.annotation.processing.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int zoid;

    private String name;
    private LocalDateTime tar;


    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", zoid=" + zoid +
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


    public int getZoid() {
        return zoid;
    }

    public void setZoid(int zoid) {
        this.zoid = zoid;
    }
}
