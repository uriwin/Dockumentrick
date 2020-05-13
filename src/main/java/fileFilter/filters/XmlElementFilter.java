package fileFilter.filters;

import fileFilter.AbstractFilter;
import fileFilter.FilterType;

public class XmlElementFilter extends AbstractFilter {
    public String tagData;
    private String tagNameToFilter;
    private boolean isInMiddleOfTag;

    public XmlElementFilter(String tagName) {
        this.filterType = FilterType.ELEMENT;
        this.tagNameToFilter = tagName;
        this.tagData = "";
        this.isFilterActivated = false;
        this.isInMiddleOfTag = false;
    }

    public String getStartingTag() {
        return "<" + tagNameToFilter + ">";
    }

    public String getClosingTag() {
        return "</" + tagNameToFilter + ">";
    }

    @Override
    public void updateFilter(int fileByte) {
        if (isInMiddleOfTag){
            tagData += (char) fileByte;
            if (tagData.equals(getClosingTag()))
                isFilterActivated = false;
            if (tagData.equals(getStartingTag()))
                isFilterActivated = true;
            if ((char) fileByte == '>'){
                tagData = "";
                isInMiddleOfTag = false;
            }
        }
        else {
            if ((char) fileByte == '<'){
                tagData += (char) fileByte;
                isInMiddleOfTag = true;
            }
        }
    }
}
