package manipulateActions.actions;

import manipulateActions.AbstractManipulateAction;
import status.Status;

public class EscapeCharacterAppender extends AbstractManipulateAction {
    private String specialChar;

    private final String ESCAPE_CHARACTER = "\\";

    public EscapeCharacterAppender(String specialChar) {
        this.specialChar = specialChar;
    }

    public String manipulateDataAction(String data) {
        return data.replace(this.specialChar, ESCAPE_CHARACTER + this.specialChar);
    }

    @Override
    public void updateStatus(char data) {
        if (specialChar.equals(String.valueOf(data))) {
            setStatus(Status.DATA_CAN_MANIPULATE);
        } else {
            setStatus(Status.DATA_CAN_NOT_MANIPULATE);
        }
    }
}
