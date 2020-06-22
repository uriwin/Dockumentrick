package commandLine.parse;

import commandLine.extract.sourceExtractor.SourceDTO;
import commandLine.extract.sourceExtractor.SourceType;

import java.io.*;

public class SourceDTOParser {
    public InputStream parseSourceDTO(SourceDTO sourceDTO) throws FileNotFoundException {
        SourceType sourceType = sourceDTO.getSourceType();
        switch (sourceType){
            case INPUT_FILE:
                return new FileInputStream(sourceDTO.getSourceValue());
            default:
                throw new IllegalArgumentException("input source does not exists");
        }
    }
}
