import commandLine.parser.ManipulateActionParser;
import commandLine.clientArguments.IClientArguments;
import commandLine.extracor.sourceExtractor.CommandLineInputSourceExtractor;
import commandLine.extracor.sourceExtractor.CommandLineOutputSourceExtractor;
import commandLine.extracor.sourceExtractor.ISourceExtractor;
import commandLine.extracor.sourceExtractor.SourceDTO;
import commandLine.extracor.manipulateActionsExtractor.IActionsExtractor;
import commandLine.extracor.manipulateActionsExtractor.CommandLineActionsExtractor;
import commandLine.clientArguments.CommandLineArguments;
import commandLine.extracor.manipulateActionsExtractor.ActionDTO;
import commandLine.parser.SourceParser;
import fileETL.FileETL;
import inputStream.DataManipulatorInputStreamDecorator;
import manipulateActions.ManipulateAction;
import org.apache.commons.cli.*;

import java.io.*;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        IClientArguments commandLineArguments = new CommandLineArguments();
        CommandLineParser parser = new DefaultParser();
        CommandLine commandLine = parser.parse(commandLineArguments.getClientOptions(), args);

        IActionsExtractor actionsExtractor = new CommandLineActionsExtractor(commandLine);
        ISourceExtractor inputSourceExtractor = new CommandLineInputSourceExtractor(commandLine);
        ISourceExtractor outputSourceExtractor = new CommandLineOutputSourceExtractor(commandLine);
        ManipulateActionParser manipulateActionParser = new ManipulateActionParser();
        SourceParser sourceParser = new SourceParser();

        List<ActionDTO> actionDTOS = actionsExtractor.getActions();
        SourceDTO inputSourceDTO = inputSourceExtractor.getSource();
        SourceDTO outputSourceDTO = outputSourceExtractor.getSource();

        List<ManipulateAction> manipulateActions = manipulateActionParser.parseActionsDTO(actionDTOS);
        InputStream inputStream = sourceParser.getInputStream(inputSourceDTO);
        OutputStream outputStream = sourceParser.getOutputStream(outputSourceDTO);

        DataManipulatorInputStreamDecorator dataManipulatorInputStreamDecorator =
                new DataManipulatorInputStreamDecorator(manipulateActions, inputStream);
        inputStream = dataManipulatorInputStreamDecorator.getDataManipulatorInputStream();

        FileETL fileETL = new FileETL(inputStream, outputStream);
        fileETL.executeETL();
    }
}
