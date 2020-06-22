package manipulateActions.actions;

import manipulateActions.AbstractManipulateAction;
import status.Status;

public class EscapeCharacterAppender extends AbstractManipulateAction {
    private char specialChar;

    private final String ESCAPE_CHARACTER = "\\";

    public EscapeCharacterAppender(char specialChar) {
        this.specialChar = specialChar;
    }

    public String manipulateDataAction(String data) {
        return data.replace(String.valueOf(this.specialChar), ESCAPE_CHARACTER + this.specialChar);
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
