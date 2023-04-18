package responseLogic;

import exceptions.ServerNotAvailableException;
import responses.BaseResponse;
import responses.ErrorResponse;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

public class ResponseReader {
    final InputStream in;

    public ResponseReader(InputStream in) {
        this.in = in;
    }

    public BaseResponse readObject() throws IOException, ClassNotFoundException, ServerNotAvailableException {
        ObjectInputStream ois = new ObjectInputStream(in);
        BaseResponse result = (BaseResponse) ois.readObject();
        if (result instanceof ErrorResponse)
            throw new ServerNotAvailableException(((ErrorResponse) result).getMsg());
        return result;
    }
}
