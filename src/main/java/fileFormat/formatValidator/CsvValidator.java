package fileFormat.formats;

import fileFilter.SpecialCharacters;
import status.Status;
import fileFormat.BaseFileValidator;
import fileFormat.FileFormatType;

public class CsvValidator extends BaseFileValidator {

    private boolean isInQuotes;

    private boolean isInApostrophe;

    public CsvValidator(FileFormatType fileFormatType) {
        super(fileFormatType);

        this.isInQuotes = false;

        this.isInApostrophe = false;
    }

    @Override
    public void updateStatus(char data)
    {
        updateIsInQuotes(data);
        updateIsInApostrophe(data);

        if ((isDataRelatedToFileFormat(data)) && (!isInQuotes && !isInApostrophe)){
            setStatus(Status.DATA_CAN_NOT_MANIPULATE);
        }
        setStatus(Status.DATA_CAN_MANIPULATE);
    }

    public void updateIsInQuotes(char data){
        if (data == SpecialCharacters.QUOTES.toChar()){
            isInQuotes = !isInQuotes;
        }
    }

    public void updateIsInApostrophe(char data){
        if (data == SpecialCharacters.APOSTROPHE.toChar()){
            isInApostrophe = !isInApostrophe;
        }
    }

    private boolean isDataRelatedToFileFormat(char data){
        return (data == SpecialCharacters.COMMA.toChar() || data == SpecialCharacters.NEW_LINE.toChar());
    }

}
