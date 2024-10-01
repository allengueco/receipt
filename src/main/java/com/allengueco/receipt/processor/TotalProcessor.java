package com.allengueco.receipt.processor;

import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.allengueco.receipt.model.Receipt;

@Component
@Order(3)
public class TotalProcessor implements AbstractProcessor {
    private final Logger log = LoggerFactory.getLogger(TotalProcessor.class);
    final Pattern CENTS = Pattern.compile("\\d+.((2|7)5|(5|0)0)");

    @Override
    public long process(Receipt receipt) {
        int points = CENTS.matcher(receipt.getTotal()).matches() ? 25 : 0;
        log.info("\tMatches Quarter Processor points: {}", points);
        return points;
    } 
}
