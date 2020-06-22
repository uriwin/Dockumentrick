package commandLine.parse;

import commandLine.extract.destinationExtractor.DestinationDTO;
import commandLine.extract.destinationExtractor.IDestinationExtractor;
import commandLine.extract.manipulateActionsExtractor.ActionDTO;
import commandLine.extract.manipulateActionsExtractor.CommandLineActionsExtractor;
import commandLine.extract.manipulateActionsExtractor.IActionsExtractor;
import commandLine.extract.sourceExtractor.CommandLineInputSourceExtractor;
import commandLine.extract.destinationExtractor.CommandLineOutputSourceExtractor;
import commandLine.extract.sourceExtractor.ISourceExtractor;
import commandLine.extract.sourceExtractor.SourceDTO;
import inputStream.DataManipulatorInputStreamDecorator;
import manipulateActions.ManipulateAction;
import org.apache.commons.cli.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class CommandLineParser {
    private CommandLine commandLine;

    private SourceDTOParser sourceDTOParser;

    private DestinationDTOParser destinationDTOParser;

    private ActionDTOParser manipulateActionParser;

    public CommandLineParser(CommandLine commandLine){
        this.commandLine = commandLine;

        this.sourceDTOParser = new SourceDTOParser();

        this.destinationDTOParser = new DestinationDTOParser();

        this.manipulateActionParser = new ActionDTOParser();
    }

    public InputStream getInputStream() throws MissingArgumentException, IOException {
        ISourceExtractor inputSourceExtractor = new CommandLineInputSourceExtractor(commandLine);
        IActionsExtractor actionsExtractor = new CommandLineActionsExtractor(commandLine);

        SourceDTO inputSourceDTO = inputSourceExtractor.getSource();
        List<ActionDTO> actionDTOS = actionsExtractor.getActions();

        InputStream inputStream = sourceDTOParser.parseSourceDTO(inputSourceDTO);
        List<ManipulateAction> manipulateActions = manipulateActionParser.parseActionsDTO(actionDTOS);

        DataManipulatorInputStreamDecorator dataManipulatorInputStreamDecorator =
                new DataManipulatorInputStreamDecorator(manipulateActions, inputStream);

        return dataManipulatorInputStreamDecorator.getDataManipulatorInputStream();
    }

    public OutputStream getOutputStream() throws MissingArgumentException, IOException {
        IDestinationExtractor destinationExtractor = new CommandLineOutputSourceExtractor(commandLine);
        DestinationDTO destinationDTO = destinationExtractor.getDestination();
        return destinationDTOParser.parseDestinationDTO(destinationDTO);
    }
}
