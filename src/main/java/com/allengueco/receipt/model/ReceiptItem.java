package com.allengueco.receipt.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ReceiptItem { 
    @Id
    String id;
    String shortDescription; 
    String price;
}
