package commandLine.convertStringToEnum;

import fileFilter.FilterType;

public class StringToFilterEnumConverter {
    public FilterType convertFilterNameToFilterType(String filerName) {
        switch (filerName) {
            case "limitedLines":
                return FilterType.FILTER_ON_SPECIFIC_FIRST_LINES;
            case "Element":
                return FilterType.FILTER_ON_SPECIFIC_ELEMENT;
            case "Column":
                return FilterType.FILTER_ON_SPECIFIC_COLUMN;
            default:
                throw new IllegalArgumentException("Filter: " + filerName + " does not exists");
        }
    }
}
