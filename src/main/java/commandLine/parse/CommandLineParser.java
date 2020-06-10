package commandLine.parse;

import commandLine.extracte.manipulateActionsExtractor.ActionDTO;
import commandLine.extracte.manipulateActionsExtractor.CommandLineActionsExtractor;
import commandLine.extracte.manipulateActionsExtractor.IActionsExtractor;
import commandLine.extracte.sourceExtractor.CommandLineInputSourceExtractor;
import commandLine.extracte.sourceExtractor.CommandLineOutputSourceExtractor;
import commandLine.extracte.sourceExtractor.ISourceExtractor;
import commandLine.extracte.sourceExtractor.SourceDTO;
import inputStream.DataManipulatorInputStreamDecorator;
import manipulateActions.ManipulateAction;
import org.apache.commons.cli.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class CommandLineParser {
    private CommandLine commandLine;

    private SourceDTOParser sourceParser;

    private ActionDTOParser manipulateActionParser;

    public CommandLineParser(CommandLine commandLine){
        this.commandLine = commandLine;

        this.sourceParser = new SourceDTOParser();

        this.manipulateActionParser = new ActionDTOParser();
    }

    public InputStream getInputStream() throws MissingArgumentException, IOException {
        ISourceExtractor inputSourceExtractor = new CommandLineInputSourceExtractor(commandLine);
        IActionsExtractor actionsExtractor = new CommandLineActionsExtractor(commandLine);

        SourceDTO inputSourceDTO = inputSourceExtractor.getSource();
        List<ActionDTO> actionDTOS = actionsExtractor.getActions();

        InputStream inputStream = sourceParser.parseInputSourceDTO(inputSourceDTO);
        List<ManipulateAction> manipulateActions = manipulateActionParser.parseActionsDTO(actionDTOS);

        DataManipulatorInputStreamDecorator dataManipulatorInputStreamDecorator =
                new DataManipulatorInputStreamDecorator(manipulateActions, inputStream);

        return dataManipulatorInputStreamDecorator.getDataManipulatorInputStream();
    }

    public OutputStream getOutputStream() throws MissingArgumentException, IOException {
        ISourceExtractor outputSourceExtractor = new CommandLineOutputSourceExtractor(commandLine);
        SourceDTO outputSourceDTO = outputSourceExtractor.getSource();
        return sourceParser.parseOutputSourceDTO(outputSourceDTO);
    }
}
