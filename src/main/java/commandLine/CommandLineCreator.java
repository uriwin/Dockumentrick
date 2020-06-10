package commandLine;

import commandLine.clientArguments.CommandLineArguments;
import commandLine.clientArguments.IClientArguments;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.ParseException;

public class CommandLineCreator {
    public org.apache.commons.cli.CommandLine getCommandLine(String[] args) throws ParseException {
        IClientArguments commandLineArguments = new CommandLineArguments();
        org.apache.commons.cli.CommandLineParser parser = new DefaultParser();
        return parser.parse(commandLineArguments.getClientOptions(), args);
    }
}
