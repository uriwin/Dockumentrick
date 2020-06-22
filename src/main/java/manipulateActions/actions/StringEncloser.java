package manipulateActions.actions;

import manipulateActions.AbstractManipulateAction;
import status.Status;

public class StringEncloser extends AbstractManipulateAction {

    private String stringRequired;

    private String enclose;

    public StringEncloser(String stringToEnclose, String encloseString) {

        this.stringRequired = stringToEnclose;

        this.enclose = encloseString;

        setDataToManipulate("");
    }

    public String manipulateDataAction(String data) {
        if (data.equals(stringRequired))
            return this.enclose.concat(data).concat(this.enclose);
        return data;
    }

    @Override
    public void updateStatus(char data) {
        if (getDataToManipulate().length() < stringRequired.length()) {
            if (stringRequired.charAt(getDataToManipulate().length()) == data) {
                setDataToManipulate(getDataToManipulate() + data);
                setStatus(Status.MORE_DATA_NEEDED);
            }
            else{
                setStatus(Status.DATA_CAN_NOT_MANIPULATE);
            }
        }
        else{
             setStatus(Status.DATA_CAN_MANIPULATE);
            setDataToManipulate("");
        }
    }
}
