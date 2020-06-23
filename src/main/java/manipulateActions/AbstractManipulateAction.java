package manipulateActions;

import status.Status;

public abstract class AbstractManipulateAction implements IManipulateAction{

    private String dataToManipulate;

    private Status status;

    @Override
    public Status getStatus() {
        return status;
    }

    protected void setStatus(Status status) {
        this.status = status;
    }

    protected String getDataToManipulate() {
        return dataToManipulate;
    }

    protected void setDataToManipulate(String dataToManipulate) {
        this.dataToManipulate = dataToManipulate;
    }
}
