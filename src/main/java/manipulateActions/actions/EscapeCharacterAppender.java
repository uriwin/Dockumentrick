package manipulateActions.actions;

import manipulateActions.AbstractManipulateAction;
import status.Status;

public class EscapeCharacterAppender extends AbstractManipulateAction {
    private char specialChar;

    private String escapeCharacter;

    public EscapeCharacterAppender(char specialChar) {
        this.specialChar = specialChar;

        this.escapeCharacter = "\\";
    }

    public String manipulateDataAction(String data) {
        return data.replace(String.valueOf(this.specialChar), this.escapeCharacter + this.specialChar);
    }

    @Override
    public void updateStatus(char data) {
        if (data == specialChar) {
            status = Status.DATA_CAN_MANIPULATE;
        } else {
            status = Status.DATA_CAN_NOT_MANIPULATE;
        }
    }
}
