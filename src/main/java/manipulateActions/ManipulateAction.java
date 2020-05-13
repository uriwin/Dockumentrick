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
    public String manipulateData(String fileChunkData) {
        return dataManipulator.manipulateData(fileChunkData);
    }

    @Override
    public boolean isByteRequiredForAction(int data) {
        return dataManipulator.isByteRequiredForAction(data);
    }

    @Override
    public ManipulateActionsType getManipulateActionType() {
        return dataManipulator.getManipulateActionType();
    }

    public void addManipulatorFilter(IFilter manipulatorFilter) {
        this.manipulatorFilters.add(manipulatorFilter);
    }

    public boolean isAllFiltersActivated() {
        if (!isActionHaveFilters())
            return true;
        for (IFilter manipulatorFilter : manipulatorFilters) {
            if (!manipulatorFilter.isFilterActivated())
                return false;
        }
        return true;
    }

    public boolean isActionHaveFilters() {
        return manipulatorFilters.size() > 0;
    }

    public List<IFilter> getManipulatorFilters() {
        return manipulatorFilters;
    }


    public void updateFilters(int fileByte) {
        if (isActionHaveFilters()) {
            for (IFilter manipulatorFilter : getManipulatorFilters()) {
                manipulatorFilter.updateFilter(fileByte);
            }
        }
    }
}
