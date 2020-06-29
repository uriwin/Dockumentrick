package arguments.extract.destinationExtractor;

public class DestinationDTO {
    private DestinationType destinationType;

    private String destinationValue;

    public DestinationDTO() { }

    public DestinationType getDestinationType() {
        return destinationType;
    }

    public String getDestinationValue() {
        return destinationValue;
    }

    public void setDestinationType(DestinationType destinationType) {
        this.destinationType = destinationType;
    }

    public void setDestinationValue(String destinationValue) {
        this.destinationValue = destinationValue;
    }
}
