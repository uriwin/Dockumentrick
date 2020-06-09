package manipulateActions.actions;

import manipulateActions.AbstractManipulateAction;
import status.Status;

public class BaseConverter extends AbstractManipulateAction {
    private Integer baseToChange;

    private Integer currentBase;

    private String dataToManipulate;

    public BaseConverter(Integer baseToChange, Integer currentBase) {
        this.baseToChange = baseToChange;

        this.currentBase = currentBase;

        this.dataToManipulate = "";
    }

    public String manipulateDataAction(String data) {
        return convertFromBaseToBase(data, this.currentBase, this.baseToChange);
    }

    public String convertFromBaseToBase(String data, int fromBase, int toBase) {
        return Integer.toString(Integer.parseInt(data, fromBase), toBase);
    }

    @Override
    public void updateStatus(char data) {
        if (Character.isDigit(data)) {
            dataToManipulate += data;
            status = Status.MORE_DATA_NEEDED;
        }
        else if (dataToManipulate.length() > 0){
            status = Status.DATA_CAN_MANIPULATE;
            dataToManipulate = "";
        }
        else{
            status = Status.DATA_CAN_NOT_MANIPULATE;
        }
    }
}


