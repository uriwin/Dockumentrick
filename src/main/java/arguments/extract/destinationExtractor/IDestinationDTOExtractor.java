package arguments.extract.destinationExtractor;

import org.apache.commons.cli.MissingArgumentException;

public interface IDestinationDTOExtractor {
    public DestinationDTO getDestinationDTO() throws MissingArgumentException;
}
