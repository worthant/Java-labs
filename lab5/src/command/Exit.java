//package command;
//
//import user.UserManager;
//
//public class Exit extends Command {
//
//    public Exit() {
//        super(false);
//    }
//
//    @Override
//    public void execute() {
//        if (checkArgument(getArgument())) {
//            UserManager.setIsWorking(false);
//            System.out.println("Завершение работы программы!");
//        }
//    }
//
//    @Override
//    public boolean checkArgument(Object inputArgument) {
//        if (inputArgument == null)
//            return true;
//        else {
//            System.out.println("Команда exit не имеет аргументов!");
//            return false;
//        }
//    }
//
//}
