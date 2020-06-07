package inputStream;

import fileFormat.BaseFileValidator;
import manipulateActions.ManipulateAction;

import java.io.BufferedInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class DataManipulatorInputStream extends BufferedInputStream {

    private BaseFileValidator fileFormat;

    private ManipulateAction dataManipulatorAction;

    private byte[] dataToReturnBuffer;

    private int dataToReturnPos;

    private int dataToReturnCount;

    private int lastByteRead;



    public DataManipulatorInputStream(InputStream inputStream, BaseFileValidator fileFormat,
                                      ManipulateAction dataManipulatorAction) throws IOException {
        super(inputStream);

        this.fileFormat = fileFormat;

        this.dataManipulatorAction = dataManipulatorAction;

        this.dataToReturnCount = this.dataToReturnPos = 0;

        this.lastByteRead = -2;
    }


    @Override
    public synchronized int read() throws IOException{
        if (dataToReturnCount == dataToReturnPos) {
            if (!refillDataToReturnBuffer())
                return -1;
        }
        dataToReturnPos += 1;
        return dataToReturnBuffer[dataToReturnPos - 1];
    }

    public boolean refillDataToReturnBuffer() throws IOException {
        int fileByte = getFileByteToManipulate();
        if (fileByte == -1) {
            return false;
        }

        String dataToReturn = getDataToReturn(fileByte);
        dataToReturnBuffer = dataToReturn.getBytes();
        dataToReturnPos = 0;
        dataToReturnCount = dataToReturn.length();
        return true;
    }

    public int getFileByteToManipulate() throws IOException {
        int fileByteToManipulate;
        if (lastByteRead == -2)
            fileByteToManipulate = in.read();
        else {
            fileByteToManipulate = lastByteRead;
            lastByteRead = -2;
        }
        return fileByteToManipulate;
    }

    public boolean isFileByteToManipulate(int fileByte) {
        if (fileFormat.isByteRelatedToFileFormat(fileByte))
            return false;
        if (!dataManipulatorAction.areAllFiltersActivated())
            return false;
        return dataManipulatorAction.isByteRequiredForAction(fileByte);
    }


    public String getDataToReturn(int fileByte) throws IOException {
        StringBuilder dataToManipulate = new StringBuilder();
        String dataToReturn = "";
        dataManipulatorAction.updateFilters(fileByte);
        boolean isFileByteToManipulate = isFileByteToManipulate(fileByte);

        if (!isFileByteToManipulate) {
            dataToReturn += (char) fileByte;
            return dataToReturn;
        }

        while (isFileByteToManipulate) {
            dataToManipulate.append((char) fileByte);
            fileByte = in.read();
            dataManipulatorAction.updateFilters(fileByte);
            isFileByteToManipulate = isFileByteToManipulate(fileByte);
        }
        lastByteRead = fileByte;
        dataToReturn = dataManipulatorAction.manipulateDataAction(dataToManipulate.toString());

        return dataToReturn;
    }
}
