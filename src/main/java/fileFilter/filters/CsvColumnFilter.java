package fileFilter.filters;

import status.AbstractStatus;
import status.Status;

public class CsvColumnFilter extends AbstractStatus {
    private int columnPositionToFilter;

    private int currentColumnPosition;

    public CsvColumnFilter(int columnPositionToFilter) {
        this.currentColumnPosition = 0;

        this.columnPositionToFilter = columnPositionToFilter;

        status = Status.DATA_CAN_NOT_MANIPULATE;
    }

    public void updateStatus(char data) {
        if (data == ',')
            currentColumnPosition += 1;
        if (data == '\n')
            currentColumnPosition = 0;
        if (currentColumnPosition == columnPositionToFilter) {
            status = Status.DATA_CAN_MANIPULATE;
        }
        else{
            status = Status.DATA_CAN_NOT_MANIPULATE;
        }
    }
}
