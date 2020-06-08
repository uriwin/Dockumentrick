package inputStream;

import fileFormat.FileFormatFactory;
import fileFormat.FileFormatType;
import manipulateActions.ManipulateAction;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class DataManipulatorInputStreamDecorator {

    private FileFormatType fileFormat;

    private List<ManipulateAction> dataManipulatorActions;

    private InputStream inputStream;

    public DataManipulatorInputStreamDecorator(FileFormatType fileFormat, List<ManipulateAction> dataManipulatorActions,
                                               InputStream inputStream) {
        this.fileFormat = fileFormat;

        this.dataManipulatorActions = dataManipulatorActions;

        this.inputStream = inputStream;
    }

    public InputStream getDataManipulatorInputStream() throws IOException {
        FileFormatFactory fileFormatFactory = new FileFormatFactory();
        for (ManipulateAction dataManipulatorAction : dataManipulatorActions) {
            inputStream = new ManipulatorInputStream(inputStream, dataManipulatorAction);
        }
        return inputStream;
    }
}
