//package command;
//
//public class Info extends Command {
//
//    public Info() {
//        super(false);
//    }
//
//    @Override
//    public void execute() {
//        if (checkArgument(getArgument())) {
//            System.out.println("Тип коллекции: " + CommandManager.getDragonCollection().getDragonArray().getClass().toString());
//            System.out.println("Дата инициализации: " + CommandManager.getDragonCollection().getCreationDate().toString());
//            System.out.println("Количество элементов: " + CommandManager.getDragonCollection().getDragonArray().size());
//        }
//    }
//
//    @Override
//    public boolean checkArgument(Object inputArgument) {
//        if (inputArgument == null)
//            return true;
//        else {
//            System.out.println("Команда info не имеет аргументов!");
//            return false;
//        }
//    }
//
//}
