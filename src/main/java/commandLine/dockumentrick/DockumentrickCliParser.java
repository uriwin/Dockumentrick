package commandLine.dockumentrick;

import fileFormat.FileFormatType;
import manipulateActions.ManipulateAction;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.io.FilenameUtils;

import java.util.Iterator;
import java.util.List;

public class DockumentrickCliParser {
    private CommandLine cli;

    public DockumentrickCliParser(CommandLine cli) {
        this.cli = cli;
    }

    public List<ManipulateAction> getManipulateActions(){
        ManipulatorActionParser manipulatorActionParser = new ManipulatorActionParser();
        Iterator<Option> argumentsIterator = cli.iterator();
        argumentsIterator.next(); // first argument should be input path.
        return manipulatorActionParser.getDataManipulationActions(argumentsIterator);
    }

    public String getOutputFilePath() {
        String inputFilePath = getInputFilePath();
        return FilenameUtils.getBaseName(inputFilePath) + "_result." + FilenameUtils.getExtension(inputFilePath);
    }

    public FileFormatType getFileFormat(String filePath) {
        String fileFormatTypeRaw = FilenameUtils.getExtension(filePath).toUpperCase();
        return FileFormatType.valueOf(fileFormatTypeRaw);
    }

    public String getInputFilePath(){
        return cli.getOptionValue("inputPath");
    }
}
