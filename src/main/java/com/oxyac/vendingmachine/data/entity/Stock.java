package com.oxyac.vendingmachine.data.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="stock")
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    @JsonProperty
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "config_id", referencedColumnName = "id")
    private StockConfig config;

    @JsonProperty
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "stock_id", referencedColumnName = "id")
    private List<Item> items;


    @JsonProperty
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vm_id", referencedColumnName = "id")
    private VendingMachine vendingMachine;


    public Stock(StockConfig config) {
        this.config = config;
    }

    public Stock() {

    }

    public VendingMachine getVendingMachine() {
        return vendingMachine;
    }

    public void setVendingMachine(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public StockConfig getConfig() {
        return config;
    }

    public void setConfig(StockConfig config) {
        this.config = config;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
