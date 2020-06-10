package commandLine.parse;

import commandLine.extracte.sourceExtractor.SourceDTO;
import commandLine.extracte.sourceExtractor.SourceType;

import java.io.*;

public class SourceDTOParser {
    public InputStream parseInputSourceDTO(SourceDTO sourceDTO) throws FileNotFoundException {
        SourceType sourceType = sourceDTO.getSourceType();
        switch (sourceType){
            case FILE:
                return new FileInputStream(sourceDTO.getSourceValue());
            default:
                throw new IllegalArgumentException("input source does not exists");
        }
    }

    public OutputStream parseOutputSourceDTO(SourceDTO sourceDTO) throws FileNotFoundException {
        SourceType sourceType = sourceDTO.getSourceType();
        switch (sourceType){
            case FILE:
                return new FileOutputStream(sourceDTO.getSourceValue());
            default:
                throw new IllegalArgumentException("output source does not exists");
        }
    }
}
