package com.colosovskyi.agency.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class UserTours {
    private Long userId;
    private Long tourId;
    private Date orderTime;
    private TourStatus status;
    private BigDecimal finalPrice;
    private Integer discountPercent;

    public UserTours() {
    }

    public UserTours(Long userId, Long tourId, Date orderTime, TourStatus status, BigDecimal finalPrice, Integer discountPercent) {
        this.discountPercent = discountPercent;
        this.userId = userId;
        this.tourId = tourId;
        this.status = status;
        this.finalPrice = finalPrice;
        this.orderTime = orderTime;
    }


    public Integer getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(Integer discountPercent) {
        this.discountPercent = discountPercent;
    }

    public BigDecimal getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(BigDecimal finalPrice) {
        this.finalPrice = finalPrice;
    }

    public TourStatus getStatus() {
        return status;
    }

    public void setStatusID(TourStatus status) {
        this.status = status;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Long getTourID() {
        return tourId;
    }

    public void setTourID(Long tourID) {
        this.tourId = tourID;
    }

    public Long getUserID() {
        return userId;
    }

    public void setUserID(Long userID) {
        this.userId = userID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserTours userTours = (UserTours) o;
        return Objects.equals(userId, userTours.userId) && Objects.equals(tourId, userTours.tourId) && Objects.equals(orderTime, userTours.orderTime) && status == userTours.status && Objects.equals(finalPrice, userTours.finalPrice) && Objects.equals(discountPercent, userTours.discountPercent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, tourId, orderTime, status, finalPrice, discountPercent);
    }

    @Override
    public String toString() {
        return "UserTours{" +
                "userID=" + userId +
                ", tourID=" + tourId +
                ", orderTime=" + orderTime +
                ", status=" + status +
                ", finalPrice=" + finalPrice +
                ", discountPercent=" + discountPercent +
                '}';
    }
}
