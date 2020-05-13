package fileFormat.formats;

import fileFormat.AbstractFileFormat;
import fileFormat.FileFormatType;

public class Csv extends AbstractFileFormat {

    public Csv(FileFormatType fileFormatType) {
        this.fileFormatType = fileFormatType;
    }

    @Override
    public boolean isByteRelatedToFileFormat(int data) {
        return (char) data == ',' || (char) data == '\n';
    }

}
