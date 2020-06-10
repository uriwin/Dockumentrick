package commandLine.convertStringToEnum;

import fileFilter.FilterType;
import fileFormat.FileFormatFactory;

public class StringToFilterEnumConverter {
    public FilterType convertFilterNameToFilterType(String filterName) {
        switch (filterName) {
            case "limitedLines":
                return FilterType.FILTER_ON_SPECIFIC_FIRST_LINES;
            case "Element":
                return FilterType.FILTER_ON_SPECIFIC_ELEMENT;
            case "Column":
                return FilterType.FILTER_ON_SPECIFIC_COLUMN;
            case "inputFilePath":
                return FilterType.FILTER_BASED_ON_FILE_TYPE;
            default:
                throw new IllegalArgumentException("Filter: " + filterName + " does not exists");
        }
    }
}
