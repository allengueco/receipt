package com.allengueco.receipt.processor;

import java.time.LocalTime;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.allengueco.receipt.model.Receipt;

@Component
@Order(7)
public class PurchaseTimeProcessor implements AbstractProcessor {
    private final LocalTime TWO_PM = LocalTime.of(14, 0);
    private final LocalTime FOUR_PM = LocalTime.of(16, 0);

    @Override
    public long process(Receipt receipt) {
        return between(receipt.getPurchaseTime(), TWO_PM, FOUR_PM) ? 10 : 0;
    }

    private boolean between(LocalTime time, LocalTime start, LocalTime end) {
        return betweenInclusive(time.getHour(), start.getHour(), end.getHour())
                || betweenExclusive(time.getMinute(), start.getMinute(), end.getMinute());
    }

    private boolean betweenExclusive(int time, int start, int end) {
        return start < time && time < end;
    }

    private boolean betweenInclusive(int time, int start, int end) {
        return start <= time && time <= end;
    }
}
