package fileFilter.filters;

import status.AbstractStatusProvider;
import status.Status;

public class LimitedLinesFilter extends AbstractStatusProvider {
    private final int linesToManipulate;

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
        if (linesRead < linesToManipulate){
            setStatus(Status.DATA_CAN_MANIPULATE);
        }
        else{
            setStatus(Status.DATA_CAN_NOT_MANIPULATE);
        }
    }
}
