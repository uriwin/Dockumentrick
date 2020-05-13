import cli.Cli;
import cli.dockumentrick.DockumentrickCliParser;
import cli.dockumentrick.DockumentrickOptions;
import fileETL.FileETL;
import fileFormat.FileFormatType;
import manipulateActions.ManipulateAction;
import org.apache.commons.cli.ParseException;

import java.io.IOException;
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

        FileETL fileETL = new FileETL(inputPath, outputPath, fileFormatType, manipulateActions);
        fileETL.executeETL();
    }
}
