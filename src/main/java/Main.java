import commandLine.CommandLineCreator;
import commandLine.parser.CommandLineParser;
import fileETL.FileETL;
import org.apache.commons.cli.*;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        CommandLineCreator commandLineCreator = new CommandLineCreator();
        CommandLine commandLine = commandLineCreator.getCommandLine(args);
        CommandLineParser commandLineParser = new CommandLineParser(commandLine);

        InputStream inputStream = commandLineParser.getInputStream();
        OutputStream outputStream = commandLineParser.getOutputStream();

        FileETL fileETL = new FileETL(inputStream,outputStream);
        fileETL.executeETL();
    }
}
