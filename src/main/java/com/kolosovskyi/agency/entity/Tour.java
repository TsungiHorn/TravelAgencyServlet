package com.kolosovskyi.agency.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Tour {
    private Long id;
    private String title;
    private TourType tourType;
    private Long personNumber;
    private Integer hotelStars;
    private BigDecimal price;
    private Boolean isHot;
    private Boolean isHidden;
    private String country;
    private String city;
    private LocalDate startDate;


    public Tour() {
    }

    public Tour(Long id, String title, TourType tourType, Long personNumber, Integer hotelStars, BigDecimal price, Boolean isHot,
                Boolean isHidden, String country, String city, LocalDate startDate) {
        this.city = city;
        this.country = country;
        this.isHidden = isHidden;
        this.id = id;
        this.hotelStars = hotelStars;
        this.personNumber = personNumber;
        this.title = title;
        this.tourType = tourType;
        this.price = price;
        this.isHot = isHot;
        this.startDate = startDate;
    }

    public Tour(String title, TourType tourType, Long personNumber, Integer hotelStars, BigDecimal price, Boolean isHot,
                Boolean isHidden, String country, String city, LocalDate startDate) {
        this.city = city;
        this.country = country;
        this.isHidden = isHidden;
        this.hotelStars = hotelStars;
        this.personNumber = personNumber;
        this.title = title;
        this.tourType = tourType;
        this.price = price;
        this.isHot = isHot;
        this.startDate = startDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public TourType getTourType() {
        return tourType;
    }

    public void setTourType(TourType tourType) {
        this.tourType = tourType;
    }

    public Long getPersonNumber() {
        return personNumber;
    }

    public void setPersonNumber(Long personNumber) {
        this.personNumber = personNumber;
    }

    public Integer getHotelStars() {
        return hotelStars;
    }

    public void setHotelStars(Integer hotelStars) {
        this.hotelStars = hotelStars;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Boolean getHot() {
        return isHot;
    }

    public void setHot(Boolean hot) {
        isHot = hot;
    }

    public Boolean getHidden() {
        return isHidden;
    }

    public void setHidden(Boolean hidden) {
        isHidden = hidden;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tour tour = (Tour) o;
        return Objects.equals(id, tour.id) && Objects.equals(title, tour.title) && tourType == tour.tourType && Objects.equals(personNumber, tour.personNumber) && Objects.equals(hotelStars, tour.hotelStars) && Objects.equals(price, tour.price) && Objects.equals(isHot, tour.isHot) && Objects.equals(isHidden, tour.isHidden) && Objects.equals(country, tour.country) && Objects.equals(city, tour.city) && Objects.equals(startDate, tour.startDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, tourType, personNumber, hotelStars, price, isHot, isHidden, country, city, startDate);
    }

    @Override
    public String toString() {
        return "Tour{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", tourType=" + tourType +
                ", personNumber=" + personNumber +
                ", hotelStars=" + hotelStars +
                ", price=" + price +
                ", isHot=" + isHot +
                ", isHidden=" + isHidden +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", startDate=" + startDate +
                '}';
    }
}