package manipulateActions;

public abstract class AbstractManipulateAction implements IManipulateAction{
    private ManipulateActionsType manipulateActionType;

    public AbstractManipulateAction (ManipulateActionsType manipulateActionType){
        this.manipulateActionType = manipulateActionType;
    }

    @Override
    public ManipulateActionsType getManipulateActionType() {
        return manipulateActionType;
    }
}
