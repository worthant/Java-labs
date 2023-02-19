//package command;
//
//import dragon.Dragon;
//
//public class SumOfAge extends Command {
//
//    public SumOfAge() {
//        super(false);
//    }
//
//    @Override
//    public void execute() {
//        if (checkArgument(getArgument())) {
//            long sum = 0;
//
//            if (!CommandManager.getDragonCollection().getDragonArray().isEmpty()) {
//                for (Dragon dragon : CommandManager.getDragonCollection().getDragonArray()) {
//                    sum += dragon.getAge();
//                }
//                System.out.println("Суммарный возраст всех элементов текущей коллекции составляет: " + sum);
//            } else {
//                System.out.println("Текущая коллекция пуста!");
//            }
//        }
//    }
//
//    @Override
//    public boolean checkArgument(Object inputArgument) {
//        if (inputArgument == null)
//            return true;
//        else {
//            System.out.println("Команда sum_of_age не имеет аргументов!");
//            return false;
//        }
//    }
//
//}
