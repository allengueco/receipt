package com.allengueco.receipt.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.allengueco.receipt.model.Receipt;

@Component
@Order(1)
public class RetailerProcessor implements AbstractProcessor {
    private final Logger log = LoggerFactory.getLogger(RetailerProcessor.class);
    @Override
    public long process(Receipt receipt) {
        log.info("Starting retailer name processor for receiptId: {}", receipt.getId());

        int result = (int) receipt.getRetailer()
            .chars()
            .filter(Character::isLetterOrDigit)
            .count();
        log.info("\tRetailer Name Alphanumeric points: {}", result);

        return result;
    }

}
