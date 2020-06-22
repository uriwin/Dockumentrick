package inputStream;

import manipulateActions.ManipulateAction;
import status.Status;

import java.io.IOException;
import java.io.InputStream;

public class ManipulatorInputStream extends InputStream {

    private ManipulateAction dataManipulatorAction;

    private byte[] dataBuffer;

    private int dataBufferPos;

    private int dataBufferNextPos;

    private int lastByteRead;

    private InputStream in;

    public ManipulatorInputStream(InputStream in, ManipulateAction dataManipulatorAction) throws IOException {
        this.in = in;

        this.dataManipulatorAction = dataManipulatorAction;

        this.dataBufferPos = this.dataBufferNextPos = 0;

        this.lastByteRead = in.read();
    }

    @Override
    public synchronized int read() throws IOException {
        if (dataBufferNextPos == 0 || dataBufferNextPos == dataBuffer.length) {
            if (lastByteRead == -1) {
                return lastByteRead;
            }
            dataBuffer = getDataToFill((char) lastByteRead).getBytes();
            dataBufferPos = dataBufferNextPos = 0;
        }

        dataBufferPos = dataBufferNextPos;
        dataBufferNextPos += 1;
        return dataBuffer[dataBufferPos];
    }

    private String getDataToFill(char data) throws IOException {
        String dataToReturn;

        dataManipulatorAction.updateFiltersStatus(data);
        if (dataManipulatorAction.isAtLeastOneFiltersStatusBad()) {
            lastByteRead = in.read();
            return String.valueOf(data);
        }

        dataManipulatorAction.updateStatus(data);
        if (dataManipulatorAction.getStatus() == Status.MORE_DATA_NEEDED){
            dataToReturn = handleMoreDataNeeded(data);
        }
        else {
            lastByteRead = in.read();
            dataToReturn = String.valueOf(data);
        }

        if (isDataCanManipulate()){
            return dataManipulatorAction.manipulateDataAction(dataToReturn);
        }
        return dataToReturn;
    }

    private String handleMoreDataNeeded(char data) throws IOException {
        StringBuilder dataToReturn = new StringBuilder();

        while (dataManipulatorAction.getStatus() == Status.MORE_DATA_NEEDED
                && !dataManipulatorAction.isAtLeastOneFiltersStatusBad()) {
            dataToReturn.append(data);
            lastByteRead = in.read();
            data = (char) lastByteRead;
            dataManipulatorAction.updateStatus(data);
            if (dataManipulatorAction.getStatus() != Status.MORE_DATA_NEEDED){
                break;
            }
            dataManipulatorAction.updateFiltersStatus(data);
        }
        return dataToReturn.toString();
    }

    private boolean isDataCanManipulate(){
        return ((dataManipulatorAction.getStatus() == Status.DATA_CAN_MANIPULATE)
                && (dataManipulatorAction.isAllFiltersStatusGood()));
    }
}
