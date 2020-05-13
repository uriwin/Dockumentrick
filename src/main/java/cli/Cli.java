package cli;

import org.apache.commons.cli.*;

public class Cli {
    private CommandLine cli;
    private Options options;
    private CommandLineParser parser;

    public Cli(String[] args, Options options) throws ParseException {
        this.options = options;
        this.parser = new DefaultParser();
        this.cli =  this.parser.parse(options, args);
    }

    public CommandLine getCli() {
        return cli;
    }
}