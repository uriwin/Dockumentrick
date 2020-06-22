package commandLine;

import commandLine.clientArguments.CommandLineArguments;
import commandLine.clientArguments.IClientArgument;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.ParseException;

public class CommandLineCreator {
    public org.apache.commons.cli.CommandLine getCommandLine(String[] clientArguments) throws ParseException {
        IClientArgument options = new CommandLineArguments();
        org.apache.commons.cli.CommandLineParser parser = new DefaultParser();
        return parser.parse(options.getArguments(), clientArguments);
    }
}
