package javabean_components;

import java.math.BigDecimal;
import java.util.Date;

public class UserTours {
    private Long userID;
    private Long tourID;
    private Date orderTime;
    private Integer statusID;
    private BigDecimal finalPrice;
    private Integer discountPercent;

    public UserTours() {
    }

    public UserTours(Long userID, Long tourID, Date orderTime, Integer statusID, BigDecimal finalPrice, Integer discountPercent) {
        this.discountPercent = discountPercent;
        this.userID = userID;
        this.tourID = tourID;
        this.statusID = statusID;
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

    public Integer getStatusID() {
        return statusID;
    }

    public void setStatusID(Integer statusID) {
        this.statusID = statusID;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Long getTourID() {
        return tourID;
    }

    public void setTourID(Long tourID) {
        this.tourID = tourID;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }
}
