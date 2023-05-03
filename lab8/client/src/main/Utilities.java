package main;

import exceptions.StreamInterruptedException;
import exceptions.WrongAmountOfArgumentsException;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Utilities {
    public static void checkArgumentsOrThrow(int provided, int expected) throws WrongAmountOfArgumentsException {
        if (provided - 1 != expected)
            throw new WrongAmountOfArgumentsException("Provided " + (provided - 1) + " arguments, expected " + expected);
    }

    /**
     * Generates a unique ID based on threads
     *
     * @return the unique ID that was generated
     */
    public static Long generateId() {
        Long id;
        id = ThreadLocalRandom.current().nextLong(1, Long.MAX_VALUE);
        return id;
    }
}
