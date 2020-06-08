package manipulateActions;

import status.Status;

public abstract class AbstractManipulateAction implements IManipulateAction{

    public String dataToManipulate;

    public Status status;

    @Override
    public Status getStatus() {
        return status;
    }


}
