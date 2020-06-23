package fileFilter.filters;

import fileFilter.SpecialCharacters;
import status.AbstractStatusProvider;
import status.Status;

public class CsvColumnFilter extends AbstractStatusProvider {
    private final int columnPositionToFilter;

    private int currentColumnPosition;

    private boolean isInQuotes;

    private boolean isInApostrophe;

    public CsvColumnFilter(int columnPositionToFilter) {
        this.currentColumnPosition = 0;

        this.columnPositionToFilter = columnPositionToFilter;

        this.isInQuotes = false;

        this.isInApostrophe = false;

        setStatus(Status.DATA_CAN_NOT_MANIPULATE);
    }

    public void updateStatus(char data) {
        updateIsInQuotes(data);
        updateIsInApostrophe(data);

        if (data == SpecialCharacters.COMMA.toChar() && (!isInQuotes && !isInApostrophe))
            currentColumnPosition += 1;

        if (data == SpecialCharacters.NEW_LINE.toChar() && (!isInQuotes && !isInApostrophe))
            currentColumnPosition = 0;

        changeStatus();
    }

    private void changeStatus(){
        if (currentColumnPosition == columnPositionToFilter) {
            setStatus(Status.DATA_CAN_MANIPULATE);
        }
        else{
            setStatus(Status.DATA_CAN_NOT_MANIPULATE);
        }
    }

    private void updateIsInQuotes(char data){
        if (data == SpecialCharacters.QUOTES.toChar()){
            isInQuotes = !isInQuotes;
        }
    }

    private void updateIsInApostrophe(char data){
        if (data == SpecialCharacters.APOSTROPHE.toChar()){
            isInApostrophe = !isInApostrophe;
        }
    }
}
