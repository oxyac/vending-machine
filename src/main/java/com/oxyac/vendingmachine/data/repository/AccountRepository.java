package com.oxyac.vendingmachine.data.repository;

import com.oxyac.vendingmachine.data.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {
}