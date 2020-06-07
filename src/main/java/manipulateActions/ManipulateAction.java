package manipulateActions;

import fileFilter.IFilter;

import java.util.ArrayList;
import java.util.List;

public class ManipulateAction implements IManipulateAction {
    private IManipulateAction dataManipulator;
    private List<IFilter> manipulatorFilters;

    public ManipulateAction(IManipulateAction dataManipulator) {
        this.dataManipulator = dataManipulator;
        this.manipulatorFilters = new ArrayList<IFilter>();
    }

    @Override
    public String manipulateDataAction(String data) {
        return dataManipulator.manipulateDataAction(data);
    }

    @Override
    public boolean isByteRequiredForAction(int data) {
        return dataManipulator.isByteRequiredForAction(data);
    }

    public void addManipulatorFilter(IFilter manipulatorFilter) {
        this.manipulatorFilters.add(manipulatorFilter);
    }

    public boolean areAllFiltersActivated() {
        for (IFilter manipulatorFilter : manipulatorFilters) {
            if (!manipulatorFilter.isDataCanBeManipulated())
                return false;
        }
        return true;
    }

    public boolean doesActionHaveFilters() {
        return manipulatorFilters.size() > 0;
    }

    public List<IFilter> getManipulatorFilters() {
        return manipulatorFilters;
    }


    public void updateFilters(int fileByte) {
        if (doesActionHaveFilters()) {
            for (IFilter manipulatorFilter : getManipulatorFilters()) {
                manipulatorFilter.updateFilter(fileByte);
            }
        }
    }
}
