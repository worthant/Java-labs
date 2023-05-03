package responses;

import commandLogic.CommandDescription;

import java.util.ArrayList;

public class CommandDescriptionsResponse extends BaseResponse {
    private final ArrayList<CommandDescription> commands;

    public CommandDescriptionsResponse(ArrayList<CommandDescription> commands) {
        this.commands = commands;
    }

    public ArrayList<CommandDescription> getCommands() {
        return commands;
    }
}
