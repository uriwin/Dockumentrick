package fileETL;

import manipulateActions.ManipulateAction;
import fileFormat.FileFormatFactory;
import fileFormat.FileFormatType;
import inputStream.DataManipulatorInputStream;

import java.io.*;
import java.util.List;

public class FileETL {
    private String inputFilePath;
    private String outputFilePath;
    private FileFormatType fileFormat;
    private List<ManipulateAction> dataManipulatorActions;

    public FileETL(String inputFilePath, String outputFilePath, FileFormatType fileFormat,
                   List<ManipulateAction> dataManipulatorActions) {
        this.inputFilePath = inputFilePath;
        this.outputFilePath = outputFilePath;
        this.fileFormat = fileFormat;
        this.dataManipulatorActions = dataManipulatorActions;
    }

    public void executeETL() throws IOException {
        InputStream inputStream = getDecoratedInputStream();
        OutputStream outputStream = new FileOutputStream(outputFilePath);
        int data = inputStream.read();
        while(data != -1) {
            outputStream.write(data);
            data = inputStream.read();
        }
        inputStream.close();
        outputStream.close();
    }

    public InputStream getDecoratedInputStream() throws IOException {
        InputStream inputStream = new FileInputStream(inputFilePath);
        FileFormatFactory fileFormatFactory = new FileFormatFactory();
        for (ManipulateAction dataManipulatorAction : dataManipulatorActions) {
            inputStream = new DataManipulatorInputStream(inputStream,
                    fileFormatFactory.getFileFormat(fileFormat), dataManipulatorAction);
        }
        return inputStream;
    }
}
