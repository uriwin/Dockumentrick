package commandLine.extract.destinationExtractor;

import commandLine.extract.sourceExtractor.SourceDTO;
import org.apache.commons.cli.MissingArgumentException;

public interface IDestinationDTOExtractor {
    public DestinationDTO getDestinationDTO() throws MissingArgumentException;
}
