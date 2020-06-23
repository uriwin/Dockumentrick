package manipulateActions;

import status.IStatusProvider;

public interface IManipulateAction extends IStatusProvider {
    public String manipulateDataAction(String data);
}
