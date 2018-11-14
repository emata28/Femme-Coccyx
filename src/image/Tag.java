package image;

public class Tag {
    private Double Confidence;
    private String Name;

    public Double getConfidence() {
        return Confidence;
    }

    public void setConfidence(Double confidence) {
        Confidence = confidence;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Tag(Double confidence, String name) {
        Confidence = confidence;
        Name = name;
    }
}
