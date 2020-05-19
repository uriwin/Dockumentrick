package inputStream;

import manipulateActions.ManipulateAction;
import fileFormat.FileFormatFactory;
import fileFormat.FileFormatType;
import inputStream.DataManipulatorInputStream;

import java.io.*;
import java.util.List;

public class DataManipulatorInputStreamDecorator {
    private String inputFilePath;
    private FileFormatType fileFormat;
    private List<ManipulateAction> dataManipulatorActions;

    public DataManipulatorInputStreamDecorator(String inputFilePath, FileFormatType fileFormat,
                                               List<ManipulateAction> dataManipulatorActions) {
        this.inputFilePath = inputFilePath;
        this.fileFormat = fileFormat;
        this.dataManipulatorActions = dataManipulatorActions;
    }

    public InputStream getDataManipulatorInputStream() throws IOException {
        InputStream inputStream = new FileInputStream(inputFilePath);
        FileFormatFactory fileFormatFactory = new FileFormatFactory();
        for (ManipulateAction dataManipulatorAction : dataManipulatorActions) {
            inputStream = new DataManipulatorInputStream(inputStream,
                    fileFormatFactory.getFileFormat(fileFormat), dataManipulatorAction);
        }
        return inputStream;
    }
}
