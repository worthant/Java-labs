package command;


import collectionManagers.CollectionManager;

public class Save extends Command {

    public Save() {
        super(false);
    }

    @Override
    public void execute() {
        if (checkArgument(getArgument())) {
            CollectionManager collectionManager = new CollectionManager();
            collectionManager.writeCollection();
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

}
