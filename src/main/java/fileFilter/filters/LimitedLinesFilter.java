package fileFilter.filters;

import fileFilter.FilterState;
import fileFilter.IFilter;

public class LimitedLinesFilter implements IFilter {
    private int linesToManipulate;
    private int linesRead;

    public LimitedLinesFilter(int linesToManipulate) {
        this.linesToManipulate = linesToManipulate;
        this.linesRead = 0;
    }

    public FilterState isDataCanBeManipulated(char data)
    {
        if (data == '\n') {
            linesRead += 1;
        }
        if (linesRead >= linesToManipulate){
            return FilterState.YES;
        }
        return FilterState.NO;
    }

//    public void updateFilter(int fileByte) {
//        if ((char) fileByte == '\n') {
//            linesRead += 1;
//        }
//    }
}
