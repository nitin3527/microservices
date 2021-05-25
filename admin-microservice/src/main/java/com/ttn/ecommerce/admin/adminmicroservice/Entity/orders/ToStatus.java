package com.ttn.ecommerce.admin.adminmicroservice.Entity.orders;

public enum ToStatus {
    CANCELLED,
    ORDER_CONFIRMED,
    ORDER_REJECTED,
    REFUND_INITIATED,
    CLOSED,
    ORDER_SHIPPED,
    DELIVERED,
    RETURN_REQUESTED,
    RETURN_REJECTED,
    RETURN_APPROVED,
    PICK_UP_INITIATED,
    PICK_UP_COMPLETED,
    REFUND_COMPLETED
}
