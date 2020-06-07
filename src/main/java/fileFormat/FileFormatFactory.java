package fileFormat;

import fileFormat.formats.CsvValidator;
import fileFormat.formats.XmlValidator;

public class FileFormatFactory {
    public BaseFileValidator getFileFormat(FileFormatType fileFormatType) {
        switch (fileFormatType) {
            case TXT:
                return new BaseFileValidator(fileFormatType);
            case CSV:
                return new CsvValidator(fileFormatType);
            case XML:
                return new XmlValidator(fileFormatType);
            default:
                throw new IllegalArgumentException("unsupported file type");
        }
    }
}
