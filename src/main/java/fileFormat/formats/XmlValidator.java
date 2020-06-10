package fileFormat.formats;

import status.Status;
import fileFormat.BaseFileValidator;
import fileFormat.FileFormatValidatorType;

public class XmlValidator extends BaseFileValidator {
    public boolean isInMiddleOfTag;

    final static char START_OF_TAG = '<';

    final static char END_OF_TAG = '>';

    public XmlValidator(FileFormatValidatorType fileFormatType) {
        super(fileFormatType);

        this.isInMiddleOfTag = false;

        this.fileFormatType = fileFormatType;
    }

    @Override
    public void updateStatus(char data) {
        if (isInMiddleOfTag) {
            if (data == END_OF_TAG){
                isInMiddleOfTag = false;
            }
            status = Status.DATA_CAN_NOT_MANIPULATE;
        }
        else {
            if (data == START_OF_TAG) {
                isInMiddleOfTag = true;
                status = Status.DATA_CAN_NOT_MANIPULATE;
            }
            else{
                status = Status.DATA_CAN_MANIPULATE;
            }
        }
    }

}
