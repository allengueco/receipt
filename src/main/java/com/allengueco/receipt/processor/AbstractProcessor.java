package com.allengueco.receipt.processor;

import com.allengueco.receipt.model.Receipt;

public interface AbstractProcessor {
    long process(Receipt receipt);
}
