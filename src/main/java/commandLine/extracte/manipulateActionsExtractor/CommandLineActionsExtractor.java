package commandLine.extracte.manipulateActionsExtractor;

import commandLine.clientArguments.ArgumentTypeValidator;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.io.FilenameUtils;

import java.util.*;

public class CommandLineActionsExtractor implements IActionsExtractor {
    private CommandLine commandLine;

    private Option[] arguments;
    public CommandLineActionsExtractor(CommandLine commandLine) {
        this.commandLine = commandLine;

        this.arguments=commandLine.getOptions();
    }

    @Override
    public List<ActionDTO> getActions() {
        List<ActionDTO> actions = new ArrayList<ActionDTO>();
        ArgumentTypeValidator argumentTypeValidator = new ArgumentTypeValidator();
        Map<String,String> globalFilters = new HashMap<String, String>();
        ActionDTO lastActionDTO = new ActionDTO();

        for (Option argument : arguments) {
            String argumentName = argument.getLongOpt();
            if (argumentTypeValidator.isArgumentAction(argumentName)) {
                if (!lastActionDTO.isEmpty()) {
                    actions.add(lastActionDTO);
                }
                lastActionDTO = initializeActionDTO(argument, globalFilters);
            }
            else if (argumentTypeValidator.isArgumentConnectedToInputFileSource(argumentName)){
                globalFilters.put(argumentName, FilenameUtils.getExtension(argument.getValue()).toUpperCase());
            }
            else if (argumentTypeValidator.isArgumentFilter(argumentName)) {
                lastActionDTO.addActionFilter(argumentName, argument.getValue());
            }
        }

        if (!lastActionDTO.isEmpty()) {
            actions.add(lastActionDTO);
        }
        return actions;
    }

    public ActionDTO initializeActionDTO(Option argument, Map<String,String> globalFilters) {
        ActionDTO actionDTO = new ActionDTO();
        actionDTO.setManipulateActionName(argument.getLongOpt());
        actionDTO.setActionArguments(argument.getValuesList());
        addFiltersToActionDTO(actionDTO, globalFilters);
        return actionDTO;
    }

    public ActionDTO addFiltersToActionDTO(ActionDTO actionDTO, Map<String,String> globalFilters) {
        for (String filterName : globalFilters.keySet()) {
            actionDTO.addActionFilter(filterName, globalFilters.get(filterName));
        }
        return actionDTO;
    }
}
