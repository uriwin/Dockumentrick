package commandLine;

import arguments.CommandLineArguments;
import arguments.IArgumentsRequired;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.ParseException;

public class CommandLineCreator {
    public org.apache.commons.cli.CommandLine getCommandLine(String[] clientArguments) throws ParseException {
        IArgumentsRequired argumentsRequired = new CommandLineArguments();
        org.apache.commons.cli.CommandLineParser parser = new DefaultParser();
        return parser.parse(argumentsRequired.getArgumentsRequired(), clientArguments);
    }
}
