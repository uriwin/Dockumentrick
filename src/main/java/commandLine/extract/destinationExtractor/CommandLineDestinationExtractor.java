package commandLine.extract.destinationExtractor;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.MissingArgumentException;
import org.apache.commons.cli.Option;

public class CommandLineDestinationExtractor implements IDestinationDTOExtractor {
    private CommandLine commandLine;

    public CommandLineDestinationExtractor(CommandLine commandLine) {
        this.commandLine = commandLine;
    }


    @Override
    public DestinationDTO getDestinationDTO() throws MissingArgumentException {
        DestinationDTO destinationDTO = new DestinationDTO();

        for (Option option : commandLine.getOptions()) {
            try {
                DestinationType destinationType = DestinationType.valueOf(option.getLongOpt());
                destinationDTO.setDestinationType(destinationType);
                destinationDTO.setDestinationValue(option.getValue());
                return destinationDTO;
            } catch (Exception e) {}
        }
        throw new MissingArgumentException("No output source available");
    }
}
