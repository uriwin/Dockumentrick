package manipulateActions;

import status.IStatusProvider;
import status.Status;

import java.util.ArrayList;
import java.util.List;

public class ManipulateAction implements IManipulateAction {

    private IManipulateAction dataManipulator;

    private List<IStatusProvider> manipulatorFilters;

    public ManipulateAction(IManipulateAction dataManipulator) {

        this.dataManipulator = dataManipulator;

        this.manipulatorFilters = new ArrayList<IStatusProvider>();
    }

    @Override
    public String manipulateDataAction(String data) {
        return dataManipulator.manipulateDataAction(data);
    }

    @Override
    public void updateStatus(char data) {
        dataManipulator.updateStatus(data);
    }

    @Override
    public Status getStatus() {
        return dataManipulator.getStatus();
    }

    public void addManipulatorFilter(IStatusProvider manipulatorFilter) {
        this.manipulatorFilters.add(manipulatorFilter);
    }

    public void updateFiltersStatus(char data) {
        for (IStatusProvider manipulatorFilter : manipulatorFilters) {
            manipulatorFilter.updateStatus(data);
        }
    }

    public boolean isAtLeastOneFiltersStatusBad() {
        for (IStatusProvider manipulatorFilter : manipulatorFilters) {
            if (manipulatorFilter.getStatus() == Status.DATA_CAN_NOT_MANIPULATE) {
                return true;
            }
        }
        return false;
    }

    public boolean isAllFiltersStatusGood() {
        for (IStatusProvider manipulatorFilter : manipulatorFilters) {
            if (manipulatorFilter.getStatus() != Status.DATA_CAN_MANIPULATE) {
                return false;
            }
        }
        return true;
    }
}
