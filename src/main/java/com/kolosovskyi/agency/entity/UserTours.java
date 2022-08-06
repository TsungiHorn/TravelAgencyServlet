package com.kolosovskyi.agency.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class UserTours {
    private User user;
    private Tour tour;
    private LocalDate orderTime;
    private TourStatus status;
    private BigDecimal finalPrice;
    private Integer discountPercent;

    public UserTours() {
    }

    public UserTours(User user, Tour tour,
                     LocalDate orderTime,
                     TourStatus status,
                     BigDecimal finalPrice,
                     Integer discountPercent) {
        this.discountPercent = discountPercent;
        this.user = user;
        this.tour = tour;
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

    public void setStatus(TourStatus status) {
        this.status = status;
    }

    public LocalDate getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalDate orderTime) {
        this.orderTime = orderTime;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        return user.equals(((UserTours)o).user )&&
                tour.equals(((UserTours)o).tour) &&
                orderTime.equals(((UserTours)o).orderTime) &&
                status.equals(((UserTours)o).status )&&
                finalPrice.equals(((UserTours)o).finalPrice) &&
                discountPercent.equals(((UserTours)o).discountPercent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, tour, orderTime, status, finalPrice, discountPercent);
    }

    @Override
    public String toString() {
        return "UserTours{" +
                "user=" + user +
                ", tour=" + tour +
                ", orderTime=" + orderTime +
                ", status=" + status +
                ", finalPrice=" + finalPrice +
                ", discountPercent=" + discountPercent +
                '}';
    }
}
