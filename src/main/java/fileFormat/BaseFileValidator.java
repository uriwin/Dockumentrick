package fileFormat;

import status.AbstractStatus;
import status.Status;

public class BaseFileValidator extends AbstractStatus {
    private FileFormatType fileFormatType;

    public BaseFileValidator(FileFormatType fileFormatType) {
        this.fileFormatType = fileFormatType;

        setStatus(Status.DATA_CAN_MANIPULATE);
    }

    public void updateStatus(char data) {}
}
