package main;

import Client.Client;
import commandManager.CommandDescriptionHolder;
import commandManager.CommandExecutor;
import commandManager.CommandLoaderUtility;
import commandManager.CommandMode;
import exceptions.CommandsNotLoadedException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import requestLogic.requestSenders.AuthRequestSender;
import requestLogic.requestSenders.RegRequestSender;
import responses.AuthResponse;
import responses.RegResponse;
import serverLogic.*;
import models.validators.*;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Program entry point class. Contains main() method.
 * This program is managing collection of type <code>HashSet&#8249;Route></code>
 *
 * @author worthant
 * @since 1.0
 */
public class Main {
    public static final int PORT = 50457;
    private static final Logger logger = LogManager.getLogger("lab6");

    /**
     * Program entry point.
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) {

        // server connecting
        try {
            //ServerConnection connection = new UdpServerConnectionFactory().openConnection(InetAddress.getByName(HOST_ADDRESS), PORT);
            ServerConnection connection = new UdpConnectionBlockDecorator((UdpServerConnection) new UdpServerConnectionFactory().openConnection(InetAddress.getLocalHost(), PORT), true);
            ServerConnectionHandler.setServerConnection(connection);
            connection.openConnection();

            System.out.println("\nWelcome to lab7 client!) Please, authorize or register to start working with commands:\n");
            String name;
            char[] passwd;
            String nextLine;
            boolean auth;
            boolean authorizationDone = false;
            InputValidator inputValidator = new InputValidator();
            inputValidator.canBeNull(false);
            do {
                System.out.println("Write:\n  1 - to authorize,\n  {anything} - to register\n↓");
                try {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                    nextLine = reader.readLine().trim();
                    auth = nextLine.equals("1");

                    while (true) {
                        System.out.print("Enter your name(not null!)(type: String): ");
                        nextLine = reader.readLine().trim();

                        if (inputValidator.validate(nextLine)) {
                            name = nextLine.split(" ")[0];
                            break;
                        } else System.out.println("Input should not be empty!(name is not null)");
                    }

                    while (true) {
                        System.out.print("Enter your password(not null!)(type: charArray): ");
                        nextLine = reader.readLine().trim();

                        if (inputValidator.validate(nextLine)) {
                            passwd = nextLine.split(" ")[0].toCharArray();
                            break;
                        } else System.out.println("Input should not be empty!(passwd is not null)");
                    }

                    if (auth) {
                        System.out.println("Checking if user exists in database");
                        AuthRequestSender rqSender = new AuthRequestSender();
                        AuthResponse response = rqSender.sendAuthData(name, passwd, ServerConnectionHandler.getCurrentConnection());
                        if (response.isAuth()) {
                            Client.getInstance(name, passwd);
                            System.out.println("Successfully authenticated");
                            authorizationDone = true;
                        } else
                            System.out.println("There is no user with this name, or passwd is incorrect");
                    } else {
                        System.out.println("Registering new user");
                        RegRequestSender rqSender = new RegRequestSender();
                        RegResponse response = rqSender.sendRegData(name, passwd, ServerConnectionHandler.getCurrentConnection());
                        if (response.isReg()) {
                            System.out.println("Successfully registered new user with:\n name: " + name + ",\n passwd: " + Arrays.toString(passwd));
                        } else
                            System.out.println("User with this credentials already exists in database");
                    }
                } catch (Exception e) {
                    System.out.println("При вводе произошла непредвиденная ошибка!" + e.getMessage());
                }
            } while (!authorizationDone);

            // request commands
            boolean commandsNotLoaded = true;
            int waitingCount = 4000;
            while (commandsNotLoaded) {
                try {
                    CommandLoaderUtility.initializeCommands();
                    CommandExecutor executor = new CommandExecutor(CommandDescriptionHolder.getInstance().getCommands(), System.in, CommandMode.CLI_UserMode);
                    commandsNotLoaded = false;

                    // start executing
                    System.out.println("Welcome to CLI! We've established connection to a server.");
                    System.out.println("Now you can enter the commands. Use help for reference.");
                    executor.startExecuting();
                } catch (CommandsNotLoadedException e) {
                    logger.warn("We couldn't get commands from server last time, so now we'll try to do this again...");

                    AtomicInteger secondsRemained = new AtomicInteger(waitingCount / 1000 - 1);

                    javax.swing.Timer timer = new Timer(1000, (x) -> logger.info("Re-attempt in " + secondsRemained.getAndDecrement() + " seconds. You may interrupt awaiting by hitting Enter."));

                    timer.start();

                    CountDownLatch latch = new CountDownLatch(1);

                    int finalWaitingCount = waitingCount;
                    Runnable wait = () -> {
                        try {
                            Thread.sleep(finalWaitingCount);
                            latch.countDown();
                        } catch (InterruptedException ex) {
                            logger.info("Continuing...");
                        }
                    };

                    /*Runnable forceInterrupt = () -> {
                        try {
                            int ignored = System.in.read();
                            latch.countDown();
                        } catch (IOException ex) {
                            logger.error("Something went wrong during i/o.");
                        }
                    };*/

                    Thread tWait = new Thread(wait);
                    //Thread tForceInt = new Thread(forceInterrupt);

                    tWait.start();
                    //tForceInt.start();

                    try {
                        latch.await();
                        timer.stop();
                        tWait.interrupt();
                    } catch (InterruptedException ex) {
                        logger.info("Interrupted");
                    }

                    waitingCount += 2000;
                }
            }
        } catch (UnknownHostException ex) {
            logger.fatal("Can't find host.");
        } catch (IOException ex) {
            logger.error("Something went wrong during IO operations.");
        }
    }
}