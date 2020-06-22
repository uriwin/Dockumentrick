package manipulateActions.actions;

import manipulateActions.AbstractManipulateAction;
import manipulateActions.convertBase.BaseTypeConverter;
import status.Status;

public class BaseConverter extends AbstractManipulateAction {
    private Integer baseToChange;

    private Integer currentBase;

    private final Integer DEFAULT_BASE = 10;

    public BaseConverter(String baseToChange){
        BaseTypeConverter baseTypeConverter = new BaseTypeConverter();

        this.baseToChange = baseTypeConverter.convertStringToInt(baseToChange);

        this.currentBase = DEFAULT_BASE;

        setDataToManipulate("");
    }

    public String manipulateDataAction(String data) {
        return convertFromBaseToBase(data, this.currentBase, this.baseToChange);
    }

    private String convertFromBaseToBase(String data, int fromBase, int toBase) {
        return Integer.toString(Integer.parseInt(data, fromBase), toBase);
    }

    @Override
    public void updateStatus(char data) {
        if (Character.isDigit(data)) {
            setDataToManipulate(getDataToManipulate() + data);
            setStatus(Status.MORE_DATA_NEEDED);
        }
        else if (getDataToManipulate().length() > 0){
            setStatus(Status.DATA_CAN_MANIPULATE);
            setDataToManipulate("");
        }
        else{
            setStatus(Status.DATA_CAN_NOT_MANIPULATE);
        }
    }
}


