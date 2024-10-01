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

    public Optional<Receipt> getReceipt(String id) {
        return receiptRepository.findById(id);
    }

    public Receipt processReceipt(Receipt receipt) {
        return receiptRepository.save(receipt);
    }

    public Optional<ReceiptPoints> calculatePoints(String id) {
        var receipt = receiptRepository.findById(id);
        if (receipt.isEmpty())
            return Optional.empty();

        int points = receiptProcessor.process(receipt.get());

        if (points < 1)
            return Optional.empty();

        return Optional.of(new ReceiptPoints(points));
    }
}
