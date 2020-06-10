import commandLine.CommandLineCreator;
import commandLine.parse.CommandLineParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.ParseException;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        CommandLineCreator commandLineCreator = new CommandLineCreator();
        CommandLine commandLine = commandLineCreator.getCommandLine(args);
        CommandLineParser commandLineParser = new CommandLineParser(commandLine);

        InputStream inputStream = commandLineParser.getInputStream();
        OutputStream outputStream = commandLineParser.getOutputStream();

        IOUtils.copy(inputStream, outputStream);
    }
}
