package fileFormat;

import fileFormat.formats.Csv;
import fileFormat.formats.Txt;
import fileFormat.formats.Xml;

public class FileFormatFactory {
    public AbstractFileFormat getFileFormat(FileFormatType fileFormatType) {
        switch (fileFormatType) {
            case TXT:
                return new Txt(fileFormatType);
            case CSV:
                return new Csv(fileFormatType);
            case XML:
                return new Xml(fileFormatType);
            default:
                throw new IllegalArgumentException("Invalid file format type");
        }
    }
}
