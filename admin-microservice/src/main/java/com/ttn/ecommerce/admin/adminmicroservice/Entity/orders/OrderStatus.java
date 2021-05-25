package com.ttn.ecommerce.admin.adminmicroservice.Entity.orders;

import javax.persistence.*;

@Entity
public class OrderStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int order_status_id;
    private String transitionNoteComment;
    private FromStatus fromStatus;
    private ToStatus toStatus;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_Product_Id")
    OrderProduct orderProduct;

    public OrderProduct getOrderProduct() {
        return orderProduct;
    }

    public void setOrderProduct(OrderProduct orderProduct) {
        this.orderProduct = orderProduct;
    }

    public int getOrder_status_id() {
        return order_status_id;
    }

    public void setOrder_status_id(int order_status_id) {
        this.order_status_id = order_status_id;
    }

    public String getTransitionNoteComment() {
        return transitionNoteComment;
    }

    public void setTransitionNoteComment(String transitionNoteComment) {
        this.transitionNoteComment = transitionNoteComment;
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

    @Override
    public String toString() {
        return "OrderStatus{" +
                "order_status_id=" + order_status_id +
                ", transitionNoteComment='" + transitionNoteComment + '\'' +
                ", fromStatus=" + fromStatus +
                ", toStatus=" + toStatus +
                ", orderProduct=" + orderProduct +
                '}';
    }
}
