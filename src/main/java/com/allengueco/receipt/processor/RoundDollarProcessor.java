package com.allengueco.receipt.processor;

import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.allengueco.receipt.model.Receipt;

@Component
@Order(2)
public class RoundDollarProcessor implements AbstractProcessor {
    private final Logger log = LoggerFactory.getLogger(RoundDollarProcessor.class);
    final Pattern ROUND_DOLLAR = Pattern.compile("\\d+.00");

    @Override
    public long process(Receipt receipt) {
        int points = ROUND_DOLLAR.matcher(receipt.getTotal()).matches() ? 50 : 0;
        log.info("\tRound Dollar Processor points: {}", points);
        return points;
    }

}
