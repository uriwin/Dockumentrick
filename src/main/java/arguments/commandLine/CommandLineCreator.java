package arguments.commandLine;

import arguments.IArgumentsRequired;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.CommandLine;

public class CommandLineCreator {
    public CommandLine createCommandLine(String[] clientArguments, IArgumentsRequired argumentsRequired) throws ParseException {
        CommandLineParser commandLineParser = new DefaultParser();
        return commandLineParser.parse(argumentsRequired.getArguments(), clientArguments);
    }
}
