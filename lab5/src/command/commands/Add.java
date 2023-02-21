package command.commands;
import collection.City.City;
import collectionManagers.CollectionManager;
import command.Command;
public class Add extends Command{

    public Add() {
        super(false);
    }

    @Override
    public void execute() {
        if (checkArgument(getArgument())) {
            CollectionManager.getCityCollection().add(CollectionManager.getNewCity());
        }
    }

    @Override
    public boolean checkArgument(Object inputArgument) {
        if (inputArgument == null)
            return true;
        else {
            System.out.println("Команда add не имеет аргументов!");
            return false;
        }
    }

}
