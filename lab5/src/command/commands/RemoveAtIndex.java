//package command;
//
//public class RemoveAtIndex extends Command {
//
//    public RemoveAtIndex() {
//        super(true);
//    }
//    @Override
//    public void execute() {
//        if (checkArgument(getArgument())) {
//            int index = Integer.parseInt((String) getArgument());
//
//            if (index >= 0 && index < CommandManager.getDragonCollection().getDragonArray().size()) {
//                CommandManager.getDragonCollection().getDragonArray().remove(index);
//                System.out.println("Элемент с индексом=" + index + " успешно удален из текущей коллекции!");
//            } else {
//                System.out.println("Элемента с таким индексом нет в текущей коллекции");
//            }
//        }
//    }
//
//    @Override
//    public boolean checkArgument(Object inputArgument) {
//        if (inputArgument == null) {
//            System.out.println("Команда remove_at_index имеет аргумент типа данных int!");
//            return false;
//        } else if (inputArgument instanceof String) {
//           try {
//               Integer.parseInt((String) inputArgument);
//               return true;
//           } catch (NumberFormatException e) {
//               System.out.println("Команда remove_at_index имеет аргумент типа данных int!");
//               return false;
//           }
//        }
//        return false;
//    }
//
//}
