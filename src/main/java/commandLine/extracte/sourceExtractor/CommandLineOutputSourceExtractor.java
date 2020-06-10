package commandLine.extracte.sourceExtractor;

import commandLine.clientArguments.ArgumentTypeValidator;
import commandLine.convertStringToEnum.StringToOutputSourceEnumConverter;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.MissingArgumentException;
import org.apache.commons.cli.Option;

public class CommandLineOutputSourceExtractor implements ISourceExtractor {
    private CommandLine commandLine;

    public CommandLineOutputSourceExtractor(CommandLine commandLine) {
        this.commandLine = commandLine;
    }


    @Override
    public SourceDTO getSource() throws MissingArgumentException {
        SourceDTO inputSourceDTO = new SourceDTO();
        ArgumentTypeValidator argumentTypeValidator = new ArgumentTypeValidator();
        for (Option option : commandLine.getOptions()) {
            if (argumentTypeValidator.isArgumentOutputSource(option.getLongOpt())) {
                StringToOutputSourceEnumConverter stringToOutputSourceEnumConverter = new StringToOutputSourceEnumConverter();
                inputSourceDTO.setSourceType(stringToOutputSourceEnumConverter.getOutputSourceType(option.getLongOpt()));
                inputSourceDTO.setSourceValue(option.getValue());
                return inputSourceDTO;
            }
        }
        throw new MissingArgumentException("No output source available");
    }
}
