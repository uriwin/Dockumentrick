package inputStream;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class BufferInputStream  extends FilterInputStream {
    private byte[] dataReadBuffer;
    private InputStream inputStream;

    private int dataReadPos;
    private int markPos;
    private int dataReadCount;

    public BufferInputStream(InputStream inputStream) throws IOException {
        super(inputStream);
        this.inputStream = inputStream;
        this.dataReadBuffer = new byte[inputStream.available()];
        this.dataReadCount = this.dataReadPos = 0;
    }

    @Override
    public int read() throws IOException {
        if (dataReadCount == dataReadPos){
            if (dataReadPos == dataReadBuffer.length){
                byte[] bufferToExpand = new byte[1024];
                dataReadBuffer = concatenateByteArrays(dataReadBuffer, bufferToExpand);
            }
            dataReadBuffer[dataReadCount] = (byte) this.inputStream.read();
            dataReadCount += 1;
        }
        dataReadPos += 1;
        return dataReadBuffer[dataReadPos -1];
    }


    public byte[] concatenateByteArrays(byte[] a, byte[] b) {
        byte[] result = new byte[a.length + b.length];
        System.arraycopy(a, 0, result, 0, a.length);
        System.arraycopy(b, 0, result, a.length  , b.length);
        return result;
    }

    @Override
    public synchronized void mark(int readlimit) {
        markPos = dataReadPos;
//        super.mark(readlimit);
    }


    @Override
    public synchronized void reset() throws IOException {
        dataReadPos = markPos;
//        super.reset();
    }
}
