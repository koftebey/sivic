package com.em.sivicapp.sivic.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "game_id", referencedColumnName = "id")
    private Game game;
    private BigDecimal oldPrice;
    private BigDecimal newPrice;
    private BigDecimal percentage;
    private LocalDateTime tar;

    //


    @Override
    public String toString() {
        return "Record{" +
                "id=" + id +
                ", game=" + game +
                ", oldPrice=" + oldPrice +
                ", newPrice=" + newPrice +
                ", percentage=" + percentage +
                ", date=" + tar +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public BigDecimal getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(BigDecimal oldPrice) {
        this.oldPrice = oldPrice;
    }

    public BigDecimal getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(BigDecimal newPrice) {
        this.newPrice = newPrice;
    }

    public BigDecimal getPercentage() {
        return percentage;
    }

    public void setPercentage(BigDecimal percentage) {
        this.percentage = percentage;
    }

    public LocalDateTime getTar() {
        return tar;
    }

    public void setTar(LocalDateTime date) {
        this.tar = date;
    }
}
