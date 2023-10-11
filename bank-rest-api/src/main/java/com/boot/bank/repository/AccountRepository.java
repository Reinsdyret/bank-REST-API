package com.boot.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boot.bank.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{}
