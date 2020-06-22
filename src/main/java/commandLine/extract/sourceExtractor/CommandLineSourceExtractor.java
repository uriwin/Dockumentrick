package commandLine.extract.sourceExtractor;

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
        for (Option option : commandLine.getOptions()) {
            try {
                SourceType sourceType = SourceType.valueOf(option.getLongOpt());
                inputSourceDTO.setSourceType(sourceType);
                inputSourceDTO.setSourceValue(option.getValue());
                return inputSourceDTO;
            }
            catch (Exception e) { }
        }
        throw new MissingArgumentException("No input source available");
    }
}
