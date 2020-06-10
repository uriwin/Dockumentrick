package commandLine.extracte.sourceExtractor;

public class SourceDTO {
    private SourceType sourceType;

    private String sourceValue;

    public SourceDTO(){
        this.sourceValue = "";
    }

    public SourceType getSourceType() {
        return sourceType;
    }

    public String getSourceValue() {
        return sourceValue;
    }

    public void setSourceType(SourceType sourceType) {
        this.sourceType = sourceType;
    }

    public void setSourceValue(String sourceValue) {
        this.sourceValue = sourceValue;
    }
}
