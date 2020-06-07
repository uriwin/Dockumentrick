package fileFormat;

import fileFilter.IFilter;

public class BaseFileValidator {
    public FileFormatType fileFormatType;

    public BaseFileValidator(FileFormatType fileFormatType){
        this.fileFormatType = fileFormatType;
    }

    public boolean isByteRelatedToFileFormat(int data) {
        return false;
    }
}
