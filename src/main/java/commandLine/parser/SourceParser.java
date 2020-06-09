package commandLine.parser;

import commandLine.extracor.sourceExtractor.SourceDTO;
import commandLine.extracor.sourceExtractor.SourceType;

import java.io.*;

public class SourceParser {
    public InputStream getInputStream(SourceDTO sourceDTO) throws FileNotFoundException {
        SourceType sourceType = sourceDTO.getSourceType();
        switch (sourceType){
            case FILE:
                return new FileInputStream(sourceDTO.getSourceValue());
            default:
                throw new IllegalArgumentException("input source does not exists");
        }
    }

    public OutputStream getOutputStream(SourceDTO sourceDTO) throws FileNotFoundException {
        SourceType sourceType = sourceDTO.getSourceType();
        switch (sourceType){
            case FILE:
                return new FileOutputStream(sourceDTO.getSourceValue());
            default:
                throw new IllegalArgumentException("output source does not exists");
        }
    }
}
