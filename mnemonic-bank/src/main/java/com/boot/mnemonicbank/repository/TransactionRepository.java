package com.boot.mnemonicbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boot.mnemonicbank.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{}
