package main;

import clientLogic.ClientHandler;
import commandManager.commands.HistoryCommand;
import commandManager.commands.SaveCommand;
import exceptions.NotAvailableException;
import models.City;
import models.handlers.CollectionHandler;
import models.handlers.CityHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import requestLogic.RequestReader;
import requestLogic.StatusRequest;
import requestLogic.requestWorkers.RequestWorkerManager;
import requestLogic.requests.ServerRequest;
import requests.BaseRequest;
import responseLogic.responseSenders.ResponseSender;
import responses.ErrorResponse;
import serverLogic.DatagramServerConnectionFactory;
import serverLogic.ServerConnection;

import javax.swing.*;
import java.io.IOException;
import java.util.TreeSet;

@SuppressWarnings("InfiniteLoopStatement")
public class Main {
    public static final int PORT = 50457;
    private static final Logger logger = LogManager.getLogger("io.github.worthant.lab6");

    /**
     * Environment key to XML file for store collection.
     */
    public static final String ENV_KEY = "lab5";

    public static void main(String[] args) {
        CollectionHandler<TreeSet<City>, City> handler = CityHandler.getInstance();
        CityHandler loader = CityHandler.getInstance();
        HistoryCommand.initializeCommandsHistoryQueue();

        logger.trace("This is a server!");

        // setup background tasks
        var timer = new Timer(600000, event -> new SaveCommand().execute(new String[0]));
        timer.start();

        // load collection
        try {
            loader.loadCollection(ENV_KEY);
            logger.info("Loaded " + handler.getCollection().size() + " elements total.");
            logger.info(" ");

            // commands
            logger.info("Welcome to CLI! Now you are operating with collection of \n  type: " + handler.getCollection().getClass().getName() + ", \n  filled with elements of type: " + handler.getFirstOrNew().getClass().getName());
            logger.info("Now server is listening a requests.");
        } catch (IllegalArgumentException e) {
            logger.info("Collection can't be loaded from file due to some troubles: \n");
            logger.info(e.getMessage());
        }

        // connection
        ServerConnection connection = new DatagramServerConnectionFactory().initializeServer(PORT);
        while (true) {
            try {
                StatusRequest rq = connection.listenAndGetData();
                if (rq.getCode() < 0) {
                    logger.debug("Status code: " + rq.getCode());
                    continue;
                }

                ClientHandler.getInstance().approveCallerBack(rq.getCallerBack());
                ClientHandler.getInstance().restartTimer();

                RequestReader rqReader = new RequestReader(rq.getInputStream());
                BaseRequest baseRequest = rqReader.readObject();
                var request = new ServerRequest(baseRequest, rq.getCallerBack(), connection);
                RequestWorkerManager worker = new RequestWorkerManager();
                worker.workWithRequest(request);
            } catch (IOException e) {
                logger.error("Something went wrong during I/O", e);
            } catch (ClassNotFoundException e) {
                logger.error("Class not Found", e);
            } catch (RuntimeException e) {
                logger.fatal(e);
            } catch (NotAvailableException e) {
                try {
                    ErrorResponse response = new ErrorResponse("Server is busy right now...");
                    ResponseSender.sendResponse(response, connection, e.getDeniedClient());
                } catch (IOException ex) {
                    logger.fatal("Can't send response", e);
                }
            }
        }
    }
}