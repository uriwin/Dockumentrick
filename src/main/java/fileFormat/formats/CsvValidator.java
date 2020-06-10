package fileFormat.formats;

import status.Status;
import fileFormat.BaseFileValidator;
import fileFormat.FileFormatValidatorType;

public class CsvValidator extends BaseFileValidator {


    public CsvValidator(FileFormatValidatorType fileFormatType) {
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
