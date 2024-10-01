package com.allengueco.receipt.controllers;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.allengueco.receipt.model.Receipt;
import com.allengueco.receipt.model.ReceiptId;
import com.allengueco.receipt.model.ReceiptPoints;
import com.allengueco.receipt.service.ReceiptService;

@RestController("/receipts")
public class ReceiptProcessorController {
    ReceiptService receiptService;

    public ReceiptProcessorController(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    @PostMapping("/process")
    public ResponseEntity<ReceiptId> processReceipt(@RequestBody Receipt receipt) {
        Receipt r = receiptService.processReceipt(receipt);

        return ResponseEntity.ok(new ReceiptId(r.getId()));
    }

    @GetMapping("/{id}/points")
    public ResponseEntity<ReceiptPoints> getReceiptPoints(@RequestParam String id) {
        return ResponseEntity.of(receiptService.calculatePoints(id));
    }
}
