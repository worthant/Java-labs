package commandManagers.commands;

import collection.City.City;
import collectionManagers.CityManager;
import collectionManagers.CollectionManager;
import collectionManagers.modeManagers.ModeManager;
import commandManagers.Command;
import exceptions.BuildObjectException;

import java.util.TreeSet;

/**
 * Command to add a new city to the collection.
 *
 * @author boris
 */
public class Add extends Command {
    ModeManager<City> handler;

    /**
     * Default constructor from 1.0
     */
    public Add() {
        super(false);
    }

    /**
     * Provides choosing handler
     *
     * @param handler ModuleHandler for operating
     * @since 2.0
     */
    public Add(ModeManager<City> handler) {
        super(false);
        this.handler = handler;
    }

    @Override
    public String getName() {
        return "Add";
    }

    @Override
    public String getDescr() {
        return "Adds new element to collection.";
    }

    /**
     * Adds a new city to the collection if an argument is not provided.
     * Prints a message indicating that the command does not take arguments otherwise.
     */
    @Override
    public void execute() throws BuildObjectException {
        if (checkArgument(this.getArgument())) {
            CollectionManager<TreeSet<City>, City> manager = CityManager.getInstance();
            manager.addElementToCollection(handler.buildObject());
            System.out.println("City Object successfully added!");
        }
    }

    /**
     * Checks whether an argument is provided or not.
     *
     * @param inputArgument the argument to be checked.
     * @return true if no argument is provided and false otherwise.
     */
    @Override
    public boolean checkArgument(Object inputArgument) {
        if (inputArgument == null)
            return true;
        else {
            System.out.println("Add has no arguments!");
            return false;
        }
    }

}
