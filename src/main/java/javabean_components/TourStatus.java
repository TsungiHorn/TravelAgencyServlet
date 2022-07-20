package javabean_components;

public class TourStatus {
    private Integer id;
    private String status;

    public TourStatus() {
    }

    public TourStatus(Integer id, String status) {
        this.status = status;
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
