package com.oxyac.vendingmachine.data.entity;


import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Component
public class VendingMachine {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "stock_id", referencedColumnName = "id")
    private Stock stock;

    private Long depositedAmount;

    private Boolean transactionInProgress;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public Long getDepositedAmount() {
        return depositedAmount;
    }

    public void setDepositedAmount(Long depositedAmount) {
        this.depositedAmount = depositedAmount;
    }

    public Boolean getTransactionInProgress() {
        return transactionInProgress;
    }

    public void setTransactionInProgress(Boolean transactionInProgress) {
        this.transactionInProgress = transactionInProgress;
    }

    public VendingMachine() {
        this.transactionInProgress = true;
        this.depositedAmount = 0L;
    }
}
