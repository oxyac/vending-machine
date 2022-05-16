package com.oxyac.vendingmachine.data.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name = "session")
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    private LocalDateTime datetimeStarted;

    private LocalDateTime datetimeStopped;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "item_id", referencedColumnName = "id")
    private Item item;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vm_id", referencedColumnName = "id")
    private VendingMachine vendingMachine;

    public Session() {
        this.datetimeStarted = LocalDateTime.now();
    }


    public LocalDateTime getDatetimeStarted() {
        return datetimeStarted;
    }

    public void setDatetimeStarted(LocalDateTime datetimeStarted) {
        this.datetimeStarted = datetimeStarted;
    }

    public LocalDateTime getDatetimeStopped() {
        return datetimeStopped;
    }

    public void setDatetimeStopped(LocalDateTime datetimeStopped) {
        this.datetimeStopped = datetimeStopped;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

}