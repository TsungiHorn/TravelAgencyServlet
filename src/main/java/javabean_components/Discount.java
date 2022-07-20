package javabean_components;

public class Discount {
    private Integer step;
    private Integer maxPercent;
    private Long id;

    public Discount() {
    }

    public Discount(Integer step, Integer maxPercent, Long id) {
        this.id = id;
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
}
