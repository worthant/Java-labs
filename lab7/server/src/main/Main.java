package main;

import commandManager.ServerCommandManager;
import commandManager.commands.HistoryCommand;
import commandManager.commands.SaveCommand;
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

import java.io.*;
import java.net.SocketTimeoutException;

import serverLogic.DatagramServerConnectionFactory;
import serverLogic.ServerConnection;

import javax.swing.*;
import java.io.IOException;
import java.util.Scanner;
import java.util.TreeSet;

@SuppressWarnings("InfiniteLoopStatement")
public class Main {
    public static final int PORT = 50457;
    private static final Logger logger = LogManager.getLogger("io.github.worthant.lab6");

    /**
     * Environment key to CSV file for store collection.
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
            // loader.loadCollectionFromFile(ENV_KEY);
            loader.loadCollectionFromDatabase();
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
        logger.info("Creating a connection...");
        ServerConnection connection = new DatagramServerConnectionFactory().initializeServer(PORT);
        while (true) {
            try {
                StatusRequest rq = connection.listenAndGetData();
                if (rq.getCode() < 0) {
                    logger.debug("Status code: " + rq.getCode());
                    continue;
                }

                RequestReader rqReader = new RequestReader(rq.getInputStream());
                BaseRequest baseRequest = rqReader.readObject();
                var request = new ServerRequest(baseRequest, rq.getCallerBack(), connection);
                RequestWorkerManager worker = new RequestWorkerManager();
                worker.workWithRequest(request);
            } catch (SocketTimeoutException e) {
                // Check if there's any input available in System.in
                try {
                    if (System.in.available() > 0) {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                        String line = reader.readLine();
                        if (line != null) {
                            ServerCommandManager manager = new ServerCommandManager();
                            manager.executeCommand(line.split(" "));
                        }
                    }
                } catch (IOException e1) {
                    logger.error("Something went wrong during I/O", e1);
                }
            } catch (IOException e) {
                logger.error("Something went wrong during I/O", e);
            } catch (ClassNotFoundException e) {
                logger.error("Class not Found", e);
            } catch (RuntimeException e) {
                logger.fatal(e);
            }
        }
    }
}