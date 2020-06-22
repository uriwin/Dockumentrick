package fileFormat;

import status.IStatus;
import fileFormat.formats.CsvValidator;
import fileFormat.formats.XmlValidator;

public class FileFormatValidatorFactory {
    public IStatus getFileFormatValidator(FileFormatType fileFormatValidatorType) {
        switch (fileFormatValidatorType) {
            case TXT:
                return new BaseFileValidator(fileFormatValidatorType);
            case CSV:
                return new CsvValidator(fileFormatValidatorType);
            case XML:
                return new XmlValidator(fileFormatValidatorType);
            default:
                throw new IllegalArgumentException("unsupported file type");
        }
    }
}
