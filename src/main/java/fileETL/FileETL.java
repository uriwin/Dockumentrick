package fileETL;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileETL {
    private InputStream inputStream;
    private OutputStream outputStream;

    public FileETL(InputStream inputStream, OutputStream outputStream) {
        this.inputStream = inputStream;
        this.outputStream = outputStream;
    }

    public void executeETL() throws IOException {
        int data = inputStream.read();
        while (data != -1) {
            outputStream.write(data);
            data = inputStream.read();
        }
        inputStream.close();
        outputStream.close();
    }
}
