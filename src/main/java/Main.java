import commandLine.dockumentrick.DockumentrickCliParser;
import commandLine.dockumentrick.DockumentrickOptions;
import inputStream.DataManipulatorInputStreamDecorator;
import fileETL.FileETL;
import fileFormat.FileFormatType;
import manipulateActions.ManipulateAction;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.ParseException;

import java.io.*;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        DockumentrickOptions cliOptions = new DockumentrickOptions();
        CommandLineParser parser = new DefaultParser();
        CommandLine commandLine = parser.parse(cliOptions.cliOptions, args);
        DockumentrickCliParser dockumentrickCliParser = new DockumentrickCliParser(commandLine);

        String inputPath = dockumentrickCliParser.getInputFilePath();
        String outputPath = dockumentrickCliParser.getOutputFilePath();
        FileFormatType fileFormatType = dockumentrickCliParser.getFileFormat(inputPath);
        List<ManipulateAction> manipulateActions = dockumentrickCliParser.getManipulateActions();
        InputStream fileInputStream = new FileInputStream(inputPath);

        DataManipulatorInputStreamDecorator dataManipulatorInputStreamDecorator =
                new DataManipulatorInputStreamDecorator(fileFormatType, manipulateActions, fileInputStream);
        OutputStream outputStream = new FileOutputStream(outputPath);
        InputStream inputStream = dataManipulatorInputStreamDecorator.getDataManipulatorInputStream();

        FileETL fileETL = new FileETL(inputStream, outputStream);
        fileETL.executeETL();
    }
}
