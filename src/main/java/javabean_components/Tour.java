package javabean_components;

import java.math.BigDecimal;

public class Tour {
    private String title;
    private Integer typeID;
    private Long personNumber;
    private Integer hotelStars;
    private BigDecimal price;
    private Boolean isHot;
    private Boolean isHidden;
    private String country;
    private String city;
    private Long id;

    public Tour() {
    }

    public Tour(String title, Integer typeID, Long personNumber, Integer hotelStars, BigDecimal price, Boolean isHot,
                Boolean isHidden, String country, String city, Long id) {
        this.city = city;
        this.country = country;
        this.isHidden = isHidden;
        this.id = id;
        this.hotelStars = hotelStars;
        this.personNumber = personNumber;
        this.title = title;
        this.typeID = typeID;
        this.price = price;
        this.isHot = isHot;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Boolean getHidden() {
        return isHidden;
    }

    public void setHidden(Boolean hidden) {
        isHidden = hidden;
    }

    public Boolean getHot() {
        return isHot;
    }

    public void setHot(Boolean hot) {
        isHot = hot;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getHotelStars() {
        return hotelStars;
    }

    public void setHotelStars(Integer hotelStars) {
        this.hotelStars = hotelStars;
    }

    public Long getPersonNumber() {
        return personNumber;
    }

    public void setPersonNumber(Long personNumber) {
        this.personNumber = personNumber;
    }

    public Integer getTypeID() {
        return typeID;
    }

    public void setTypeID(Integer typeID) {
        this.typeID = typeID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
