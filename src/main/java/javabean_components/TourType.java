package javabean_components;

public class TourType {
    private Long id;
    private String type;

    public TourType() {
    }

    public TourType(Long id, String type) {
        this.type = type;
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
