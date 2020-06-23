package commandLine.parse;

import commandLine.extract.destinationExtractor.DestinationDTO;
import commandLine.extract.destinationExtractor.IDestinationDTOExtractor;
import commandLine.extract.manipulateActionsExtractor.ActionDTO;
import commandLine.extract.manipulateActionsExtractor.CommandLineActionsExtractor;
import commandLine.extract.manipulateActionsExtractor.IActionsDTOExtractor;
import commandLine.extract.sourceExtractor.CommandLineSourceExtractor;
import commandLine.extract.destinationExtractor.CommandLineDestinationExtractor;
import commandLine.extract.sourceExtractor.ISourceDTOExtractor;
import commandLine.extract.sourceExtractor.SourceDTO;
import commandLine.extract.sourceExtractor.SourceType;
import fileFilter.FilterType;
import inputStream.ManipulatorInputStreamDecorator;
import manipulateActions.ManipulateAction;
import org.apache.commons.cli.*;
import org.apache.commons.io.FilenameUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class CommandLineParser {
    private CommandLine commandLine;

    private final SourceDTOParser sourceDTOParser;

    private final DestinationDTOParser destinationDTOParser;

    private final ActionDTOParser manipulateActionParser;

    public CommandLineParser(CommandLine commandLine){
        this.commandLine = commandLine;

        this.sourceDTOParser = new SourceDTOParser();

        this.destinationDTOParser = new DestinationDTOParser();

        this.manipulateActionParser = new ActionDTOParser();
    }

    public InputStream getInputStream() throws MissingArgumentException, IOException {
        ISourceDTOExtractor sourceDTOExtractor = new CommandLineSourceExtractor(commandLine);
        IActionsDTOExtractor actionsDTOExtractor = new CommandLineActionsExtractor(commandLine);

        SourceDTO sourceDTO = sourceDTOExtractor.getSourceDTO();
        List<ActionDTO> actionsDTO = actionsDTOExtractor.getActionsDTO();

        addFilterBasedOnSource(sourceDTO, actionsDTO);

        InputStream inputStream = sourceDTOParser.parseSourceDTO(sourceDTO);
        List<ManipulateAction> manipulateActions = manipulateActionParser.parseActionsDTO(actionsDTO);

        ManipulatorInputStreamDecorator dataManipulatorInputStreamDecorator =
                new ManipulatorInputStreamDecorator(manipulateActions, inputStream);

        return dataManipulatorInputStreamDecorator.getDataManipulatorInputStream();
    }

    public OutputStream getOutputStream() throws MissingArgumentException, IOException {
        IDestinationDTOExtractor destinationDTOExtractor = new CommandLineDestinationExtractor(commandLine);
        DestinationDTO destinationDTO = destinationDTOExtractor.getDestinationDTO();
        return destinationDTOParser.parseDestinationDTO(destinationDTO);
    }

    private void addFilterBasedOnSource(SourceDTO inputSourceDTO, List<ActionDTO> actionDTOS){
        if (inputSourceDTO.getSourceType() == SourceType.INPUT_FILE){
            String fileFormat = FilenameUtils.getExtension(inputSourceDTO.getSourceValue()).toUpperCase();
            String filterName = FilterType.FILTER_BASED_ON_FILE_TYPE.toString();

            for (ActionDTO actionDTO: actionDTOS){
                actionDTO.addActionFilter(filterName, fileFormat);
            }
        }
    }
}
