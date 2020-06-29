package arguments;

import arguments.extract.destinationExtractor.DestinationType;
import arguments.extract.sourceExtractor.SourceType;
import fileFilter.FilterType;
import manipulateActions.ManipulateActionsType;

public class ArgumentTypeValidator {

    public boolean isArgumentRelatedToAction(String argumentName) {
        try {
            ManipulateActionsType manipulateActionType = ManipulateActionsType.valueOf(argumentName);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public boolean isArgumentRelatedToFilter(String argumentName) {
        try {
            FilterType filterType = FilterType.valueOf(argumentName);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public boolean isArgumentRelatedToDestination(String argumentName) {
        try {
            DestinationType destinationType = DestinationType.valueOf(argumentName);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }


    public boolean isArgumentRelatedToSource(String argumentName) {
        try {
            SourceType sourceType = SourceType.valueOf(argumentName);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
