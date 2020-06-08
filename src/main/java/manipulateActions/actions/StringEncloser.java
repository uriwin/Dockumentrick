package manipulateActions.actions;

import manipulateActions.AbstractManipulateAction;
import status.Status;

public class StringEncloser extends AbstractManipulateAction {

    private String stringRequired;

    private String enclose;


    public StringEncloser(String stringToEnclose, String encloseString) {

        this.stringRequired = stringToEnclose;

        this.enclose = encloseString;

        dataToManipulate = "";
    }

    public String manipulateDataAction(String data) {
        if (data.equals(stringRequired))
            return this.enclose.concat(data).concat(this.enclose);
        return data;
    }

    @Override
    public void updateStatus(char data) {
        if (dataToManipulate.length() < stringRequired.length()) {
            if (stringRequired.charAt(dataToManipulate.length()) == data) {
                dataToManipulate += data;
                status = Status.MORE_DATA_NEEDED;
            }
            else{
                status = Status.DATA_CAN_NOT_MANIPULATE;
            }
        }
        else{
            status = Status.DATA_CAN_MANIPULATE;
            dataToManipulate = "";
        }
    }
}
