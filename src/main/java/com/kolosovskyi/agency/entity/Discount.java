package com.kolosovskyi.agency.entity;

import java.util.Objects;

public class Discount {
    private Long id;

    private Integer step;
    private Integer maxPercent;

    public Discount() {
    }

    public Discount(Long id, Integer step, Integer maxPercent) {
        this.id = id;
        this.maxPercent = maxPercent;
        this.step = step;
    }

    public Discount(Integer step, Integer maxPercent) {
        this.maxPercent = maxPercent;
        this.step = step;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMaxPercent() {
        return maxPercent;
    }

    public void setMaxPercent(Integer maxPercent) {
        this.maxPercent = maxPercent;
    }

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Discount discount = (Discount) o;
        return Objects.equals(id, discount.id) && Objects.equals(step, discount.step)
                && Objects.equals(maxPercent, discount.maxPercent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, step, maxPercent);
    }

    @Override
    public String toString() {
        return "Discount{" +
                "id=" + id +
                ", step=" + step +
                ", maxPercent=" + maxPercent +
                '}';
    }
}
