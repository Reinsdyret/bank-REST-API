package com.boot.mnemonicbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boot.mnemonicbank.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{}
