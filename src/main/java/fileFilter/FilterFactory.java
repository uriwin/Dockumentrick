package fileFilter;

import fileFilter.filters.CsvColumnFilter;
import fileFilter.filters.LimitedLinesFilter;
import fileFilter.filters.XmlElementFilter;
import status.IStatus;

public class FilterFactory {
    public IStatus getFilter(FilterType filterType, String filterArgument) {
        switch (filterType) {
            case FILTER_ON_SPECIFIC_FIRST_LINES:
                return new LimitedLinesFilter(Integer.parseInt(filterArgument));
            case FILTER_ON_SPECIFIC_ELEMENT:
                return new XmlElementFilter(filterArgument);
            case FILTER_ON_SPECIFIC_COLUMN:
                return new CsvColumnFilter(Integer.parseInt(filterArgument));
            default:
                throw new IllegalArgumentException("No filter found");
        }
    }
}
