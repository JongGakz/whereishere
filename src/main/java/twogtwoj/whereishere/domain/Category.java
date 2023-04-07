package twogtwoj.whereishere.domain;

public enum Category {

    HEALTH("헬스장"),
    RESTAURANT("음식점"),
    HOSPITAL("병원"),
    THEATER("영화관"),
    DEPARTMENT("백화점"),
    CAFFE("카페");

    private final String description;

    Category(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
