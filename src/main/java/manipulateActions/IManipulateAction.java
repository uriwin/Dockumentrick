package manipulateActions;

import java.util.List;

public interface IManipulateAction {
    public String manipulateDataAction(String data);

    public boolean isByteRequiredForAction(int data);
}
