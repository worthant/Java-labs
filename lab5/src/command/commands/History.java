package command.commands;

import command.Command;

import java.util.Arrays;

public class History extends Command {
    private static final int QUEUE_SIZE = 14;
    private static String[] commandQueue;
    private static int head;
    private static int tail;
    public History() {
        super(false);
    }

    @Override
    public void execute() {
        if (checkArgument(getArgument())) {
            History.printHistory();
        }
    }

    @Override
    public boolean checkArgument(Object inputArgument) {
        if (inputArgument == null)
            return true;
        else {
            System.out.println("Команда save не имеет аргументов!");
            return false;
        }
    }
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
    public static void initializeCommandsHistoryQueue() {
        commandQueue = new String[QUEUE_SIZE];
        Arrays.fill(commandQueue, null);
        head = -1;
        tail = -1;
    }

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
