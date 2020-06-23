package status;

public abstract class AbstractStatusProvider implements IStatusProvider {
    private Status status;

    @Override
    public Status getStatus() {
        return status;
    }

    protected void setStatus(Status status) {
        this.status = status;
    }
}
