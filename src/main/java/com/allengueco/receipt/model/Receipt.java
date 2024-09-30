package com.allengueco.receipt.model;

import java.util.List;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Receipt {
    @Id
    @UuidGenerator
    String id;

    String retailer;
    String purchaseOrder;
    String purchaseTime;

    @OneToMany(cascade = CascadeType.PERSIST, orphanRemoval = false)
    List<ReceiptItem> items;
    String total;
}
