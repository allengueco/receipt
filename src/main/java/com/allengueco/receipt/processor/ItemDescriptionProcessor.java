package com.allengueco.receipt.processor;

import java.util.function.Function;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.allengueco.receipt.model.Item;
import com.allengueco.receipt.model.Receipt;

@Component
@Order(5)
public class ItemDescriptionProcessor implements AbstractProcessor {
    private final Logger log = LoggerFactory.getLogger(ItemDescriptionProcessor.class);
    private final int MULTIPLE = 3;

    @Override
    public long process(Receipt receipt) {
        return receipt.getItems().stream()
                .collect(Collectors.summingLong(this::bonusForItem));
    }

    private long bonusForItem(Item item) {
        if (item.getShortDescription().trim().length() % MULTIPLE == 0) {
            Double price = Double.valueOf(item.getPrice()) * 0.2;
            long result = (int) Math.ceil(price);
            log.info("\tItem[name={}]: {}", item.getShortDescription(), result);
            return result;
        } else
            return 0;
    }

}
