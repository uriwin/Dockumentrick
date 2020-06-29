package arguments.extract.destinationExtractor;

public enum DestinationType {
    OUTPUT_FILE(1, "output file path", true);

    private final int argumentsRequiredNumber;
    private final String description;
    private final boolean isRequired;

    DestinationType(int argumentsRequiredNumber, String description, boolean isRequired){
        this.argumentsRequiredNumber = argumentsRequiredNumber;
        this.description = description;
        this.isRequired = isRequired;
    }

    public int getArgumentsRequiredNumber(){ return argumentsRequiredNumber; }

    public String getDescription(){ return this.description; }

    public boolean isRequired() { return isRequired; }

}
