package command.commands;

import collectionManagers.CollectionManager;
import command.Command;

public class Clear extends Command {

    public Clear() {
        super(false);
    }

    @Override
    public void execute() {
        if (checkArgument(getArgument())) {
            CollectionManager.getCityCollection().clear();
            System.out.println("Коллекция успешно очищена!");
        }
    }

    @Override
    public boolean checkArgument(Object inputArgument) {
        if (inputArgument == null)
            return true;
        else {
            System.out.println("Команда clear не имеет аргументов!");
            return false;
        }
    }

}
