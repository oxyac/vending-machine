package com.oxyac.vendingmachine.data.repository;

import com.oxyac.vendingmachine.data.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query("select i from Item i where i.col = :col and i.row = :row and i.stock.id = :stock_id")
    Optional<Item> findByColAndRowAndStockId(@Param("col") Integer col, @Param("row") String row, @Param("stock_id") UUID stockId);

}