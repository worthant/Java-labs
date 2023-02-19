package command;

public interface CommandInterface {

    void execute();

    boolean checkArgument(Object argument);


}
