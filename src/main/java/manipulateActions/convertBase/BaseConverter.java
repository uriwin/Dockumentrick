package manipulateActions.convertBase;

import manipulateActions.AbstractManipulateAction;
import manipulateActions.ManipulateActionsType;

public class BaseConverter extends AbstractManipulateAction {
    private Integer baseToChange;
    private Integer currentBase;
    private ManipulateActionsType manipulateActionType;

    public BaseConverter(Integer baseToChange, Integer currentBase, ManipulateActionsType manipulateActionType) {
        super(manipulateActionType);
        this.baseToChange = baseToChange;
        this.currentBase = currentBase;
        this.manipulateActionType = ManipulateActionsType.BaseConverter;
    }

    public String manipulateData(String data) {
        return convertFromBaseToBase(data, this.currentBase, this.baseToChange);
    }

    public boolean isByteRequiredForAction(int data) {
        return Character.isDigit((char) data);
    }

    public String convertFromBaseToBase(String data, int fromBase, int toBase) {
        return Integer.toString(Integer.parseInt(data, fromBase), toBase);
    }
}


