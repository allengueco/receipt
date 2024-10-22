package com.allengueco.receipt.processor;

import com.allengueco.receipt.model.Receipt;
import org.springframework.stereotype.Component;

@Component
public class UniqueItemsProcessor implements AbstractProcessor {
    @Override
    public long process(Receipt receipt) {
        long count = receipt.getItems().size();
        return receipt.getItems()
                .stream()
                .distinct()
                .count() == count ?
                20 : 0;
    }
}
