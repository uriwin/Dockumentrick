package commandLine.manipulateActionsExtractor;

import commandLine.ManipulatorActionParser;
import manipulateActions.ManipulateAction;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;

import java.util.Iterator;
import java.util.List;

public class commandLineActionsExtractor implements IActionsExtractor {
    private CommandLine commandLine;

    public commandLineActionsExtractor(CommandLine commandLine) {
        this.commandLine = commandLine;
    }

//    public FileFormatType getFileFormat(String filePath) {
//        String fileFormatTypeRaw = FilenameUtils.getExtension(filePath).toUpperCase();
//        return FileFormatType.valueOf(fileFormatTypeRaw);
//    }
//    @Override
//    public InputStream getInputStream() throws FileNotFoundException {
//        return new FileInputStream(commandLine.getOptionValue("inputPath"));
//    }
//
//    @Override
//    public OutputStream getOutputStream() throws FileNotFoundException {
//        String inputFilePath = commandLine.getOptionValue("inputPath");
//        String outputFilePath = FilenameUtils.getBaseName(inputFilePath)
//                + "_result." + FilenameUtils.getExtension(inputFilePath);
//        return new FileOutputStream(outputFilePath);
//    }

    @Override
    public List<ManipulateAction> getActions() {
        ManipulatorActionParser manipulatorActionParser = new ManipulatorActionParser();
        Iterator<Option> argumentsIterator = commandLine.iterator();
        argumentsIterator.next(); // first argument should be input path.
        return manipulatorActionParser.getDataManipulationActions(argumentsIterator);
    }
}
