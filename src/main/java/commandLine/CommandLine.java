package commandLine;

import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class CommandLine {
    private org.apache.commons.cli.CommandLine commandLine;

    private Options options;

    private CommandLineParser parser;

    public CommandLine(String[] args, Options options) throws ParseException {
        this.options = options;

        this.parser = new DefaultParser();

        this.commandLine = this.parser.parse(options, args);
    }

    public org.apache.commons.cli.CommandLine getCommandLine() {
        return commandLine;
    }
}