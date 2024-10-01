package com.allengueco.receipt.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.allengueco.receipt.model.Receipt;
import com.allengueco.receipt.model.ReceiptPoints;
import com.allengueco.receipt.processor.AbstractProcessor;
import com.allengueco.receipt.repository.ReceiptRepository;

@Service
public class ReceiptService {
    ReceiptRepository receiptRepository;
    AbstractProcessor receiptProcessor;

    public ReceiptService(ReceiptRepository receiptRepository, AbstractProcessor receiptProcessor) {
        this.receiptRepository = receiptRepository;
        this.receiptProcessor = receiptProcessor;
    }

    Optional<Receipt> getReceipt(String id) {
        return receiptRepository.findById(id);
    }

    Optional<Receipt> processReceipt(Receipt receipt) {
        if (receiptRepository.existsById(receipt.getId())) {
            return receiptRepository.findById(receipt.getId());
        } else {
            return Optional.of(receiptRepository.save(receipt));
        }
    }

    Optional<ReceiptPoints> calculatePoints(String id) {
        var receipt = receiptRepository.findById(id);
        if (receipt.isEmpty()) return Optional.empty();

        int points = receiptProcessor.process(receipt.get());

        if (points < 1) return Optional.empty();

        return Optional.of(new ReceiptPoints(points));
    }
}
