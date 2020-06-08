package commandLine;

import fileFilter.FilterType;
import manipulateActions.ManipulateActionsType;

public class StringToEnumTypeConverter {
    public ManipulateActionsType convertActionNameToActionType(String actionType) {
        switch (actionType) {
            case "changeNumBase":
                return ManipulateActionsType.BaseConverter;
            case "escapeChar":
                return ManipulateActionsType.EscapeCharacterAppender;
            case "enclose":
                return ManipulateActionsType.StringEncloser;
            default:
                throw new IllegalArgumentException("action: " + actionType + " does not exists");
        }
    }

    public FilterType convertFilterNameToFilterType(String filerName) {
        switch (filerName) {
            case "limitedLines":
                return FilterType.FILTER_ON_SPECIFIC_FIRST_LINES;
            case "Element":
                return FilterType.FILTER_ON_SPECIFIC_ELEMENT;
            case "Column":
                return FilterType.FILTER_ON_SPECIFIC_COLUMN;
            default:
                throw new IllegalArgumentException("Filter: " + filerName + " does not exists");
        }
    }
}
