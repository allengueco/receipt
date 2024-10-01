package com.allengueco.receipt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.allengueco.receipt.model.Receipt;

@Repository
public interface ReceiptRepository extends JpaRepository<Receipt, String> {
    Optional<Receipt> findById(String id);
}

