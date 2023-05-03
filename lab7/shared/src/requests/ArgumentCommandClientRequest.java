package requests;

import commandLogic.CommandDescription;

public class ArgumentCommandClientRequest<T> extends CommandClientRequest {
    private final T argument;

    public ArgumentCommandClientRequest(String name, char[] passwd, CommandDescription command, String[] lineArgs, T argument) {
        super(name, passwd, command, lineArgs);
        this.argument = argument;
    }

    public T getArgument() {
        return argument;
    }
}
