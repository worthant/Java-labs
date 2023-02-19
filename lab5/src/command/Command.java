package command;

public abstract class Command implements CommandInterface {

    private final boolean hasArgument;
    private Object argument;

    public Command(boolean hasArgument) {
        this.hasArgument = hasArgument;
    }

    @Override
    public abstract void execute();

    @Override
    public abstract boolean checkArgument(Object inputArgument);

    public boolean isHasArgument() {
        return hasArgument;
    }

    public Object getArgument() {
        return argument;
    }

    public void setArgument(String argument) {
        this.argument = argument;
    }
}
