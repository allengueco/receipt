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

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = false)
    List<ReceiptItem> items;

    String total;

    public Receipt() {
    }

    public Receipt(String id, String retailer, String purchaseOrder, String purchaseTime, List<ReceiptItem> items,
            String total) {
        this.id = id;
        this.retailer = retailer;
        this.purchaseOrder = purchaseOrder;
        this.purchaseTime = purchaseTime;
        this.items = items;
        this.total = total;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((retailer == null) ? 0 : retailer.hashCode());
        result = prime * result + ((purchaseOrder == null) ? 0 : purchaseOrder.hashCode());
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
        if (purchaseOrder == null) {
            if (other.purchaseOrder != null)
                return false;
        } else if (!purchaseOrder.equals(other.purchaseOrder))
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

    @Override
    public String toString() {
        return "Receipt [id=" + id + ", retailer=" + retailer + ", purchaseOrder=" + purchaseOrder + ", purchaseTime="
                + purchaseTime + ", items=" + items + ", total=" + total + "]";
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

    public String getPurchaseOrder() {
        return purchaseOrder;
    }

    public void setPurchaseOrder(String purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }

    public String getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(String purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    public List<ReceiptItem> getItems() {
        return items;
    }

    public void setItems(List<ReceiptItem> items) {
        this.items = items;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
