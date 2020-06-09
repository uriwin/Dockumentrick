package commandLine.convertStringToEnum;

import commandLine.extracor.sourceExtractor.SourceType;

public class StringToOutputSourceEnumConverter {
    public SourceType getOutputSourceType(String sourceType) {
        switch (sourceType) {
            case "outputFilePath":
                return SourceType.FILE;
            default:
                throw new IllegalArgumentException("Output source: " + sourceType + " does not exists");
        }
    }
}
