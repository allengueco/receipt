package com.allengueco.receipt.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.allengueco.receipt.model.Receipt;
import com.allengueco.receipt.model.ReceiptId;
import com.allengueco.receipt.model.ReceiptPoints;

@RestController("/receipts")
public class ReceiptProcessorController {
    @PostMapping("/process")
    public ResponseEntity<ReceiptId> processReceipt(@RequestBody Receipt receipt) {
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/points")
    public ResponseEntity<ReceiptPoints> getReceiptPoints(@RequestParam String id) {
        return ResponseEntity.notFound().build();
    }
}
