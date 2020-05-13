package cli;

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
                throw new IllegalArgumentException("No data manipulation action exists");
        }
    }

    public FilterType convertFilterNameToFilterType(String filerName) {
        switch (filerName) {
            case "limitedLines":
                return FilterType.LIMITED_LINES;
            case "Element":
                return FilterType.ELEMENT;
            case "Column":
                return FilterType.COLUMN;
            default:
                throw new IllegalArgumentException("Filter does not exists");
        }
    }
}
