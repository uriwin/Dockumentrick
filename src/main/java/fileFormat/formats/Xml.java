package fileFormat.formats;

import fileFormat.AbstractFileFormat;
import fileFormat.FileFormatType;

public class Xml extends AbstractFileFormat {
    public char startOfTag;
    public char endOfTag;
    public boolean isInMiddleOfTag;

    public Xml(FileFormatType fileFormatType) {
        this.startOfTag = '<';
        this.endOfTag = '>';
        this.isInMiddleOfTag = false;
        this.fileFormatType = fileFormatType;
    }

    @Override
    public boolean isByteRelatedToFileFormat(int data) {
        if (isInMiddleOfTag) {
            if ((char) data == endOfTag)
                isInMiddleOfTag = false;
            return true;
        } else {
            if ((char) data == startOfTag) {
                isInMiddleOfTag = true;
                return true;
            }
            return false;
        }
    }

}
