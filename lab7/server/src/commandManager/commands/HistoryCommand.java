package commandManager.commands;

import models.City;
import models.handlers.CityHandler;
import models.handlers.CollectionHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import responses.CommandStatusResponse;

import java.util.Arrays;
import java.util.TreeSet;

/**
 Command that prints the command history
 */
public class HistoryCommand implements BaseCommand {
    private static final Logger logger = LogManager.getLogger("io.github.worthant.lab6.commands.history");
    private CommandStatusResponse response;
    /**
     The maximum size of the command history
     */
    private static final int QUEUE_SIZE = 14;
    /**
     An array containing the commands in the history
     */
    private static String[] commandQueue;
    /**
     The index of the head of the queue
     */
    private static int head;
    /**
     The index of the tail of the queue
     */
    private static int tail;

    @Override
    public String getName() {
        return "history";
    }

    @Override
    public String getDescr() {
        return "Prints the last 14 commands (without their arguments).";
    }

    @Override
    public void execute(String[] args) {
        response = CommandStatusResponse.ofString(HistoryCommand.printHistory().toString());

        logger.info(response.getResponse());
    }

    /**
     Prints the command history
     */
    public static StringBuilder printHistory() {
        StringBuilder sb = new StringBuilder();

        if (head == -1) {
            sb.append("No commands in history");
        } else {
            int i = head;
            int count = 1;
            do {
                sb.append(count + ": " + commandQueue[i]).append('\n');
                i = (i + 1) % QUEUE_SIZE;
                count++;
            } while (i != tail + 1);
        }

        return sb;
    }

    /**
     Initializes the command history queue
     */
    public static void initializeCommandsHistoryQueue() {
        commandQueue = new String[QUEUE_SIZE];
        Arrays.fill(commandQueue, null);
        head = -1;
        tail = -1;
    }

    /**
     Adds a command to the command history queue
     @param command the command to add
     */
    public static void addToCommandsHistoryQueue(String command) {
        if (head == -1) {
            head = 0;
            tail = 0;
            commandQueue[head] = command;
        } else {
            tail = (tail + 1) % QUEUE_SIZE;
            commandQueue[tail] = command;
            if (tail == head) {
                head = (head + 1) % QUEUE_SIZE;
            }
        }
    }

    @Override
    public CommandStatusResponse getResponse() {
        return response;
    }
}

