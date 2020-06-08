package fileFormat;

import status.AbstractStatus;
import status.Status;

public class BaseFileValidator extends AbstractStatus {
    public FileFormatType fileFormatType;

    public BaseFileValidator(FileFormatType fileFormatType) {
        this.fileFormatType = fileFormatType;

        this.status = Status.DATA_CAN_MANIPULATE;
    }

    public void updateStatus(char data) {}
}
