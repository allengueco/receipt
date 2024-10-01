package com.allengueco.receipt.model;

import java.time.LocalDate;
import java.time.LocalTime;
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

    LocalDate purchaseDate;

    LocalTime purchaseTime;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = false)
    List<Item> items;

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
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((retailer == null) ? 0 : retailer.hashCode());
        result = prime * result + ((purchaseDate == null) ? 0 : purchaseDate.hashCode());
        result = prime * result + ((purchaseTime == null) ? 0 : purchaseTime.hashCode());
        result = prime * result + ((items == null) ? 0 : items.hashCode());
        result = prime * result + ((total == null) ? 0 : total.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Receipt other = (Receipt) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (retailer == null) {
            if (other.retailer != null)
                return false;
        } else if (!retailer.equals(other.retailer))
            return false;
        if (purchaseDate == null) {
            if (other.purchaseDate != null)
                return false;
        } else if (!purchaseDate.equals(other.purchaseDate))
            return false;
        if (purchaseTime == null) {
            if (other.purchaseTime != null)
                return false;
        } else if (!purchaseTime.equals(other.purchaseTime))
            return false;
        if (items == null) {
            if (other.items != null)
                return false;
        } else if (!items.equals(other.items))
            return false;
        if (total == null) {
            if (other.total != null)
                return false;
        } else if (!total.equals(other.total))
            return false;
        return true;
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
