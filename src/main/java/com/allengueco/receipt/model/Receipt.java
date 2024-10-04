package com.allengueco.receipt.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class Receipt {
    @Id
    @UuidGenerator
    String id;

    @Pattern(regexp = "^[\\w\\s\\-&]+$")
    String retailer;

    @PastOrPresent
    LocalDate purchaseDate;

    LocalTime purchaseTime;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @Size(min = 1)
    List<Item> items = new ArrayList<>();

    @Pattern(regexp = "^\\d+\\.\\d{2}$")
    String total;

    public Receipt() {
    }

    @Override
    public String toString() {
        return "Receipt [id=" + id + ", retailer=" + retailer + ", purchaseOrder=" + purchaseDate + ", purchaseTime="
                + purchaseTime + ", items=" + items + ", total=" + total + "]";
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
       if (this == obj) return true;

       if (!(obj instanceof Receipt)) return false;

       Receipt other = (Receipt) obj;

       return id != null && id.equals(other.getId()); 
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRetailer() {
        return retailer;
    }

    public void setRetailer(String retailer) {
        this.retailer = retailer;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseOrder) {
        this.purchaseDate = purchaseOrder;
    }

    public LocalTime getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(LocalTime purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
