//package command;
//
//public class Clear extends Command {
//
//    public Clear() {
//        super(false);
//    }
//
//    @Override
//    public void execute() {
//        if (checkArgument(getArgument())) {
//            CommandManager.getDragonCollection().getDragonArray().clear();
//            System.out.println("Коллекция успешно очищена!");
//        }
//    }
//
//    @Override
//    public boolean checkArgument(Object inputArgument) {
//        if (inputArgument == null)
//            return true;
//        else {
//            System.out.println("Команда clear не имеет аргументов!");
//            return false;
//        }
//    }
//
//}
