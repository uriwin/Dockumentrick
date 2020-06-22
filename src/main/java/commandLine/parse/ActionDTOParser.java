package commandLine.parse;

import commandLine.extract.manipulateActionsExtractor.ActionDTO;
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

    public ActionDTOParser() {
    }

    public List<ManipulateAction> parseActionsDTO(List<ActionDTO> actionDTOList) {
        List<ManipulateAction> manipulateActions = new ArrayList<ManipulateAction>();
        for (ActionDTO actionDTO : actionDTOList) {
            ManipulateAction manipulateAction =
                    parseAction(actionDTO.getManipulateActionsType(), actionDTO.getActionArguments());
            manipulateAction = parseFilters(manipulateAction, actionDTO.getActionFilters());
            manipulateActions.add(manipulateAction);
        }
        return manipulateActions;
    }

    public ManipulateAction parseAction(ManipulateActionsType manipulateActionsType, List<String> actionArguments) {
        switch (manipulateActionsType) {
            case BASE_CONVERTER:
                return new ManipulateAction(new BaseConverter(actionArguments.get(0)));
            case ESCAPE_CHARACTER_APPENDER:
                return new ManipulateAction(new EscapeCharacterAppender(actionArguments.get(0)));
            case STRING_ENCLOSER:
                return new ManipulateAction(new StringEncloser(actionArguments.get(0), actionArguments.get(1)));
            default:
                throw new IllegalArgumentException("No action: " + manipulateActionsType.toString() + " exists");
        }
    }

    public ManipulateAction parseFilters(ManipulateAction manipulateAction, Map<String, String> filters) {
        FilterFactory filterFactory = new FilterFactory();
        for (String filterName : filters.keySet()) {
            FilterType filterType = FilterType.valueOf(filterName);
            manipulateAction.addManipulatorFilter(filterFactory.getFilter(filterType, filters.get(filterName)));
        }
        return manipulateAction;
    }
}
