package manipulateActions;

public class EscapeCharacterAppender extends AbstractManipulateAction {
    private String specialChar;
    private String escapeCharacter;
    private ManipulateActionsType manipulateActionType;

    public EscapeCharacterAppender(String specialChar, ManipulateActionsType manipulateActionType) {
        super(manipulateActionType);

        this.specialChar = specialChar;
        this.escapeCharacter = "/";
        this.manipulateActionType = ManipulateActionsType.EscapeCharacterAppender;
    }


    @Override
    public String manipulateData(String data) {
        return data.replace(this.specialChar, this.escapeCharacter + this.specialChar);
    }
    public boolean isByteRequiredForAction(int data) {
        return (char) data == specialChar.indexOf(0);
    }

}
