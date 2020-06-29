package arguments.extract.manipulateActionsExtractor;

import manipulateActions.ManipulateActionsType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActionDTO{
    private ManipulateActionsType manipulateActionsType;

    private List<String> actionArguments;

    private Map<String,String> actionFilters;

    private boolean isEmpty;

    public ActionDTO(){

        this.actionArguments = new ArrayList<String>();

        this.actionFilters = new HashMap<String, String>();

        this.isEmpty = true;
    }

    public void setActionArguments(List<String> actionArguments) {
        this.actionArguments = actionArguments;
    }

    public void addActionFilter(String filterName, String filterValue) {
        this.actionFilters.put(filterName, filterValue);
    }

    public void setManipulateActionsType(ManipulateActionsType manipulateActionsType) {
        this.manipulateActionsType = manipulateActionsType;
    }

    public ManipulateActionsType getManipulateActionsType() {
        return manipulateActionsType;
    }

    public List<String> getActionArguments() {
        return actionArguments;
    }

    public Map<String, String> getActionFilters() {
        return actionFilters;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public void setNotEmpty() {
        isEmpty = false;
    }
}
