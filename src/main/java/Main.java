import cli.Cli;
import cli.dockumentrick.DockumentrickCliParser;
import cli.dockumentrick.DockumentrickOptions;
import inputStream.DataManipulatorInputStreamDecorator;
import fileETL.FileETL;
import fileFormat.FileFormatType;
import manipulateActions.ManipulateAction;
import org.apache.commons.cli.ParseException;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        DockumentrickOptions cliOptions = new DockumentrickOptions();
        Cli cli = new Cli(args, cliOptions.cliOptions);
        DockumentrickCliParser dockumentrickCliParser = new DockumentrickCliParser(cli.getCli());

        String inputPath = dockumentrickCliParser.getInputFilePath();
        String outputPath = dockumentrickCliParser.getOutputFilePath();
        FileFormatType fileFormatType = dockumentrickCliParser.getFileFormat(inputPath);
        List<ManipulateAction> manipulateActions = dockumentrickCliParser.getManipulateActions();
        InputStream fileInputStream = new FileInputStream(inputPath);

        DataManipulatorInputStreamDecorator dataManipulatorInputStreamDecorator =
                new DataManipulatorInputStreamDecorator(fileFormatType, manipulateActions, fileInputStream);
        OutputStream outputStream = new FileOutputStream(outputPath);
        InputStream inputStream = dataManipulatorInputStreamDecorator.getDataManipulatorInputStream();
//        IOUtils.copy(inputStream, outputStream);
        FileETL fileETL = new FileETL(inputStream, outputStream);
        fileETL.executeETL();
    }
}
