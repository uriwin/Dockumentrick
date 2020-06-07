package manipulateActions.convertBase;

import manipulateActions.IManipulateAction;

public class BaseConverter implements IManipulateAction {
    private Integer baseToChange;
    private Integer currentBase;

    public BaseConverter(Integer baseToChange, Integer currentBase) {
        this.baseToChange = baseToChange;
        this.currentBase = currentBase;
    }

    public String manipulateDataAction(String data) {
        return convertFromBaseToBase(data, this.currentBase, this.baseToChange);
    }

    public boolean isByteRequiredForAction(int data) {
        return Character.isDigit((char) data);
    }

    public String convertFromBaseToBase(String data, int fromBase, int toBase) {
        return Integer.toString(Integer.parseInt(data, fromBase), toBase);
    }
}


