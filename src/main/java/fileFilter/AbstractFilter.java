package fileFilter;

public abstract class AbstractFilter implements IFilter {
    public boolean isFilterActivated;
    public FilterType filterType;

    public boolean isFilterActivated() {
        return isFilterActivated;
    }

    public FilterType getFilterType() {
        return this.filterType;
    }
}
