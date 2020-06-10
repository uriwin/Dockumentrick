package commandLine.clientArguments;

import commandLine.convertStringToEnum.StringToActionEnumConverter;
import commandLine.convertStringToEnum.StringToFilterEnumConverter;
import commandLine.convertStringToEnum.StringToInputSourceEnumConverter;
import commandLine.convertStringToEnum.StringToOutputSourceEnumConverter;
import commandLine.extracte.sourceExtractor.SourceType;
import fileFilter.FilterType;
import manipulateActions.ManipulateActionsType;

public class ArgumentTypeValidator {

    public boolean isArgumentAction(String argumentName) {
        StringToActionEnumConverter stringToActionEnumConverter = new StringToActionEnumConverter();
        try {
            ManipulateActionsType manipulateActionType = stringToActionEnumConverter.convertActionNameToActionType(argumentName);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isArgumentFilter(String argumentName) {
        StringToFilterEnumConverter stringToFilterEnumConverter = new StringToFilterEnumConverter();
        try {
            FilterType filterType = stringToFilterEnumConverter.convertFilterNameToFilterType(argumentName);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public boolean isArgumentConnectedToInputFileSource(String argumentName){
        return argumentName.equals("inputFilePath");
    }

    public boolean isArgumentInputSource(String argumentName){
        StringToInputSourceEnumConverter stringToInputSourceEnumConverter = new StringToInputSourceEnumConverter();
        try {
            SourceType sourceType = stringToInputSourceEnumConverter.getInputSourceType(argumentName);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isArgumentOutputSource(String argumentName){
        StringToOutputSourceEnumConverter stringToOutputSourceEnumConverter = new StringToOutputSourceEnumConverter();
        try {
            SourceType sourceType = stringToOutputSourceEnumConverter.getOutputSourceType(argumentName);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
