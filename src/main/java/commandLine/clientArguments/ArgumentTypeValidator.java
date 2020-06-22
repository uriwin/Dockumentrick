package commandLine.clientArguments;

import fileFilter.FilterType;
import manipulateActions.ManipulateActionsType;

public class ArgumentTypeValidator {

    public boolean isArgumentAction(String argumentName) {
        try {
            ManipulateActionsType manipulateActionType = ManipulateActionsType.valueOf(argumentName);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isArgumentFilter(String argumentName) {
        try {
            FilterType filterType = FilterType.valueOf(argumentName);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public boolean isArgumentConnectedToInputFileSource(String argumentName){
        return argumentName.equals("inputFilePath");
    }
}
