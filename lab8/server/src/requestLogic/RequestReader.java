package requestLogic;

import requests.BaseRequest;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

public class RequestReader {
    final InputStream in;

    public RequestReader(InputStream in) {
        this.in = in;
    }

    public BaseRequest readObject() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(in);
        return (BaseRequest) ois.readObject();
    }
}
