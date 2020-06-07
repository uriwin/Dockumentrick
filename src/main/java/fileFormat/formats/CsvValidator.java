package fileFormat.formats;

import fileFormat.BaseFileValidator;
import fileFormat.FileFormatType;

public class CsvValidator extends BaseFileValidator {


    public CsvValidator(FileFormatType fileFormatType) {
        super(fileFormatType);
    }

    @Override
    public boolean isByteRelatedToFileFormat(int data) {
        return (char) data == ',' || (char) data == '\n';
    }

}
