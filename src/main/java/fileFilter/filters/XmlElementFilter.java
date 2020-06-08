package fileFilter.filters;

import status.AbstractStatus;
import status.Status;

public class XmlElementFilter extends AbstractStatus {
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

    public void updateStatus(char data){
        if (isInMiddleOfTag){
            tagData += data;
            if (isEqualToEndOfTag(data)){
                handleEndOfTag();
            }
        }
        else if (isEqualToStartOfTag(data)){
            isInMiddleOfTag = true;
            tagData += data;
            status = Status.MORE_DATA_NEEDED;
        }
    }
    public boolean isEqualToEndOfTag(char data){
        return data == '>';
    }

    public boolean isEqualToStartOfTag(char data){
        return (data == '<');
    }

    public void handleEndOfTag(){
        if (tagData.equals(closingTagToFilter)) {
            status = Status.DATA_CAN_NOT_MANIPULATE;
        }
        else if(tagData.equals(startingTagToFilter)){
            status = Status.DATA_CAN_MANIPULATE;
        }
        isInMiddleOfTag = false;
        tagData = "";
    }
}
