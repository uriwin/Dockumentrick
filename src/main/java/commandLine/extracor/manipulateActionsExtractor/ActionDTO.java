package commandLine.extracor.manipulateActionsExtractor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActionDTO{
    private String manipulateAction;

    private List<String> actionArguments;

    private Map<String,String> actionFilters;

    public ActionDTO(){
        this.manipulateAction = "";

        this.actionArguments = new ArrayList<String>();

        this.actionFilters = new HashMap<String, String>();
    }

    public void setActionArguments(List<String> actionArguments) {
        this.actionArguments = actionArguments;
    }

    public void addActionFilter(String filterName, String filterValue) {
        this.actionFilters.put(filterName, filterValue);
    }

    public void setManipulateAction(String manipulateAction) {
        this.manipulateAction = manipulateAction;
    }

    public String getManipulateAction() {
        return manipulateAction;
    }

    public List<String> getActionArguments() {
        return actionArguments;
    }

    public Map<String, String> getActionFilters() {
        return actionFilters;
    }

    public boolean isEmpty(){
        return manipulateAction.equals("");
    }
}
