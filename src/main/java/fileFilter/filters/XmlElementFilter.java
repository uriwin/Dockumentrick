package fileFilter.filters;

import fileFilter.FilterState;
import fileFilter.IFilter;

public class XmlElementFilter implements IFilter {
    public String tagData;

    private boolean isInMiddleOfTag;

    private String startingTagToFilter;

    private String closingTagToFilter;

    private Boolean isBetweenTagToFilter;

    public XmlElementFilter(String tagName) {

        this.startingTagToFilter = "<" + tagName + ">";

        this.closingTagToFilter = "</" + tagName + ">";

        this.tagData = "";

        this.isInMiddleOfTag = false;

        this.isBetweenTagToFilter = false;
    }

    public FilterState isDataCanBeManipulated(char data){
        if (isInMiddleOfTag){
            return handleMiddleOfTag(data);
        }
        else {
            if (isTagStarted(data)){
                tagData += data;
                isInMiddleOfTag = true;
                return FilterState.MORE_DATA_NEEDED;
            }
            return FilterState.NO;
        }
    }
    public boolean isTagCompleted(char data){
        return data == '>';
    }

    public boolean isTagStarted(char data){
        return (data == '<');
    }

    public FilterState handleMiddleOfTag(char data){
        tagData += data;
        if (isTagCompleted(data)){
            isInMiddleOfTag = false;
            if (tagData.equals(closingTagToFilter)){
                tagData = "";
                return FilterState.YES;
            }
            tagData = "";
            return FilterState.NO;
        }
        return FilterState.MORE_DATA_NEEDED;
    }
}
