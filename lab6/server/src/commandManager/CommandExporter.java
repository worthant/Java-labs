package commandManager;

import commandLogic.CommandDescription;
import commandLogic.commandReceiverLogic.callers.ExternalArgumentReceiverCaller;
import commandLogic.commandReceiverLogic.callers.ExternalBaseReceiverCaller;
import models.City;

import java.util.ArrayList;

public class CommandExporter {
    public static ArrayList<CommandDescription> getCommandsToExport() {
        ArrayList<CommandDescription> commands = new ArrayList<>();
        commands.add(new CommandDescription("help", new ExternalBaseReceiverCaller()));
        commands.add(new CommandDescription("info", new ExternalBaseReceiverCaller()));
        commands.add(new CommandDescription("show", new ExternalBaseReceiverCaller()));
        commands.add(new CommandDescription("update", new ExternalBaseReceiverCaller()));
        commands.add(new CommandDescription("clear", new ExternalBaseReceiverCaller()));
        commands.add(new CommandDescription("execute_script", new ExternalBaseReceiverCaller()));
        commands.add(new CommandDescription("exit", new ExternalBaseReceiverCaller()));
        commands.add(new CommandDescription("history", new ExternalBaseReceiverCaller()));
        commands.add(new CommandDescription("sum_of_meters_above_sea_level", new ExternalBaseReceiverCaller()));
        commands.add(new CommandDescription("print_descending", new ExternalBaseReceiverCaller()));
        commands.add(new CommandDescription("print_field_descending_meters_above_sea_level", new ExternalBaseReceiverCaller()));
        commands.add(new CommandDescription("remove_by_id", new ExternalBaseReceiverCaller()));
        commands.add(new CommandDescription("add", new ExternalArgumentReceiverCaller<City>()));
        commands.add(new CommandDescription("add_if_min", new ExternalArgumentReceiverCaller<City>()));
        commands.add(new CommandDescription("remove_greater", new ExternalArgumentReceiverCaller<City>()));

        return commands;
    }
}
