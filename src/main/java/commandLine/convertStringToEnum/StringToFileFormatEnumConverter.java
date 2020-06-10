package commandLine.convertStringToEnum;

import fileFormat.FileFormatValidatorType;

public class StringToFileFormatEnumConverter {
    public FileFormatValidatorType getFileFormatValidatorType(String fileFormat){
        switch (fileFormat){
            case "XML":
                return FileFormatValidatorType.XML_VALIDATOR;
            case "CSV":
                return FileFormatValidatorType.CSV_VALIDATOR;
            case "TXT":
                return FileFormatValidatorType.TXT_VALIDATOR;
            default:
                throw new IllegalArgumentException("File type:" + fileFormat + " not supported");
        }
    }
}
