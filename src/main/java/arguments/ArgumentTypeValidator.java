package arguments;

import fileFilter.FilterType;
import manipulateActions.ManipulateActionsType;

public class ArgumentTypeValidator {

    public boolean isArgumentAction(String argumentName) {
        try {
            ManipulateActionsType manipulateActionType = ManipulateActionsType.valueOf(argumentName);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public boolean isArgumentFilter(String argumentName) {
        try {
            FilterType filterType = FilterType.valueOf(argumentName);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
