package fileFormat.formatValidator;

import fileFilter.SpecialCharacters;
import status.Status;
import fileFormat.BaseFileValidator;
import fileFormat.FileFormatType;

public class XmlValidator extends BaseFileValidator {
    private boolean isInMiddleOfTag;

    public XmlValidator(FileFormatType fileFormatType) {
        super(fileFormatType);

        this.isInMiddleOfTag = false;
    }

    @Override
    public void updateStatus(char data) {
        if (isInMiddleOfTag) {
            if (data == SpecialCharacters.CLOSE_TAG.toChar()){
                isInMiddleOfTag = false;
            }
            setStatus(Status.DATA_CAN_NOT_MANIPULATE);
        }
        else {
            if (data == SpecialCharacters.START_TAG.toChar()) {
                isInMiddleOfTag = true;
                setStatus(Status.DATA_CAN_NOT_MANIPULATE);
            }
            else{
                setStatus(Status.DATA_CAN_MANIPULATE);
            }
        }
    }

}
