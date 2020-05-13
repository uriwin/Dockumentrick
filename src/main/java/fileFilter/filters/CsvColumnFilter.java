package fileFilter.filters;

import fileFilter.AbstractFilter;
import fileFilter.FilterType;

public class CsvColumnFilter extends AbstractFilter {
    private int columnPositionToFilter;
    private int currentColumnPosition;


    public CsvColumnFilter(int columnPositionToFilter) {
        this.filterType = FilterType.COLUMN;
        this.isFilterActivated = false;
        this.currentColumnPosition = 1;
        this.columnPositionToFilter = columnPositionToFilter;
    }

    @Override
    public void updateFilter(int fileByte) {
        if (fileByte == ',')
            currentColumnPosition += 1;
        if (fileByte =='\n')
            currentColumnPosition = 1;

        isFilterActivated = currentColumnPosition == columnPositionToFilter;
    }

}
