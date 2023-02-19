//package command;
//
//import dragon.Dragon;
//
//public class Show extends Command {
//
//    public Show() {
//        super(false);
//    }
//
//    @Override
//    public void execute() {
//        if (checkArgument(getArgument())) {
//            if (CommandManager.getDragonCollection().getDragonArray().isEmpty()) {
//                System.out.println("Текущая коллекция пуста!");
//            } else {
//                for (Dragon dragon : CommandManager.getDragonCollection().getDragonArray()) {
//                    System.out.println(dragon);
//                }
//            }
//        }
//    }
//
//    @Override
//    public boolean checkArgument(Object inputArgument) {
//        if (inputArgument == null)
//            return true;
//        else {
//            System.out.println("Команда show не имеет аргументов!");
//            return false;
//        }
//    }
//
//}
