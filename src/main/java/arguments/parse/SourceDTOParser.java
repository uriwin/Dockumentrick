package arguments.parse;

import arguments.extract.sourceExtractor.SourceDTO;
import arguments.extract.sourceExtractor.SourceType;

import java.io.*;

public class SourceDTOParser {
    public InputStream parseSourceDTO(SourceDTO sourceDTO) throws FileNotFoundException {
        SourceType sourceType = sourceDTO.getSourceType();
        switch (sourceType){
            case INPUT_FILE:
                return new FileInputStream(sourceDTO.getSourceValue());
            default:
                throw new IllegalArgumentException("Unsupported input source: " + sourceType.toString());
        }
    }
}
