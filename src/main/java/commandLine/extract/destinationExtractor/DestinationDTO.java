package commandLine.extract.destinationExtractor;

import commandLine.extract.sourceExtractor.SourceType;

public class DestinationDTO {
    private DestinationType destinationType;

    private String destinationValue;

    public DestinationDTO(){
        this.destinationValue = "";
    }

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
