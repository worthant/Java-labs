package commandManager;

import commandLogic.CommandDescription;
import requestLogic.requestSenders.CommandDescriptionsRequestSender;

import java.util.ArrayList;

public class CommandLoaderUtility {
    public static void initializeCommands() {
        ArrayList<CommandDescription> commands = new CommandDescriptionsRequestSender().sendRequestAndGetCommands();
        CommandDescriptionHolder.initialize(commands);
    }
}
