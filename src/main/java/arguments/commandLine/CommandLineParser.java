package arguments.commandLine;

import arguments.extract.destinationExtractor.DestinationDTO;
import arguments.extract.destinationExtractor.IDestinationDTOExtractor;
import arguments.extract.manipulateActionsExtractor.ActionDTO;
import arguments.commandLine.extract.CommandLineActionsExtractor;
import arguments.extract.manipulateActionsExtractor.IActionsDTOExtractor;
import arguments.commandLine.extract.CommandLineSourceExtractor;
import arguments.commandLine.extract.CommandLineDestinationExtractor;
import arguments.extract.sourceExtractor.ISourceDTOExtractor;
import arguments.extract.sourceExtractor.SourceDTO;
import arguments.extract.sourceExtractor.SourceType;
import arguments.parse.ActionDTOParser;
import arguments.parse.DestinationDTOParser;
import arguments.parse.SourceDTOParser;
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
        SourceType sourceType = inputSourceDTO.getSourceType();
        switch (sourceType){
            case INPUT_FILE:
                String fileFormat = FilenameUtils.getExtension(inputSourceDTO.getSourceValue()).toUpperCase();
                String filterName = FilterType.FILTER_BASED_ON_FILE_TYPE.toString();

                for (ActionDTO actionDTO: actionDTOS){
                    actionDTO.addActionFilter(filterName, fileFormat);
                }
                break;
            default:
                System.out.println("No filter based on source exists");
        }
    }
}
