package commandLine.convertStringToEnum;

import manipulateActions.ManipulateActionsType;

public class StringToActionEnumConverter {
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
}
