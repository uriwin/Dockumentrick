package arguments.parse;

import arguments.extract.destinationExtractor.DestinationDTO;
import arguments.extract.destinationExtractor.DestinationType;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class DestinationDTOParser {

    public OutputStream parseDestinationDTO(DestinationDTO destinationDTO) throws FileNotFoundException {
        DestinationType destinationType = destinationDTO.getDestinationType();
        switch (destinationType) {
            case OUTPUT_FILE:
                return new FileOutputStream(destinationDTO.getDestinationValue());
            default:
                throw new IllegalArgumentException("Unsupported output source: " + destinationType.toString());
        }
    }
}
