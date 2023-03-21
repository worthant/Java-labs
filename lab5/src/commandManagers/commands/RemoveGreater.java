package commandManagers.commands;
import collection.City.City;
import collectionManagers.CityManager;
import collectionManagers.CollectionManager;
import collectionManagers.modeManagers.ModeManager;
import commandManagers.Command;
import exceptions.BuildObjectException;

import java.util.Iterator;
import java.util.TreeSet;

/**
 * This class represents the RemoveGreater command in the program. When executed, it removes all
 * elements from the collection that have a population greater than the specified city.
 */
public class RemoveGreater extends Command {

    ModeManager<City> handler;

    /**
     * Default constructor from 1.0
     */
    public RemoveGreater() {
        super(false);
    }

    /**
     * Provides choosing handler
     *
     * @since 2.0
     * @param handler ModuleHandler for operating
     */
    public RemoveGreater(ModeManager<City> handler) {
        super(false);
        this.handler = handler;
    }

    @Override
    public String getName() {
        return "RemoveGreater";
    }

    @Override
    public String getDescr() {
        return "Removes all elements from the collection that have a population greater than the specified city.";
    }

    /**
     * Executes the RemoveGreater command. Removes all elements from the collection that have a
     * population greater than the specified city.
     */
    @Override
    public void execute() throws BuildObjectException {
        if (checkArgument(this.getArgument())) {
            CollectionManager<TreeSet<City>, City> manager = CityManager.getInstance();
            if (manager.getCollection() == null) {
                System.out.println("This command doesn't work right now");
                return;
            }

            City newCity = handler.buildObject();
            Iterator<City> iter = manager.getCollection().iterator();

            int i = 0;
            while(iter.hasNext()) {
                if (newCity.getPopulation() < iter.next().getPopulation()) {
                    iter.remove();
                    i ++;
                }
            }

            // english moment
            if (i == 1)
                System.out.println(i + "object removed");
            else System.out.println(i + "objects removed");
        }
    }


    /**
     * Checks whether the given argument is valid for this command. Since this command doesn't
     * require an argument, this method always returns true.
     *
     * @param inputArgument the argument to be checked
     * @return true since this command doesn't require an argument
     */
    @Override
    public boolean checkArgument(Object inputArgument) {
        if (inputArgument == null)
            return true;
        else {
            System.out.println("Remove_greater has no arguments!");
            return false;
        }
    }

}
