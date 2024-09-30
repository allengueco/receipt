package com.allengueco.receipt.processor;

import com.allengueco.receipt.model.Receipt;

public interface AbstractProcessor {
    int process(Receipt receipt);
}
