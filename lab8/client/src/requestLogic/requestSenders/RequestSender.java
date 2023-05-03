package requestLogic.requestSenders;

import exceptions.ServerNotAvailableException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import requests.BaseRequest;
import responseLogic.ResponseReader;
import responses.BaseResponse;
import serverLogic.ServerConnection;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;

public class RequestSender {
    private static final Logger logger = LogManager.getLogger("io.github.worthant.lab6");

    public BaseResponse sendRequest(BaseRequest request, ServerConnection connection) throws IOException, ServerNotAvailableException {
        BaseResponse response = null;
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(request);
            logger.info("Request sent");
            InputStream responseStream = connection.sendData(bos.toByteArray());
            if (responseStream != null) {
                ResponseReader reader = new ResponseReader(responseStream);
                response = reader.readObject();
                logger.info("Received response");
            } else logger.info("Received null");
        } catch (ClassNotFoundException e) {
            logger.error("Response class not found.");
        }
        return response;
    }
}
