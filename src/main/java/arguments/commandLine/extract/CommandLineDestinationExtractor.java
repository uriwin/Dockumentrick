package arguments.commandLine.extract;

import arguments.ArgumentTypeValidator;
import arguments.extract.destinationExtractor.DestinationDTO;
import arguments.extract.destinationExtractor.DestinationType;
import arguments.extract.destinationExtractor.IDestinationDTOExtractor;
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
        ArgumentTypeValidator argumentTypeValidator = new ArgumentTypeValidator();

        for (Option option : commandLine.getOptions()) {
            if (argumentTypeValidator.isArgumentRelatedToDestination(option.getLongOpt())){
                DestinationType destinationType = DestinationType.valueOf(option.getLongOpt());
                destinationDTO.setDestinationType(destinationType);
                destinationDTO.setDestinationValue(option.getValue());
                return destinationDTO;
            }
        }
        throw new MissingArgumentException("No output source available");
    }
}
