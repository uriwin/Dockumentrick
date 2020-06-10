package fileFormat;

import status.AbstractStatus;
import status.Status;

public class BaseFileValidator extends AbstractStatus {
    public FileFormatValidatorType fileFormatType;

    public BaseFileValidator(FileFormatValidatorType fileFormatType) {
        this.fileFormatType = fileFormatType;

        this.status = Status.DATA_CAN_MANIPULATE;
    }

    public void updateStatus(char data) {}
}
