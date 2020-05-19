import cli.Cli;
import cli.dockumentrick.DockumentrickCliParser;
import cli.dockumentrick.DockumentrickOptions;
import inputStream.DataManipulatorInputStreamDecorator;
import fileETL.FileETL;
import fileFormat.FileFormatType;
import manipulateActions.ManipulateAction;
import org.apache.commons.cli.ParseException;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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

        DataManipulatorInputStreamDecorator dataManipulatorInputStreamDecorator =
                new DataManipulatorInputStreamDecorator(inputPath, fileFormatType, manipulateActions);
        OutputStream outputStream = new FileOutputStream(outputPath);
        InputStream inputStream = dataManipulatorInputStreamDecorator.getDataManipulatorInputStream();

        FileETL fileETL = new FileETL(inputStream, outputStream);
        fileETL.executeETL();
    }
}
