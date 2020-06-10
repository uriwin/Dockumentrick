package inputStream;

import manipulateActions.ManipulateAction;
import status.Status;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ManipulatorInputStream extends BufferedInputStream {

    private ManipulateAction dataManipulatorAction;

    private byte[] dataBuffer;

    private int dataBufferPos;

    private int lastByteRead;

    public ManipulatorInputStream(InputStream in, ManipulateAction dataManipulatorAction) throws IOException {
        super(in);

        this.dataManipulatorAction = dataManipulatorAction;

        this.dataBufferPos = 0;

        this.lastByteRead = in.read();
    }

    @Override
    public synchronized int read() throws IOException {
        if (dataBufferPos == 0 || dataBufferPos == dataBuffer.length) {
            if (lastByteRead == -1) {
                return lastByteRead;
            }
            dataBuffer = fillDataBuffer((char) lastByteRead).getBytes();
            dataBufferPos = 0;
        }
        return dataBuffer[dataBufferPos++];
    }

    public String fillDataBuffer(char data) throws IOException {
        String dataToFill = getDataToFill(data);
        if (dataManipulatorAction.getStatus() == Status.DATA_CAN_MANIPULATE
                && dataManipulatorAction.isFiltersStatusGood()) {
            return dataManipulatorAction.manipulateDataAction(dataToFill);
        }
        return dataToFill;
    }

    public String getDataToFill(char data) throws IOException {
        dataManipulatorAction.updateFiltersStatus(data);
        if (dataManipulatorAction.isFiltersStatusBad()) {
            lastByteRead = in.read();
            return String.valueOf(data);
        }

        dataManipulatorAction.updateStatus(data);
        if (dataManipulatorAction.getStatus() == Status.MORE_DATA_NEEDED){
            return handleMoreDataNeeded(data);
        }
        lastByteRead = in.read();
        return String.valueOf(data);
    }

    public String handleMoreDataNeeded(char data) throws IOException {
        StringBuilder dataToReturn = new StringBuilder();

        while (dataManipulatorAction.getStatus() == Status.MORE_DATA_NEEDED
                && !dataManipulatorAction.isFiltersStatusBad()) {
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
}
