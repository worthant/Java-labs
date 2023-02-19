//package command;
//
//import dragon.Dragon;
//
//public class RemoveById extends Command {
//
//    public RemoveById() {
//        super(true);
//    }
//
//    @Override
//    public void execute() {
//        if (checkArgument(getArgument())) {
//            int id = Integer.parseInt((String) getArgument());
//
//            for (Dragon dragon : CommandManager.getDragonCollection().getDragonArray()) {
//                if (dragon.getId() == id) {
//                    CommandManager.getDragonCollection().getDragonArray().remove(dragon);
//                    System.out.println("Элемент с id=" + id + " успешно удален из текущей коллекции!");
//                    return;
//                }
//            }
//            System.out.println("Элемента с таким id-номером нет в текущей коллекции!");
//        }
//    }
//
//
//
//    @Override
//    public boolean checkArgument(Object inputArgument) {
//        if (inputArgument == null) {
//            System.out.println("Команда remove_by_id имеет аргумент типа данных int!");
//            return false;
//        } else if (inputArgument instanceof String) {
//            try {
//                Integer.parseInt((String) inputArgument);
//                return true;
//            } catch (NumberFormatException e) {
//                System.out.println("Команда remove_by_id имеет аргумент типа данных int!");
//                return false;
//            }
//        }
//        return false;
//    }
//
//}
