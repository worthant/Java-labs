package commandManagers.commands;

import commandManagers.Command;

import java.util.Arrays;

/**
 Command that prints the command history
 */
public class History extends Command {
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
    public History() {
        super(false);
    }

    @Override
    public String getName() {
        return "history";
    }

    @Override
    public String getDescr() {
        return "Prints the last 14 commands (without their arguments).";
    }

    /**
     Executes the History command
     */
    @Override
    public void execute() {
        if (checkArgument(getArgument())) {
            History.printHistory();
        }
    }

    /**
     * Checks whether the argument is valid for the History command
     * @param inputArgument the argument to check
     * @return true if the argument is null, false otherwise
     */
    @Override
    public boolean checkArgument(Object inputArgument) {
        if (inputArgument == null)
            return true;
        else {
            System.out.println("Команда save не имеет аргументов!");
            return false;
        }
    }

    /**
     Prints the command history
     */
    public static void printHistory() {
        if (head == -1) {
            System.out.println("No commands in history");
        } else {
            int i = head;
            int count = 1;
            do {
                System.out.println(count + ": " + commandQueue[i]);
                i = (i + 1) % QUEUE_SIZE;
                count++;
            } while (i != tail + 1);
        }
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

}
