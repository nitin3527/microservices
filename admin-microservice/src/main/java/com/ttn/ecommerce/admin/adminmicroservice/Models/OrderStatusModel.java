package com.ttn.ecommerce.admin.adminmicroservice.Models;


import com.ttn.ecommerce.admin.adminmicroservice.Entity.orders.FromStatus;
import com.ttn.ecommerce.admin.adminmicroservice.Entity.orders.ToStatus;

public class OrderStatusModel {
    private long orderProductId;
    private FromStatus fromStatus;
    private ToStatus toStatus;

    public long getOrderProductId() {
        return orderProductId;
    }

    public void setOrderProductId(long orderProductId) {
        this.orderProductId = orderProductId;
    }

    public FromStatus getFromStatus() {
        return fromStatus;
    }

    public void setFromStatus(FromStatus fromStatus) {
        this.fromStatus = fromStatus;
    }

    public ToStatus getToStatus() {
        return toStatus;
    }

    public void setToStatus(ToStatus toStatus) {
        this.toStatus = toStatus;
    }
}
