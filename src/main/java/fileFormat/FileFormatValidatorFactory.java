package fileFormat;

import status.IStatusProvider;
import fileFormat.formatValidator.CsvValidator;
import fileFormat.formatValidator.XmlValidator;

public class FileFormatValidatorFactory {
    public IStatusProvider getFileFormatValidator(FileFormatType fileFormatValidatorType) {
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
