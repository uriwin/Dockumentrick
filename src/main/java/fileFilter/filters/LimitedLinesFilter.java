package fileFilter.filters;

import status.AbstractStatus;
import status.Status;

public class LimitedLinesFilter extends AbstractStatus {
    private int linesToManipulate;

    private int linesRead;

    public LimitedLinesFilter(int linesToManipulate) {
        this.linesToManipulate = linesToManipulate;

        this.linesRead = 0;
    }

    public void updateStatus(char data)
    {
        if (data == '\n') {
            linesRead += 1;
        }
        if (linesRead <= linesToManipulate){
            status = Status.DATA_CAN_MANIPULATE;
        }
        else{
            status = Status.DATA_CAN_NOT_MANIPULATE;
        }
    }
}
