//package command;
//
//import dragon.Dragon;
//
//public class PrintFieldAscendingColor extends Command {
//
//    public PrintFieldAscendingColor() {
//        super(false);
//    }
//
//    @Override
//    public void execute() {
//        if (checkArgument(getArgument())) {
//            for (Dragon dragon : CommandManager.getDragonCollection().getDragonArray())
//                System.out.print(dragon.getColor().toString());
//        }
//    }
//
//    @Override
//    public boolean checkArgument(Object inputArgument) {
//        if (inputArgument == null)
//            return true;
//        else {
//            System.out.println("Команда print_field_ascending_color не имеет аргументов!");
//            return false;
//        }
//    }
//
//}
