package status;

public abstract class AbstractStatus implements IStatus {
    private Status status;

    @Override
    public Status getStatus() {
        return status;
    }

    @Override
    public void setStatus(Status status) {
        this.status = status;
    }
}
