package fileFormat.formats;

import status.Status;
import fileFormat.BaseFileValidator;
import fileFormat.FileFormatValidatorType;

public class CsvValidator extends BaseFileValidator {

    private boolean isInQuotes;

    public CsvValidator(FileFormatValidatorType fileFormatType) {
        super(fileFormatType);

        this.isInQuotes = false;
    }

    @Override
    public void updateStatus(char data)
    {
        updateIsInQuotes(data);
        if ((data == ',' || data == '\n') && (!isInQuotes)){
            status = Status.DATA_CAN_NOT_MANIPULATE;
        }
        status = Status.DATA_CAN_MANIPULATE;
    }


    public void updateIsInQuotes(char data){
        if (data == '\"' || data == '\''){
            isInQuotes = !isInQuotes;
        }
    }

}
