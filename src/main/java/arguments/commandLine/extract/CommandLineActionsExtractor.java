package arguments.commandLine.extract;

import arguments.ArgumentTypeValidator;
import arguments.extract.manipulateActionsExtractor.ActionDTO;
import arguments.extract.manipulateActionsExtractor.IActionsDTOExtractor;
import manipulateActions.ManipulateActionsType;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;

import java.util.ArrayList;
import java.util.List;

public class CommandLineActionsExtractor implements IActionsDTOExtractor {
    private CommandLine commandLine;

    private Option[] arguments;

    public CommandLineActionsExtractor(CommandLine commandLine) {
        this.commandLine = commandLine;

        this.arguments = commandLine.getOptions();
    }

    @Override
    public List<ActionDTO> getActionsDTO() {
        List<ActionDTO> actions = new ArrayList<ActionDTO>();
        ArgumentTypeValidator argumentTypeValidator = new ArgumentTypeValidator();
        ActionDTO lastActionDTO = new ActionDTO();

        for (Option argument : arguments) {
            String argumentName = argument.getLongOpt();
            if (argumentTypeValidator.isArgumentRelatedToAction(argumentName)) {
                if (!lastActionDTO.isEmpty()) {
                    actions.add(lastActionDTO);
                }
                lastActionDTO = initializeActionDTO(argument);
            }
            else if (argumentTypeValidator.isArgumentRelatedToFilter(argumentName)) {
                lastActionDTO.addActionFilter(argumentName, argument.getValue());
            }
        }

        if (!lastActionDTO.isEmpty()) {
            actions.add(lastActionDTO);
        }
        return actions;
    }

    private ActionDTO initializeActionDTO(Option argument) {
        ActionDTO actionDTO = new ActionDTO();
        actionDTO.setManipulateActionsType(ManipulateActionsType.valueOf(argument.getLongOpt()));
        actionDTO.setActionArguments(argument.getValuesList());
        actionDTO.setNotEmpty();
        return actionDTO;
    }
}
