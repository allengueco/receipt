package com.allengueco.receipt.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.allengueco.receipt.model.Receipt;

@Component
@Order(4)
public class ItemsNumberProcessor implements AbstractProcessor {
    private final Logger log = LoggerFactory.getLogger(ItemsNumberProcessor.class);
    @Override
    public long process(Receipt receipt) {
        int numItems = receipt.getItems().size();
        int points = (int) Math.floor(numItems / 2) * 5;

        log.info("\tNum items: {}, bonus: {}", numItems, points);

        return points;
    }
}
