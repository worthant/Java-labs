package responseLogic.responseSenders;

import multithreading.MultithreadingManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import requestLogic.CallerBack;
import responses.BaseResponse;
import serverLogic.ServerConnection;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.concurrent.ExecutorService;

public class ResponseSender {
    private static final Logger logger = LogManager.getLogger("io.github.worthant.lab6");

    public static void sendResponse(BaseResponse response, ServerConnection connection, CallerBack to) {
        if (response != null) {
            ExecutorService responseThreadPool = MultithreadingManager.getResponseThreadPool();
            responseThreadPool.submit(() -> {
                try {
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    ObjectOutputStream oos = new ObjectOutputStream(bos);
                    oos.writeObject(response);
                    connection.sendData(bos.toByteArray(), to.getAddress(), to.getPort());
                    logger.info("response sent.");
                } catch (IOException e) {
                    logger.error("An error occurred while sending the response.", e);
                }
            });
        }
    }
}

