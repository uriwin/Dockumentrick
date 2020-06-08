package commandLine.dockumentrick;

import commandLine.StringToEnumTypeConverter;
import manipulateActions.ManipulateAction;
import manipulateActions.actions.EscapeCharacterAppender;
import manipulateActions.ManipulateActionsType;
import manipulateActions.actions.StringEncloser;
import manipulateActions.actions.BaseConverter;
import manipulateActions.convertBase.BaseTypeConverter;
import fileFilter.*;
import org.apache.commons.cli.Option;
import status.IStatus;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ManipulatorActionParser {

    public List<ManipulateAction> getDataManipulationActions(Iterator<Option> iterator) {
        List<ManipulateAction> dataManipulatorActions = new ArrayList<ManipulateAction>();

        while (iterator.hasNext()) {
            Option option = iterator.next();
            String argumentName = option.getLongOpt();
            try {
                ManipulateAction manipulateAction = getDataManipulationAction(option, iterator);
                dataManipulatorActions.add(manipulateAction);
            } catch (IllegalArgumentException e) {
                ManipulateAction lastDataManipulatorAction = dataManipulatorActions.get(dataManipulatorActions.size() - 1);
                IStatus filter = getFilter(argumentName, option.getValue());
                lastDataManipulatorAction.addManipulatorFilter(filter);
            }
        }
        return dataManipulatorActions;
    }

    public ManipulateAction getDataManipulationAction(Option option, Iterator<Option> iterator) {
        StringToEnumTypeConverter stringToEnumConverter = new StringToEnumTypeConverter();
        ManipulateActionsType manipulateActionType = stringToEnumConverter.convertActionNameToActionType(option.getLongOpt());
        switch (manipulateActionType) {
            case BaseConverter:
                BaseTypeConverter baseTypeConverter = new BaseTypeConverter();
                int baseToChange = baseTypeConverter.convertStringToInt(option.getValue());
                return new ManipulateAction(new BaseConverter(baseToChange, 10));
            case EscapeCharacterAppender:
                return new ManipulateAction(new EscapeCharacterAppender(option.getValue().charAt(0)));
            case StringEncloser:
                String encloseByValue = getEncloseByValue(iterator.next());
                return new ManipulateAction(new StringEncloser(option.getValue(), encloseByValue));
            default:
                throw new IllegalArgumentException("No data manipulation action found");
        }
    }
    public String getEncloseByValue(Option option){
        if (!option.getLongOpt().equals("encloseBy"))
            throw new IllegalArgumentException("No encloseBy argument found after enclose argument");
        return option.getValue();
    }

    public IStatus getFilter(String argument, String argumentValue) {
        StringToEnumTypeConverter stringToEnumConverter = new StringToEnumTypeConverter();
        FilterFactory filterFactory = new FilterFactory();

        FilterType filterType = stringToEnumConverter.convertFilterNameToFilterType(argument);
        return filterFactory.getFilter(filterType, argumentValue);
    }
}
