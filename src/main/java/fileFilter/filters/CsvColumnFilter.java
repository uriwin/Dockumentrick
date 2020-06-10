package fileFilter.filters;

import status.AbstractStatus;
import status.Status;

public class CsvColumnFilter extends AbstractStatus {
    private int columnPositionToFilter;

    private int currentColumnPosition;

    private boolean isInQuotes;

    public CsvColumnFilter(int columnPositionToFilter) {
        this.currentColumnPosition = 0;

        this.columnPositionToFilter = columnPositionToFilter;

        this.isInQuotes = false;

        status = Status.DATA_CAN_NOT_MANIPULATE;
    }

    public void updateStatus(char data) {
        updateIsInQuotes(data);
        if (data == ',' && !isInQuotes)
            currentColumnPosition += 1;
        if (data == '\n' && !isInQuotes)
            currentColumnPosition = 0;
        if (currentColumnPosition == columnPositionToFilter) {
            status = Status.DATA_CAN_MANIPULATE;
        }
        else{
            status = Status.DATA_CAN_NOT_MANIPULATE;
        }
    }

    public void updateIsInQuotes(char data){
        if (data == '\"' || data == '\'')
        {
            isInQuotes = !isInQuotes;
        }
    }
}
