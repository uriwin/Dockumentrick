package manipulateActions;

import status.Status;

public abstract class AbstractManipulateAction implements IManipulateAction{

    private String dataToManipulate;

    private Status status;

    @Override
    public Status getStatus() {
        return status;
    }

    @Override
    public void setStatus(Status status) {
        this.status = status;
    }

    public String getDataToManipulate() {
        return dataToManipulate;
    }

    public void setDataToManipulate(String dataToManipulate) {
        this.dataToManipulate = dataToManipulate;
    }
}
