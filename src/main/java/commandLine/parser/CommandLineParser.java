package commandLine.parser;

import commandLine.clientArguments.CommandLineArguments;
import commandLine.clientArguments.IClientArguments;
import commandLine.extracor.manipulateActionsExtractor.ActionDTO;
import commandLine.extracor.manipulateActionsExtractor.CommandLineActionsExtractor;
import commandLine.extracor.manipulateActionsExtractor.IActionsExtractor;
import commandLine.extracor.sourceExtractor.CommandLineInputSourceExtractor;
import commandLine.extracor.sourceExtractor.CommandLineOutputSourceExtractor;
import commandLine.extracor.sourceExtractor.ISourceExtractor;
import commandLine.extracor.sourceExtractor.SourceDTO;
import commandLine.parser.ManipulateActionParser;
import commandLine.parser.SourceParser;
import fileETL.FileETL;
import inputStream.DataManipulatorInputStreamDecorator;
import manipulateActions.ManipulateAction;
import org.apache.commons.cli.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class CommandLineParser {
    private CommandLine commandLine;

    private SourceParser sourceParser;

    private ManipulateActionParser manipulateActionParser;

    public CommandLineParser(CommandLine commandLine){
        this.commandLine = commandLine;

        this.sourceParser = new SourceParser();

        this.manipulateActionParser = new ManipulateActionParser();
    }

    public InputStream getInputStream() throws MissingArgumentException, IOException {
        ISourceExtractor inputSourceExtractor = new CommandLineInputSourceExtractor(commandLine);
        IActionsExtractor actionsExtractor = new CommandLineActionsExtractor(commandLine);

        SourceDTO inputSourceDTO = inputSourceExtractor.getSource();
        List<ActionDTO> actionDTOS = actionsExtractor.getActions();

        InputStream inputStream = sourceParser.getInputStream(inputSourceDTO);
        List<ManipulateAction> manipulateActions = manipulateActionParser.parseActionsDTO(actionDTOS);

        DataManipulatorInputStreamDecorator dataManipulatorInputStreamDecorator =
                new DataManipulatorInputStreamDecorator(manipulateActions, inputStream);

        return dataManipulatorInputStreamDecorator.getDataManipulatorInputStream();
    }

    public OutputStream getOutputStream() throws MissingArgumentException, IOException {
        ISourceExtractor outputSourceExtractor = new CommandLineOutputSourceExtractor(commandLine);
        SourceDTO outputSourceDTO = outputSourceExtractor.getSource();
        return sourceParser.getOutputStream(outputSourceDTO);
    }
}
