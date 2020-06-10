package commandLine;

import commandLine.clientArguments.CommandLineOptions;
import commandLine.clientArguments.IOption;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.ParseException;

public class CommandLineCreator {
    public org.apache.commons.cli.CommandLine getCommandLine(String[] clientArguments) throws ParseException {
        IOption options = new CommandLineOptions();
        org.apache.commons.cli.CommandLineParser parser = new DefaultParser();
        return parser.parse(options.getOptions(), clientArguments);
    }
}
