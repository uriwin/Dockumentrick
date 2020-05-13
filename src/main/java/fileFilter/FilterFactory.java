package fileFilter;

import fileFilter.filters.CsvColumnFilter;
import fileFilter.filters.LimitedLinesFilter;
import fileFilter.filters.XmlElementFilter;

public class FilterFactory {
    public AbstractFilter getFilter(FilterType filterType, String filterArgument) {
        switch (filterType) {
            case LIMITED_LINES:
                return new LimitedLinesFilter(Integer.parseInt(filterArgument));
            case ELEMENT:
                return new XmlElementFilter(filterArgument);
            case COLUMN:
                return new CsvColumnFilter(Integer.parseInt(filterArgument));
            default:
                throw new IllegalArgumentException("No filter found");
        }
    }
}
