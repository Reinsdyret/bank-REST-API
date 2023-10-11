package com.boot.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boot.bank.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{}
