package com.allengueco.receipt.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.allengueco.receipt.model.Receipt;
import com.allengueco.receipt.model.ReceiptId;
import com.allengueco.receipt.model.ReceiptPoints;
import com.allengueco.receipt.service.ReceiptService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/receipts")
public class ReceiptProcessorController {
    ReceiptService receiptService;

    public ReceiptProcessorController(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    @PostMapping(path = "/process", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReceiptId> processReceipt(@RequestBody @Valid Receipt receipt) {
        Receipt r = receiptService.processReceipt(receipt);

        return ResponseEntity.ok(new ReceiptId(r.getId()));
    }

    @GetMapping(path = "/{id}/points", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReceiptPoints> getReceiptPoints(@PathVariable("id") String id) {
        return ResponseEntity.of(receiptService.calculatePoints(id));
    }
}
