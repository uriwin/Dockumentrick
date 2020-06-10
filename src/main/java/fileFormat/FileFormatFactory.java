package fileFormat;

import status.IStatus;
import fileFormat.formats.CsvValidator;
import fileFormat.formats.XmlValidator;

public class FileFormatFactory {
    public IStatus getFileFormatValidator(FileFormatValidatorType fileFormatValidatorType) {
        switch (fileFormatValidatorType) {
            case TXT_VALIDATOR:
                return new BaseFileValidator(fileFormatValidatorType);
            case CSV_VALIDATOR:
                return new CsvValidator(fileFormatValidatorType);
            case XML_VALIDATOR:
                return new XmlValidator(fileFormatValidatorType);
            default:
                throw new IllegalArgumentException("unsupported file type");
        }
    }
}
