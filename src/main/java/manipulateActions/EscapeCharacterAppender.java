package manipulateActions;

public class EscapeCharacterAppender implements IManipulateAction{
    private char specialChar;
    private String escapeCharacter;

    public EscapeCharacterAppender(char specialChar) {
        this.specialChar = specialChar;
        this.escapeCharacter = "\\";
    }

    public String manipulateDataAction(String data) {
        return data.replace(String.valueOf(this.specialChar), this.escapeCharacter + this.specialChar);
    }
    public boolean isByteRequiredForAction(int data) {
        return (char) data == specialChar;
    }

}
