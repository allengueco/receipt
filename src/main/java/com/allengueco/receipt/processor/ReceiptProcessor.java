package com.allengueco.receipt.processor;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.allengueco.receipt.model.Receipt;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE) // so we use this one first, which kicks off the rest of the pipeline
public class ReceiptProcessor implements AbstractProcessor {
    private final Logger log = LoggerFactory.getLogger(ReceiptProcessor.class);
    List<AbstractProcessor> processors;

    public ReceiptProcessor(List<AbstractProcessor> processors) {
        this.processors = processors;
    }

    @Override
    public long process(Receipt receipt) {
        log.info("Starting process for receiptId: {}", receipt.getId());

        return processors.stream()
                .collect(Collectors.summingLong(p -> p.process(receipt)));
    }

}
