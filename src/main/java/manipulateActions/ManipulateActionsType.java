package manipulateActions;

public enum ManipulateActionsType{
    BASE_CONVERTER(1, "change number base, options available: h/o/b", false),
    STRING_ENCLOSER(2, "enclose string in file by other string", false),
    ESCAPE_CHARACTER_APPENDER(1, "append escape character to specific char in file", false);

    private final int argumentsRequiredNumber;
    private final String description;
    private final boolean isRequired;

    ManipulateActionsType(int argumentsRequiredNumber, String description, boolean isRequired){
        this.argumentsRequiredNumber = argumentsRequiredNumber;
        this.description = description;
        this.isRequired = isRequired;
    }

    public int getArgumentsRequiredNumber(){ return argumentsRequiredNumber; }

    public String getDescription(){ return this.description; }

    public boolean isRequired() { return isRequired; }
}
