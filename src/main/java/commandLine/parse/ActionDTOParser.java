package commandLine.parse;

import commandLine.convertStringToEnum.StringToActionEnumConverter;
import commandLine.convertStringToEnum.StringToFilterEnumConverter;
import commandLine.extracte.manipulateActionsExtractor.ActionDTO;
import fileFilter.FilterFactory;
import fileFilter.FilterType;
import manipulateActions.ManipulateAction;
import manipulateActions.ManipulateActionsType;
import manipulateActions.actions.BaseConverter;
import manipulateActions.actions.EscapeCharacterAppender;
import manipulateActions.actions.StringEncloser;
import manipulateActions.convertBase.BaseTypeConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ActionDTOParser {

    public ActionDTOParser(){}

    public List<ManipulateAction> parseActionsDTO(List<ActionDTO> actionDTOList){
        List<ManipulateAction> manipulateActions = new ArrayList<ManipulateAction>();
        for (ActionDTO actionDTO: actionDTOList){
            ManipulateAction manipulateAction = parseAction(actionDTO.getManipulateActionName(), actionDTO.getActionArguments());
            manipulateAction = parseFilters(manipulateAction, actionDTO.getActionFilters());
            manipulateActions.add(manipulateAction);
        }
        return manipulateActions;
    }

    public ManipulateAction parseAction(String actionName, List<String> actionArguments)
    {
        ManipulateActionsType manipulateActionsType = getManipulationActionType(actionName);

        switch (manipulateActionsType) {
            case BaseConverter:
                BaseTypeConverter baseTypeConverter = new BaseTypeConverter();
                Integer baseToChange = baseTypeConverter.convertStringToInt(actionArguments.get(0));
                return new ManipulateAction(new BaseConverter(baseToChange, 10));
            case EscapeCharacterAppender:
                String character = actionArguments.get(0);
                return new ManipulateAction(new EscapeCharacterAppender(character.charAt(0)));
            case StringEncloser:
                String stringToEnclose = actionArguments.get(0);
                String encloseByValue = actionArguments.get(1);
                return new ManipulateAction(new StringEncloser(stringToEnclose, encloseByValue));
            default:
                throw new IllegalArgumentException("No action: " + actionName + " exists");
        }
    }

    public ManipulateAction parseFilters(ManipulateAction manipulateAction, Map<String,String> filters) {
        StringToFilterEnumConverter stringToFilterEnumConverter = new StringToFilterEnumConverter();
        FilterFactory filterFactory = new FilterFactory();
        for(String filterName : filters.keySet()){
            FilterType filterType = stringToFilterEnumConverter.convertFilterNameToFilterType(filterName);
            manipulateAction.addManipulatorFilter(filterFactory.getFilter(filterType, filters.get(filterName)));
        }
        return manipulateAction;
    }

    public ManipulateActionsType getManipulationActionType(String actionName){
        StringToActionEnumConverter stringToActionEnumConverter = new StringToActionEnumConverter();
        return stringToActionEnumConverter.convertActionNameToActionType(actionName);
    }
}
