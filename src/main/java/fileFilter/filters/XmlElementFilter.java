package fileFilter.filters;

import fileFilter.SpecialCharacters;
import status.AbstractStatus;
import status.Status;

public class XmlElementFilter extends AbstractStatus {
    private boolean isBuildingElement;

    private String tagData;

    private final String startingTagToFilter;

    private final String closingTagToFilter;

    public XmlElementFilter(String tagName) {

        this.startingTagToFilter = SpecialCharacters.START_TAG.toChar() + tagName + SpecialCharacters.CLOSE_TAG.toChar();

        this.closingTagToFilter = SpecialCharacters.START_TAG.toChar() + SpecialCharacters.SLASH.toChar()
                                    + tagName + SpecialCharacters.CLOSE_TAG.toChar();

        this.tagData = "";

        this.isBuildingElement = false;
    }

    public void updateStatus(char data){
        if (isBuildingElement){
            tagData += data;
            if (data == SpecialCharacters.CLOSE_TAG.toChar()){
                handleEndOfTag();
            }
        }
        else if (data == SpecialCharacters.START_TAG.toChar()){
            isBuildingElement = true;
            tagData += data;
            status = Status.MORE_DATA_NEEDED;
        }
    }

    private void handleEndOfTag(){
        if (tagData.equals(closingTagToFilter)) {
            status = Status.DATA_CAN_NOT_MANIPULATE;
        }
        else if(tagData.equals(startingTagToFilter)){
            status = Status.DATA_CAN_MANIPULATE;
        }
        isBuildingElement = false;
        tagData = "";
    }
}
