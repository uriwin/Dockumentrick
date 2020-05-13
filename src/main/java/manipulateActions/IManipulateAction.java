package manipulateActions;

public interface IManipulateAction {
    public String manipulateData(String fileChunkData);

    public ManipulateActionsType getManipulateActionType();

    public boolean isByteRequiredForAction(int data);
}
