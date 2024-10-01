package com.allengueco.receipt.processor;

import java.time.temporal.ChronoField;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.allengueco.receipt.model.Receipt;

@Component
@Order(6)
public class PurchaseDateProcessor implements AbstractProcessor {
    private final Logger log = LoggerFactory.getLogger(PurchaseDateProcessor.class);

    @Override
    public long process(Receipt receipt) {
        boolean dayIsOdd = receipt.getPurchaseDate().get(ChronoField.DAY_OF_MONTH) % 2 == 0; 
        int points = dayIsOdd ? 0 : 6;
        log.info("\tDay is odd: {}", points);
        return points;
    }
}
