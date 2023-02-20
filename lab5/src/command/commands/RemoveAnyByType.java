//package command;
//
//import dragon.Dragon;
//import dragon.DragonType;
//
//import java.util.ArrayList;
//
//public class RemoveAnyByType extends Command {
//
//    public RemoveAnyByType() {
//        super(true);
//    }
//
//    @Override
//    public void execute() {
//        if (checkArgument(getArgument())) {
//            ArrayList<String> types = new ArrayList<>();
//
//            for (DragonType type : DragonType.values()) {
//                types.add(type.toString());
//            }
//            if (!types.contains(getArgument())) {
//                System.out.println("Такого типа не существует! Требуется ввести один из следующего списка: ");
//                for (String type : types)
//                    System.out.println(type);
//            }
//
//            ArrayList<Dragon> toDelete = new ArrayList<>();
//
//            for (Dragon dragon : CommandManager.getDragonCollection().getDragonArray()) {
//                if (dragon.getType().toString().equals(getArgument())) {
//                    toDelete.add(dragon);
//                }
//            }
//            for (Dragon dragon : toDelete) {
//                CommandManager.getDragonCollection().getDragonArray().remove(dragon);
//            }
//            System.out.println("Было успешно удалено элементов из текущей коллекции: " + toDelete.size());
//        }
//    }
//
//    @Override
//    public boolean checkArgument(Object inputArgument) {
//        if (inputArgument == null) {
//            System.out.println("Команда remove_any_by_type имеет один из аргументов: ");
//            for (DragonType type : DragonType.values())
//                System.out.println("\t" + type.toString());
//            return false;
//        } else if (inputArgument instanceof String) {
//            for (DragonType type : DragonType.values()) {
//                if (((String) inputArgument).equals(type.toString()))
//                    return true;
//                else {
//                    System.out.println("Команда remove_any_by_type имеет один из аргументов: ");
//                    for (DragonType type1 : DragonType.values())
//                        System.out.println("\t" + type1.toString());
//                    return false;
//                }
//            }
//        }
//        return false;
//    }
//}
