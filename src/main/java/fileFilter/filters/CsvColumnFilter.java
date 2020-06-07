package fileFilter.filters;

import fileFilter.FilterState;
import fileFilter.IFilter;

public class CsvColumnFilter implements IFilter {
    private int columnPositionToFilter;
    private int currentColumnPosition;


    public CsvColumnFilter(int columnPositionToFilter) {
        this.currentColumnPosition = 0;
        this.columnPositionToFilter = columnPositionToFilter;
    }

//    @Override
//    public void updateFilter(int fileByte) {
//        if ((char) fileByte == ',')
//            currentColumnPosition += 1;
//        if ((char) fileByte =='\n')
//            currentColumnPosition = 0;
//    }

    public FilterState isDataCanBeManipulated(char data)
    {
        if (data == ',')
            currentColumnPosition += 1;
        if (data =='\n')
            currentColumnPosition = 0;
        if (currentColumnPosition == columnPositionToFilter){
            return FilterState.YES;
        }
        return FilterState.NO;
    }
}
