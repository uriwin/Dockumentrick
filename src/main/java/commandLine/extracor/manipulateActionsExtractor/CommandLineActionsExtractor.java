package commandLine.extracor.manipulateActionsExtractor;

import commandLine.clientArguments.ArgumentTypeValidator;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;

import java.util.*;

public class CommandLineActionsExtractor implements IActionsExtractor {
    private CommandLine commandLine;

    public CommandLineActionsExtractor(CommandLine commandLine)
    {
        this.commandLine = commandLine;
    }

//    public FileFormatType getFileFormat(String filePath) {
//        String fileFormatTypeRaw = FilenameUtils.getExtension(filePath).toUpperCase();
//        return FileFormatType.valueOf(fileFormatTypeRaw);
//    }

    @Override
    public List<ActionDTO> getActions() {
        List<Option> arguments = Arrays.asList(commandLine.getOptions());
        List<ActionDTO> actions = new ArrayList<ActionDTO>();
        ArgumentTypeValidator argumentTypeValidator = new ArgumentTypeValidator();

        ActionDTO lastActionDTO = new ActionDTO();
        for (Option argument : arguments) {
            String argumentName = argument.getLongOpt();
            if (argumentTypeValidator.isArgumentAction(argumentName)) {
                if (!lastActionDTO.isEmpty()) {
                    actions.add(lastActionDTO);
                }
                lastActionDTO = handleAction(argument);
            }
            else if (argumentTypeValidator.isArgumentFilter(argumentName)){
                lastActionDTO = handleFilter(lastActionDTO, argument);
            }
        }
        if (!lastActionDTO.isEmpty()) {
            actions.add(lastActionDTO);
        }
        return actions;
    }

    public ActionDTO handleAction(Option argument){
        ActionDTO actionDTO = new ActionDTO();
        actionDTO.setManipulateAction(argument.getLongOpt());
        actionDTO.setActionArguments(argument.getValuesList());
        return actionDTO;
    }

    public ActionDTO handleFilter(ActionDTO actionDTO, Option argument){
        actionDTO.addActionFilter(argument.getLongOpt(), argument.getValue());
        return actionDTO;
    }
}
