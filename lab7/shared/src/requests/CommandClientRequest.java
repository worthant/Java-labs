package requests;

import commandLogic.CommandDescription;

public class CommandClientRequest extends BaseRequest {
    private final CommandDescription command;
    private final String[] lineArgs;
    private final String name;
    private final char[] passwd;

    public CommandClientRequest(String name, char[] passwd, CommandDescription command, String[] lineArgs) {
        this.command = command;
        this.lineArgs = lineArgs;
        this.name = name;
        this.passwd = passwd;
    }

    public CommandDescription getCommandDescription() {
        return command;
    }

    public String[] getLineArgs() {
        return lineArgs;
    }

    public CommandDescription getCommand() {
        return command;
    }

    public String getName() {
        return name;
    }

    public char[] getPasswd() {
        return passwd;
    }
}
