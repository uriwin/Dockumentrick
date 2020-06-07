package fileFormat.formats;

import fileFormat.BaseFileValidator;
import fileFormat.FileFormatType;

public class XmlValidator extends BaseFileValidator {
    public boolean isInMiddleOfTag;

    final static char START_OF_TAG = '<';

    final static char END_OF_TAG = '>';

    public XmlValidator(FileFormatType fileFormatType) {
        super(fileFormatType);

        this.isInMiddleOfTag = false;

        this.fileFormatType = fileFormatType;
    }

    @Override
    public boolean isByteRelatedToFileFormat(int data) {
        if (isInMiddleOfTag) {
            if ((char) data == END_OF_TAG){
                isInMiddleOfTag = false;
            }
            return true;
        }
        else {
            if ((char) data == START_OF_TAG) {
                isInMiddleOfTag = true;
                return true;
            }
            return false;
        }
    }

}
