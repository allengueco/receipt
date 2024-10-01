package com.allengueco.receipt.processor;

import java.time.LocalTime;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.allengueco.receipt.model.Receipt;

@Component
@Order(7)
public class PurchaseTimeProcessor implements AbstractProcessor {
    private final LocalTime TWO_PM = LocalTime.of(14, 00);
    private final LocalTime FOUR_PM = LocalTime.of(16, 00);

    @Override
    public long process(Receipt receipt) {
        return between(receipt.getPurchaseTime(), TWO_PM, FOUR_PM) ? 10 : 0;
    }

    private boolean between(LocalTime time, LocalTime start, LocalTime end) {
        return start.getHour() <= time.getHour() && time.getHour() <= end.getHour();
    }
}
