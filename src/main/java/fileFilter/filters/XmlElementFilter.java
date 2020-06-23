package fileFilter.filters;

import fileFilter.SpecialCharacters;
import status.AbstractStatusProvider;
import status.Status;

public class XmlElementFilter extends AbstractStatusProvider {
    private boolean isBuildingElement;

    private String elementData;

    private final String startingElementToFilter;

    private final String closingElementToFilter;

    private boolean isStartingElementCurrentlyAppeared;

    public XmlElementFilter(String tagName) {

        this.startingElementToFilter = SpecialCharacters.START_TAG.toChar() + tagName + SpecialCharacters.CLOSE_TAG.toChar();

        this.closingElementToFilter = SpecialCharacters.START_TAG.toChar() + SpecialCharacters.SLASH.toChar()
                                    + tagName + SpecialCharacters.CLOSE_TAG.toChar();

        this.elementData = "";

        this.isBuildingElement = isStartingElementCurrentlyAppeared = false;
    }

    public void updateStatus(char data){
        if (isStartingElementCurrentlyAppeared){
            setStatus(Status.DATA_CAN_MANIPULATE);
            isStartingElementCurrentlyAppeared = false;
        }
        if (isBuildingElement){
            elementData += data;
            if (data == SpecialCharacters.CLOSE_TAG.toChar()){
                handleEndOfTag();
            }
        }
        else if (data == SpecialCharacters.START_TAG.toChar()){
            isBuildingElement = true;
            elementData += data;
            setStatus(Status.MORE_DATA_NEEDED);
        }
    }

    private void handleEndOfTag(){
        if (elementData.equals(closingElementToFilter)) {
            setStatus(Status.DATA_CAN_NOT_MANIPULATE);
        }
        else if(elementData.equals(startingElementToFilter)){
            isStartingElementCurrentlyAppeared = true;
            setStatus(Status.DATA_CAN_NOT_MANIPULATE);
        }
        isBuildingElement = false;
        elementData = "";
    }
}
