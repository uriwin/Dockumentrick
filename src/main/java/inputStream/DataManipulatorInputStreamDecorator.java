package inputStream;

import manipulateActions.ManipulateAction;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class DataManipulatorInputStreamDecorator {

    private List<ManipulateAction> dataManipulatorActions;

    private InputStream inputStream;

    public DataManipulatorInputStreamDecorator(List<ManipulateAction> dataManipulatorActions, InputStream inputStream) {
        this.dataManipulatorActions = dataManipulatorActions;

        this.inputStream = inputStream;
    }

    public InputStream getDataManipulatorInputStream() throws IOException {
        for (ManipulateAction dataManipulatorAction : dataManipulatorActions) {
            inputStream = new ManipulatorInputStream(inputStream, dataManipulatorAction);
        }
        return inputStream;
    }
}
