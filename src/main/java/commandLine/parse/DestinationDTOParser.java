package commandLine.parse;

import commandLine.extract.destinationExtractor.DestinationDTO;
import commandLine.extract.destinationExtractor.DestinationType;
import commandLine.extract.sourceExtractor.SourceDTO;
import commandLine.extract.sourceExtractor.SourceType;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class DestinationDTOParser {

    public OutputStream parseDestinationDTO(DestinationDTO destinationDTO) throws FileNotFoundException {
        DestinationType destinationType = destinationDTO.getDestinationType();
        switch (destinationType){
            case OUTPUT_FILE:
                return new FileOutputStream(destinationDTO.getDestinationValue());
            default:
                throw new IllegalArgumentException("output source does not exists");
        }
    }
}
