package status;

public abstract class AbstractStatus implements IStatus {
    public Status status;

    @Override
    public Status getStatus() {
        return status;
    }
}
