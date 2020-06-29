package fileFilter;

public enum FilterType {
    FILTER_ON_SPECIFIC_ELEMENT(1, "Filter on specific element in xml file", false),
    FILTER_ON_SPECIFIC_COLUMN(1, "Filter on specific column in csv file", false),
    FILTER_ON_SPECIFIC_FIRST_LINES(1, "Filter first n lines of file", false),
    FILTER_BASED_ON_FILE_TYPE(1, "Does not manipulate data related to file format", false);

    private final int argumentsRequiredNumber;
    private final String description;
    private final boolean isRequired;

    FilterType(int argumentsRequired, String description, boolean isRequired) {
        this.argumentsRequiredNumber = argumentsRequired;
        this.description = description;
        this.isRequired = isRequired;
    }

    public int getArgumentsRequiredNumber() {
        return argumentsRequiredNumber;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean isRequired() {
        return isRequired;
    }
}
