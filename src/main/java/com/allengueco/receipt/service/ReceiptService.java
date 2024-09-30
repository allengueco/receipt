package com.allengueco.receipt.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.allengueco.receipt.model.Receipt;
import com.allengueco.receipt.model.ReceiptPoints;
import com.allengueco.receipt.repository.ReceiptRepository;

@Service
public class ReceiptService {
    ReceiptRepository receiptRepository;

    public ReceiptService(ReceiptRepository receiptRepository) {
        this.receiptRepository = receiptRepository;
    }

    Optional<Receipt> getReceipt(String id) {
        return receiptRepository.findById(id);
    }
}
