package manipulateActions;

public class EscapeCharacterAppender extends AbstractManipulateAction {
    private char specialChar;
    private String escapeCharacter;
    private ManipulateActionsType manipulateActionType;

    public EscapeCharacterAppender(char specialChar, ManipulateActionsType manipulateActionType) {
        super(manipulateActionType);

        this.specialChar = specialChar;
        this.escapeCharacter = "/";
        this.manipulateActionType = manipulateActionType;
    }


    @Override
    public String manipulateData(String data) {
        return data.replace(String.valueOf(this.specialChar), this.escapeCharacter + this.specialChar);
    }
    public boolean isByteRequiredForAction(int data) {
        return (char) data == specialChar;
    }

}
