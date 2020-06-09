package commandLine.convertStringToEnum;

import commandLine.extracor.sourceExtractor.SourceType;

public class StringToInputSourceEnumConverter {
    public SourceType getInputSourceType(String sourceType) {
        switch (sourceType) {
            case "inputFilePath":
                return SourceType.FILE;
            default:
                throw new IllegalArgumentException("Input source: " + sourceType + " does not exists");
        }
    }
}
