package manipulateActions;

import status.IStatus;
import status.Status;

import java.util.ArrayList;
import java.util.List;

public class ManipulateAction implements IManipulateAction {

    private IManipulateAction dataManipulator;

    private List<IStatus> manipulatorFilters;

    public ManipulateAction(IManipulateAction dataManipulator) {

        this.dataManipulator = dataManipulator;

        this.manipulatorFilters = new ArrayList<IStatus>();
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

    public void addManipulatorFilter(IStatus manipulatorFilter) {
        this.manipulatorFilters.add(manipulatorFilter);
    }

    public void updateFiltersStatus(char data) {
        for (IStatus manipulatorFilter : manipulatorFilters) {
            manipulatorFilter.updateStatus(data);
        }
    }

    public boolean isFiltersStatusBad() {
        for (IStatus manipulatorFilter : manipulatorFilters) {
            if (manipulatorFilter.getStatus() == Status.DATA_CAN_NOT_MANIPULATE) {
                return true;
            }
        }
        return false;
    }

    public boolean isFiltersStatusGood() {
        for (IStatus manipulatorFilter : manipulatorFilters) {
            if (manipulatorFilter.getStatus() != Status.DATA_CAN_MANIPULATE) {
                return false;
            }
        }
        return true;
    }
}
