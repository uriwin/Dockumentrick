import arguments.IArgumentsRequired;
import arguments.commandLine.CommandLineArguments;
import arguments.commandLine.CommandLineCreator;
import arguments.commandLine.CommandLineParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.ParseException;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        CommandLineCreator commandLineCreator = new CommandLineCreator();
        IArgumentsRequired argumentsRequired = new CommandLineArguments();

        CommandLine commandLine = commandLineCreator.createCommandLine(args, argumentsRequired);
        CommandLineParser commandLineParser = new CommandLineParser(commandLine);

        InputStream inputStream = commandLineParser.getInputStream();
        OutputStream outputStream = commandLineParser.getOutputStream();

        IOUtils.copy(inputStream, outputStream);
    }
}
