package fileFilter;

public interface IFilter {
    public boolean isFilterActivated();

    public void updateFilter(int fileByte);

    public FilterType getFilterType();
}

