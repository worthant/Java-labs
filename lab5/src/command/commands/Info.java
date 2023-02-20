package command.commands;

import collectionManagers.CollectionManager;
import command.Command;

public class Info extends Command {

    public Info() {
        super(false);
    }

    @Override
    public void execute() {
        if (checkArgument(getArgument())) {
            System.out.println("Тип коллекции: " + CollectionManager.getCityCollection().getClass().toString());
            System.out.println("Дата инициализации: " + CollectionManager.getCityCollection().first().getCreationDate().toString());
            System.out.println("Количество элементов: " + CollectionManager.getCityCollection().toArray().length);
        }
    }

    @Override
    public boolean checkArgument(Object inputArgument) {
        if (inputArgument == null)
            return true;
        else {
            System.out.println("Команда info не имеет аргументов!");
            return false;
        }
    }

}
