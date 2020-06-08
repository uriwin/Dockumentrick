package fileFormat.formats;

import status.Status;
import fileFormat.BaseFileValidator;
import fileFormat.FileFormatType;

public class CsvValidator extends BaseFileValidator {


    public CsvValidator(FileFormatType fileFormatType) {
        super(fileFormatType);
    }

    @Override
    public void updateStatus(char data)
    {
        if (data == ',' || data == '\n'){
            status = Status.DATA_CAN_NOT_MANIPULATE;
        }
        status = Status.DATA_CAN_MANIPULATE;
    }

}
