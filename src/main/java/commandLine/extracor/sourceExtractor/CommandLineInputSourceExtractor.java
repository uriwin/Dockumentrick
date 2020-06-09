package commandLine.extracor.sourceExtractor;

import commandLine.clientArguments.ArgumentTypeValidator;
import commandLine.convertStringToEnum.StringToInputSourceEnumConverter;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.MissingArgumentException;
import org.apache.commons.cli.Option;

public class CommandLineInputSourceExtractor implements ISourceExtractor {

    private CommandLine commandLine;

    public CommandLineInputSourceExtractor(CommandLine commandLine){
        this.commandLine = commandLine;
    }


    @Override
    public SourceDTO getSource() throws MissingArgumentException {
        SourceDTO inputSourceDTO = new SourceDTO();
        ArgumentTypeValidator argumentTypeValidator = new ArgumentTypeValidator();
        for (Option option:commandLine.getOptions()){
            if (argumentTypeValidator.isArgumentInputSource(option.getLongOpt())){
                StringToInputSourceEnumConverter stringToInputSourceEnumConverter = new StringToInputSourceEnumConverter();
                inputSourceDTO.setSourceType(stringToInputSourceEnumConverter.getInputSourceType(option.getLongOpt()));
                inputSourceDTO.setSourceValue(option.getValue());
                return inputSourceDTO;
            }
        }
        throw new MissingArgumentException("No input source available");
    }
}
