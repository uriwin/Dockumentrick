package fileFilter.filters;

import fileFilter.AbstractFilter;
import fileFilter.FilterType;

public class LimitedLinesFilter extends AbstractFilter {
    private int linesToManipulate;
    private int linesRead;

    public LimitedLinesFilter(int linesToManipulate) {
        this.isFilterActivated = true;
        this.filterType = FilterType.LIMITED_LINES;
        this.linesToManipulate = linesToManipulate;
        this.linesRead = 0;
    }

    public void updateFilter(int fileByte) {
        if ((char) fileByte == '\n') {
            linesRead += 1;
            if (linesRead >= linesToManipulate)
                isFilterActivated = false;
        }
    }
}
