package arguments.commandLine.extract;

import arguments.ArgumentTypeValidator;
import arguments.extract.sourceExtractor.ISourceDTOExtractor;
import arguments.extract.sourceExtractor.SourceDTO;
import arguments.extract.sourceExtractor.SourceType;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.MissingArgumentException;
import org.apache.commons.cli.Option;

public class CommandLineSourceExtractor implements ISourceDTOExtractor {

    private CommandLine commandLine;

    public CommandLineSourceExtractor(CommandLine commandLine) {
        this.commandLine = commandLine;
    }


    @Override
    public SourceDTO getSourceDTO() throws MissingArgumentException {
        SourceDTO inputSourceDTO = new SourceDTO();
        ArgumentTypeValidator argumentTypeValidator = new ArgumentTypeValidator();

        for (Option option : commandLine.getOptions()) {
            if (argumentTypeValidator.isArgumentRelatedToSource(option.getLongOpt())) {
                SourceType sourceType = SourceType.valueOf(option.getLongOpt());
                inputSourceDTO.setSourceType(sourceType);
                inputSourceDTO.setSourceValue(option.getValue());
                return inputSourceDTO;
            }
        }
        throw new MissingArgumentException("No input source available");
    }
}
