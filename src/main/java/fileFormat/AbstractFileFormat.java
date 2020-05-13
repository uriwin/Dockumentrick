package fileFormat;

public abstract class AbstractFileFormat {
    public FileFormatType fileFormatType;

    public boolean isByteRelatedToFileFormat(int data) {
        return false;
    }
}
